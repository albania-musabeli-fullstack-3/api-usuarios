package com.musabeli.api_usuarios.services;

import com.musabeli.api_usuarios.dto.CreateUsuarioDto;
import com.musabeli.api_usuarios.dto.ResponseUsuarioDto;
import com.musabeli.api_usuarios.entities.Rol;
import com.musabeli.api_usuarios.entities.Usuario;
import com.musabeli.api_usuarios.exceptions.UserEmailExistsException;
import com.musabeli.api_usuarios.mapper.UsuarioMapper;
import com.musabeli.api_usuarios.repositories.RolRepository;
import com.musabeli.api_usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;


    @Override
    public ResponseUsuarioDto createUsuario(CreateUsuarioDto usuarioDto) {
        // Validar si el usuario existe con correo
        boolean correoExiste = this.usuarioRepository.findByCorreo(usuarioDto.getCorreo()).isPresent();

        if (correoExiste) throw new UserEmailExistsException("El correo ingresado ya se encuentra registrado");

        // Dto a entidad
        Usuario usuario = UsuarioMapper.fromCreateDto(usuarioDto);

        if (usuarioDto.getRoles() != null && !usuarioDto.getRoles().isEmpty()) {
            Set<Rol> roles = usuarioDto.getRoles().stream()
                    .map(id -> rolRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id)))
                    .collect(Collectors.toSet());
            UsuarioMapper.asignarRoles(usuario, roles);
        }

        // guarda el bd
        Usuario usuarioBd = this.usuarioRepository.save(usuario);
        return UsuarioMapper.toResponseDto(usuarioBd);


    }

    @Override
    //@Transactional(readOnly = true)
    public List<ResponseUsuarioDto> getAllUsuarios() {
        return usuarioRepository.findAll().stream().map(UsuarioMapper::toResponseDto).toList();
    }
}