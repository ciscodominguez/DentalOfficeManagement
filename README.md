# 🦷 Dental Office Management API

API REST para la gestión integral de un consultorio odontológico: pacientes, odontólogos, turnos, historia clínica (odontograma por pieza), prácticas, obras sociales, planes, coberturas y pagos. Construida con **Spring Boot** sobre una arquitectura en capas y protegida con **autenticación JWT** stateless.

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-brightgreen)
![Spring Security](https://img.shields.io/badge/Spring%20Security-JWT-blue)
![MySQL](https://img.shields.io/badge/MySQL-8-blue)
![MapStruct](https://img.shields.io/badge/MapStruct-1.6.3-red)
![Swagger](https://img.shields.io/badge/OpenAPI-Swagger%20UI-85EA2D)

> Proyecto académico — UTN (`com.utn.DentalOfficeManagement`).

---

## 📑 Índice

- [Características](#-características)
- [Stack tecnológico](#-stack-tecnológico)
- [Arquitectura](#-arquitectura)
- [Modelo de dominio](#-modelo-de-dominio)
- [Requisitos previos](#-requisitos-previos)
- [Configuración](#-configuración)
- [Cómo ejecutar](#-cómo-ejecutar)
- [Autenticación JWT](#-autenticación-jwt)
- [Documentación interactiva (Swagger)](#-documentación-interactiva-swagger)
- [Referencia de endpoints](#-referencia-de-endpoints)
- [Manejo de errores](#-manejo-de-errores)
- [Estructura del proyecto](#-estructura-del-proyecto)
- [Consideraciones y mejoras pendientes](#-consideraciones-y-mejoras-pendientes)

---

## ✨ Características

- **CRUD completo** para 14 entidades del dominio odontológico.
- **Arquitectura en capas**: `Controller → Service → Repository`, con DTOs de entrada/salida y mappers.
- **Seguridad con JWT**: login por email, tokens firmados con HS256 y sesiones *stateless*.
- **Contraseñas hasheadas** con BCrypt (nunca se guardan en texto plano).
- **Validación de datos** con Bean Validation (`@NotBlank`, `@Email`, `@Size`, etc.).
- **Manejo centralizado de errores** con respuestas JSON consistentes.
- **Odontograma** modelado por pieza dental (1–32) asociada a cada paciente.
- **Documentación automática** con Swagger UI / OpenAPI.

---

## 🛠 Stack tecnológico

| Componente | Tecnología | Versión |
|---|---|---|
| Lenguaje | Java | 21 |
| Framework | Spring Boot | 4.0.6 |
| Persistencia | Spring Data JPA + Hibernate | — |
| Seguridad | Spring Security + JJWT | 0.12.3 |
| Base de datos | MySQL | 8 |
| Mapeo DTO ↔ Entidad | MapStruct | 1.6.3 |
| Reducción de boilerplate | Lombok | 1.18.42 |
| Validación | Spring Boot Validation (Jakarta) | — |
| Documentación API | springdoc-openapi (Swagger UI) | 2.3.0 |
| Build | Maven (con wrapper `mvnw`) | — |

---

## 🏗 Arquitectura

El proyecto sigue una arquitectura en capas clásica de Spring Boot. Cada petición atraviesa las siguientes capas:

```
Cliente HTTP
   │  (JSON + Bearer token)
   ▼
┌──────────────────────────────────────────────┐
│  Security: JwtAuthenticationFilter            │  ← valida el token antes de la request
└──────────────────────────────────────────────┘
   ▼
┌──────────────┐   ┌──────────────┐   ┌──────────────┐
│  Controller  │ → │   Service    │ → │  Repository  │ → MySQL
│  (REST API)  │   │  (lógica)    │   │  (Spring Data│
└──────────────┘   └──────────────┘   │     JPA)     │
   ▲      │                           └──────────────┘
   │      ▼
   │   ┌──────────┐   ┌──────────┐
   │   │   DTO    │ ↔ │  Mapper  │  (MapStruct)
   │   └──────────┘   └──────────┘
   │
   └── GlobalExceptionHandler (@RestControllerAdvice)
```

- **Controller**: expone los endpoints REST y devuelve `ResponseEntity`.
- **Service**: contiene la lógica de negocio y las validaciones.
- **Repository**: interfaces `JpaRepository` con *derived queries* y `@Query` (JPQL).
- **DTO** (`Request` / `Response`): separan el modelo interno de la API pública.
- **Mapper**: conversión Entidad ↔ DTO con MapStruct.
- **Exception**: excepciones propias + handler global que unifica las respuestas de error.

---

## 🗂 Modelo de dominio

14 entidades JPA con sus relaciones principales:

| Entidad | Descripción | Relaciones clave |
|---|---|---|
| **Usuario** | Credenciales de acceso (login por email) | `ManyToOne` → Rol |
| **Rol** | Rol del usuario (ej. `ROLE_ADMIN`) | — |
| **Odontologo** | Profesional del consultorio | `OneToOne` → Usuario |
| **Paciente** | Paciente del consultorio | `ManyToOne` → Plan · `OneToMany` → PiezaDental |
| **PiezaDental** | Pieza del odontograma (nº 1–32) | `ManyToOne` → Paciente |
| **FichaMedica** | Historia clínica (alergias, medicación, grupo sanguíneo) | `OneToOne` → Paciente |
| **Turno** | Cita entre paciente y odontólogo | `ManyToOne` → Paciente, Odontologo |
| **Practica** | Catálogo de prácticas y precio base | — |
| **PracticaRealizada** | Práctica efectivamente aplicada en un turno | `ManyToOne` → Practica, PiezaDental, NomencladorPractica, CoberturaPlan, Turno, Pago (0–1) |
| **NomencladorPractica** | Código externo de la práctica según obra social | `ManyToOne` → ObraSocial, Practica |
| **ObraSocial** | Obra social / prepaga | — |
| **Plan** | Plan dentro de una obra social | `ManyToOne` → ObraSocial |
| **CoberturaPlan** | % de cobertura de un plan para una práctica | `ManyToOne` → Plan, Practica |
| **Pago** | Registro de pago (saldo, medio, fecha) | — |

---

## ✅ Requisitos previos

- **JDK 21** instalado y configurado.
- **MySQL 8** corriendo localmente (o accesible vía red).
- **Maven** *(opcional)* — el proyecto incluye el wrapper `mvnw` / `mvnw.cmd`, así que no hace falta instalarlo aparte.

Antes de levantar la app, creá la base de datos (o dejá que Hibernate la use si ya existe):

```sql
CREATE DATABASE dental_db;
```

> Con `spring.jpa.hibernate.ddl-auto: update`, Hibernate crea y actualiza las tablas automáticamente al iniciar.

---

## ⚙️ Configuración

La configuración vive en `src/main/resources/application.yaml`. **Ajustá la conexión a tu base** antes de ejecutar:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/dental_db
    username: root
    password: tu_password          # 👈 cambiá esto por tu contraseña real
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

jwt:
  secret: J4wwDwl8p80eX0OxeLFa2gt2M2WV1HAfBevophvKq6s=   # clave Base64 de 256 bits
  expiration: 86400000                                    # 24 horas en ms
```

> ⚠️ **Seguridad**: el `password` de la base y el `jwt.secret` están en el archivo a modo de ejemplo. En producción conviene moverlos a **variables de entorno** y no versionarlos.

---

## ▶️ Cómo ejecutar

Desde la raíz del proyecto (donde está el `pom.xml`):

**Linux / macOS**
```bash
./mvnw spring-boot:run
```

**Windows**
```bash
mvnw.cmd spring-boot:run
```

O compilando el `.jar`:

```bash
./mvnw clean package
java -jar target/DentalOfficeManagement-0.0.1-SNAPSHOT.jar
```

La aplicación queda disponible en `http://localhost:8080`.

---

## 🔐 Autenticación JWT

El esquema es **stateless**: el cliente obtiene un token en el login y lo envía en cada petición protegida.

### 1. Crear el primer usuario y rol

Como todavía no hay usuarios, los endpoints `POST /api/v1/roles` y `POST /api/v1/usuarios` son **públicos** para poder dar de alta el primer administrador. (La contraseña se guarda hasheada con BCrypt automáticamente.)

```bash
# Crear un rol
curl -X POST http://localhost:8080/api/v1/roles \
  -H "Content-Type: application/json" \
  -d '{ "nombre": "ROLE_ADMIN" }'

# Crear un usuario asociado a ese rol
curl -X POST http://localhost:8080/api/v1/usuarios \
  -H "Content-Type: application/json" \
  -d '{
        "username": "admin",
        "email": "admin@dental.com",
        "contrasenia": "P4ssw0rd!",
        "idRol": 1
      }'
```

### 2. Hacer login y obtener el token

```bash
curl -X POST http://localhost:8080/auth \
  -H "Content-Type: application/json" \
  -d '{ "email": "admin@dental.com", "contrasenia": "P4ssw0rd!" }'
```

Respuesta:

```json
{ "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOi..." }
```

### 3. Usar el token en peticiones protegidas

Enviá el token en el header `Authorization`:

```bash
curl http://localhost:8080/api/v1/pacientes \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9..."
```

Si el token falta, es inválido o expiró, la API responde **401 Unauthorized** con un JSON de error.

**Rutas públicas** (no requieren token): `/auth/**`, `POST /api/v1/usuarios`, `POST /api/v1/roles` y la documentación Swagger. **Todo lo demás requiere un JWT válido.**

---

## 📖 Documentación interactiva (Swagger)

Con la aplicación corriendo, la documentación OpenAPI está disponible en:

- **Swagger UI** → `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON** → `http://localhost:8080/v3/api-docs`

Desde Swagger podés probar los endpoints directamente. Para los protegidos, autorizate pegando el token en el botón **Authorize** con el formato `Bearer <token>`.

---

## 🌐 Referencia de endpoints

Base URL: `http://localhost:8080`

### Autenticación

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/auth` | Login. Devuelve el token JWT |

### Usuarios — `/api/v1/usuarios`

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/v1/usuarios` | Crear usuario *(público)* |
| `GET` | `/api/v1/usuarios` | Listar todos |
| `GET` | `/api/v1/usuarios/{id}` | Obtener por ID |
| `GET` | `/api/v1/usuarios/username/{username}` | Buscar por username |
| `PUT` | `/api/v1/usuarios/{id}` | Actualizar |
| `PATCH` | `/api/v1/usuarios/{id}/cambiar-contrasenia` | Cambiar contraseña |
| `DELETE` | `/api/v1/usuarios/{id}` | Eliminar |

### Roles — `/api/v1/roles`

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/v1/roles` | Crear rol *(público)* |
| `GET` | `/api/v1/roles` | Listar todos |
| `GET` | `/api/v1/roles/{id}` | Obtener por ID |
| `PUT` | `/api/v1/roles/{id}` | Actualizar |
| `DELETE` | `/api/v1/roles/{id}` | Eliminar |

### Pacientes — `/api/v1/pacientes`

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/v1/pacientes` | Crear |
| `GET` | `/api/v1/pacientes` | Listar todos |
| `GET` | `/api/v1/pacientes/{id}` | Obtener por ID |
| `GET` | `/api/v1/pacientes/buscar/nombre` | Buscar por nombre |
| `GET` | `/api/v1/pacientes/buscar/dni` | Buscar por DNI |
| `GET` | `/api/v1/pacientes/plan/{idPlan}` | Pacientes de un plan |
| `PUT` | `/api/v1/pacientes/{id}` | Actualizar |
| `DELETE` | `/api/v1/pacientes/{id}` | Eliminar |

### Odontólogos — `/api/v1/odontologos`

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/v1/odontologos` | Crear |
| `GET` | `/api/v1/odontologos` | Listar todos |
| `GET` | `/api/v1/odontologos/{id}` | Obtener por ID |
| `GET` | `/api/v1/odontologos/buscar/nombre` | Buscar por nombre |
| `GET` | `/api/v1/odontologos/buscar/especialidad` | Buscar por especialidad |
| `PUT` | `/api/v1/odontologos/{id}` | Actualizar |
| `DELETE` | `/api/v1/odontologos/{id}` | Eliminar |

### Turnos — `/api/v1/turnos`

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/v1/turnos` | Crear |
| `GET` | `/api/v1/turnos` | Listar todos |
| `GET` | `/api/v1/turnos/{id}` | Obtener por ID |
| `GET` | `/api/v1/turnos/paciente/{idPaciente}` | Turnos de un paciente |
| `GET` | `/api/v1/turnos/odontologo/{idOdontologo}` | Turnos de un odontólogo |
| `GET` | `/api/v1/turnos/estado/{estado}` | Filtrar por estado |
| `GET` | `/api/v1/turnos/fecha/{fecha}` | Filtrar por fecha |
| `PUT` | `/api/v1/turnos/{id}` | Actualizar |
| `PATCH` | `/api/v1/turnos/{id}/estado` | Cambiar estado |
| `DELETE` | `/api/v1/turnos/{id}` | Eliminar |

### Prácticas — `/api/v1/practicas`

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/v1/practicas` | Crear |
| `GET` | `/api/v1/practicas` | Listar todas |
| `GET` | `/api/v1/practicas/{id}` | Obtener por ID |
| `GET` | `/api/v1/practicas/buscar` | Buscar |
| `PUT` | `/api/v1/practicas/{id}` | Actualizar |
| `DELETE` | `/api/v1/practicas/{id}` | Eliminar |

### Prácticas realizadas — `/api/v1/practicas-realizadas`

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/v1/practicas-realizadas` | Crear |
| `GET` | `/api/v1/practicas-realizadas` | Listar todas |
| `GET` | `/api/v1/practicas-realizadas/{id}` | Obtener por ID |
| `GET` | `/api/v1/practicas-realizadas/turno/{idTurno}` | Por turno |
| `GET` | `/api/v1/practicas-realizadas/paciente/{idPaciente}` | Por paciente |
| `GET` | `/api/v1/practicas-realizadas/sin-pago` | Prácticas sin pago asociado |
| `PUT` | `/api/v1/practicas-realizadas/{id}` | Actualizar |
| `PATCH` | `/api/v1/practicas-realizadas/{id}/asignar-pago/{idPago}` | Asignar un pago |
| `DELETE` | `/api/v1/practicas-realizadas/{id}` | Eliminar |

### Pagos — `/api/v1/pagos`

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/v1/pagos` | Crear |
| `GET` | `/api/v1/pagos` | Listar todos |
| `GET` | `/api/v1/pagos/{id}` | Obtener por ID |
| `GET` | `/api/v1/pagos/paciente/{idPaciente}` | Pagos de un paciente |
| `GET` | `/api/v1/pagos/filtrar` | Filtrar pagos |
| `PUT` | `/api/v1/pagos/{id}` | Actualizar |
| `DELETE` | `/api/v1/pagos/{id}` | Eliminar |

### Obras sociales — `/api/v1/obras-sociales`

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/v1/obras-sociales` | Crear |
| `GET` | `/api/v1/obras-sociales` | Listar todas |
| `GET` | `/api/v1/obras-sociales/{id}` | Obtener por ID |
| `GET` | `/api/v1/obras-sociales/buscar` | Buscar |
| `PUT` | `/api/v1/obras-sociales/{id}` | Actualizar |
| `DELETE` | `/api/v1/obras-sociales/{id}` | Eliminar |

### Planes — `/api/v1/planes`

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/v1/planes` | Crear |
| `GET` | `/api/v1/planes` | Listar todos |
| `GET` | `/api/v1/planes/{id}` | Obtener por ID |
| `GET` | `/api/v1/planes/obra-social/{idObraSocial}` | Planes de una obra social |
| `PUT` | `/api/v1/planes/{id}` | Actualizar |
| `DELETE` | `/api/v1/planes/{id}` | Eliminar |

### Coberturas de plan — `/api/v1/coberturas`

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/v1/coberturas` | Crear |
| `GET` | `/api/v1/coberturas` | Listar todas |
| `GET` | `/api/v1/coberturas/{id}` | Obtener por ID |
| `GET` | `/api/v1/coberturas/plan/{idPlan}` | Coberturas de un plan |
| `PUT` | `/api/v1/coberturas/{id}` | Actualizar |
| `DELETE` | `/api/v1/coberturas/{id}` | Eliminar |

### Nomencladoras — `/api/v1/nomencladoras`

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/v1/nomencladoras` | Crear |
| `GET` | `/api/v1/nomencladoras` | Listar todas |
| `GET` | `/api/v1/nomencladoras/{id}` | Obtener por ID |
| `GET` | `/api/v1/nomencladoras/obra-social/{idObraSocial}` | Por obra social |
| `PUT` | `/api/v1/nomencladoras/{id}` | Actualizar |
| `DELETE` | `/api/v1/nomencladoras/{id}` | Eliminar |

### Piezas dentales — `/api/v1/piezas-dentales`

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/v1/piezas-dentales` | Crear |
| `GET` | `/api/v1/piezas-dentales` | Listar todas |
| `GET` | `/api/v1/piezas-dentales/{id}` | Obtener por ID |
| `GET` | `/api/v1/piezas-dentales/paciente/{idPaciente}` | Odontograma de un paciente |
| `PUT` | `/api/v1/piezas-dentales/{id}` | Actualizar |
| `DELETE` | `/api/v1/piezas-dentales/{id}` | Eliminar |

### Fichas médicas — `/api/v1/fichas-medicas`

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/v1/fichas-medicas` | Crear |
| `GET` | `/api/v1/fichas-medicas/paciente/{idPaciente}` | Ficha de un paciente |
| `PUT` | `/api/v1/fichas-medicas/paciente/{idPaciente}` | Actualizar ficha |
| `DELETE` | `/api/v1/fichas-medicas/paciente/{idPaciente}` | Eliminar ficha |

---

## ⚠️ Manejo de errores

Todas las excepciones se centralizan en `GlobalExceptionHandler` (`@RestControllerAdvice`) y devuelven un JSON uniforme:

```json
{
  "timestamp": "2026-06-18T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Paciente no encontrado con id: 99",
  "path": "/api/v1/pacientes/99"
}
```

| Excepción | Código HTTP |
|---|---|
| `ResourceNotFoundException` | `404 Not Found` |
| `DuplicateResourceException` | `409 Conflict` |
| `InvalidOperationException` | `400 Bad Request` |
| `MethodArgumentNotValidException` (validación) | `400 Bad Request` |
| `InvalidCredentialsException` / `BadCredentialsException` | `401 Unauthorized` |
| `UnauthorizedException` | `403 Forbidden` |
| `Exception` (genérica) | `500 Internal Server Error` |

---

## 📁 Estructura del proyecto

```
DentalOfficeManagement/
├── pom.xml
├── mvnw / mvnw.cmd
└── src/
    ├── main/
    │   ├── java/com/utn/DentalOfficeManagement/
    │   │   ├── DentalOfficeManagementApplication.java
    │   │   ├── Config/        # SecurityConfig
    │   │   ├── Security/      # JwtService, JwtAuthenticationFilter, CustomUserDetails, etc.
    │   │   ├── Controller/    # 15 controladores REST
    │   │   ├── Service/       # Lógica de negocio
    │   │   ├── Repository/    # Interfaces JpaRepository
    │   │   ├── Model/         # 14 entidades JPA
    │   │   ├── Mapper/        # Mappers MapStruct
    │   │   ├── DTO/
    │   │   │   ├── Request/   # DTOs de entrada
    │   │   │   └── Response/  # DTOs de salida
    │   │   └── Exception/     # Excepciones + GlobalExceptionHandler
    │   └── resources/
    │       └── application.yaml
    └── test/
        └── java/...           # DentalOfficeManagementApplicationTests
```

> 📌 Ubicá este `README.md` en la **raíz del proyecto**, junto al `pom.xml`.

---

## 🔧 Consideraciones y mejoras pendientes

- **Autorización por rol**: actualmente la configuración protege con `authenticated()` (cualquier usuario logueado accede a todo). Los roles ya se exponen como *authorities*, así que se puede afinar con `hasRole(...)` por endpoint. Para que `hasRole("ADMIN")` funcione, el nombre del rol en la BD debe ser `ROLE_ADMIN`.
- **Secretos en el repo**: mover `jwt.secret` y la contraseña de la base a variables de entorno antes de cualquier despliegue.
- **Unicidad del email**: el login es por email, pero a nivel de entidad solo `username` está marcado como `unique`. Conviene agregar la restricción de unicidad también a `email` para evitar ambigüedades.
- **Apertura de endpoints de alta**: `POST /usuarios` y `POST /roles` están abiertos solo para crear el primer admin. Una vez sembrados los datos iniciales, conviene cerrarlos.

---

*Desarrollado como proyecto académico — Tecnicatura Universitaria en Programación, UTN.*
