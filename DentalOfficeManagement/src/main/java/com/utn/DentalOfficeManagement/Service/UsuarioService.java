package com.utn.DentalOfficeManagement.Service;

import com.utn.DentalOfficeManagement.DTO.Request.UsuarioRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.UsuarioResponseDTO;
import com.utn.DentalOfficeManagement.Exception.DuplicateResourceException;
import com.utn.DentalOfficeManagement.Exception.InvalidCredentialsException;
import com.utn.DentalOfficeManagement.Exception.ResourceNotFoundException;
import com.utn.DentalOfficeManagement.Mapper.UsuarioMapper;
import com.utn.DentalOfficeManagement.Model.Rol;
import com.utn.DentalOfficeManagement.Model.Usuario;
import com.utn.DentalOfficeManagement.Repository.RolRepository;
import com.utn.DentalOfficeManagement.Repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByUsername(dto.getUsername())) {
            throw new DuplicateResourceException("Usuario", "username", dto.getUsername());
        }

        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Usuario", "email", dto.getEmail());
        }

        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "id", dto.getIdRol()));

        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setContrasenia(passwordEncoder.encode(dto.getContrasenia()));
        usuario.setRol(rol);
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(usuarioGuardado);
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO obtenerUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
        return usuarioMapper.toResponse(usuario);
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO obtenerUsuarioPorUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "username", username));
        return usuarioMapper.toResponse(usuario);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO dto) {        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));

        if (!usuario.getUsername().equals(dto.getUsername()) && usuarioRepository.existsByUsername(dto.getUsername())) {
            throw new DuplicateResourceException("Usuario", "username", dto.getUsername());
        }

        if (!usuario.getEmail().equals(dto.getEmail()) && usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Usuario", "email", dto.getEmail());
        }

        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "id", dto.getIdRol()));

        usuario.setUsername(dto.getUsername());
        usuario.setEmail(dto.getEmail());
        usuario.setContrasenia(passwordEncoder.encode(dto.getContrasenia()));
        usuario.setRol(rol);
        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(usuarioActualizado);
    }

    public UsuarioResponseDTO cambiarContrasenia(Long id, String nuevaContrasenia) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
        usuario.setContrasenia(passwordEncoder.encode(nuevaContrasenia));
        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(usuarioActualizado);
    }

//    @Transactional(readOnly = true)
//    public void autenticar(String username, String contrasenia) {
//        Usuario usuario = usuarioRepository.findByUsername(username)
//                .orElseThrow(() -> new InvalidCredentialsException("Credenciales inválidas"));
//
//        if (!passwordEncoder.matches(contrasenia, usuario.getContrasenia())) {
//            throw new InvalidCredentialsException("Credenciales inválidas");
//        }
//    }<

    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario", "id", id);
        }
        usuarioRepository.deleteById(id);
    }
}

