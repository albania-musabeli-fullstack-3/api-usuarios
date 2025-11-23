package com.musabeli.api_usuarios.mapper;

import com.musabeli.api_usuarios.dto.CreateUsuarioDto;
import com.musabeli.api_usuarios.dto.ResponseUsuarioDto;
import com.musabeli.api_usuarios.dto.ResponseUsuarioPasswordDto;
import com.musabeli.api_usuarios.entities.Rol;
import com.musabeli.api_usuarios.entities.Usuario;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsuarioMapper {

    // CREATE: DTO → Entidad (sin roles)
    public static Usuario fromCreateDto(CreateUsuarioDto dto) {
        return Usuario.builder()
                .nombre(dto.getNombre())
                .correo(dto.getCorreo())
                .password(dto.getPassword())
                .roles(new HashSet<>())
                .build();
    }

    public static void asignarRoles(Usuario usuario, Set<Rol> roles) {
        usuario.getRoles().clear();
        usuario.getRoles().addAll(roles);
    }

    public static ResponseUsuarioDto toResponseDto(Usuario usuario) {
        List<ResponseUsuarioDto.RolDto> rolesDto = usuario.getRoles().stream()
                .map(rol -> new ResponseUsuarioDto.RolDto(rol.getId(), rol.getNombre()))
                .toList();

        return new ResponseUsuarioDto(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                rolesDto
        );
    }


    public static ResponseUsuarioPasswordDto toResponseConPasswordDto(Usuario usuario) {
        List<ResponseUsuarioDto.RolDto> rolesDto = usuario.getRoles().stream()
                .map(rol -> new ResponseUsuarioDto.RolDto(rol.getId(), rol.getNombre()))
                .toList();

        return new ResponseUsuarioPasswordDto(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getPassword(),
                rolesDto
        );
    }
}
