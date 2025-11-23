package com.musabeli.api_usuarios.dto;

import java.util.List;

public record ResponseUsuarioPasswordDto(
        Long id,
        String nombre,
        String correo,
        String password,
        List<ResponseUsuarioDto.RolDto> roles
) {}
