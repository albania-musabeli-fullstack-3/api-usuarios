package com.musabeli.api_usuarios.dto;

import java.util.List;

public record ResponseUsuarioDto (
    Long id,
    String nombre,
    String correo,
    List<RolDto> roles
) {
    // DTO anidado para Rol
    public record RolDto(
            Long id,
            String nombre
    ) {}
}
