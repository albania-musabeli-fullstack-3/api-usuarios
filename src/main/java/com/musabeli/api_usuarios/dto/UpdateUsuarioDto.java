package com.musabeli.api_usuarios.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class UpdateUsuarioDto {

    @Size(min = 2, max = 20, message = "El nombre de usuario debe tener entre 2 y 20 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "El nombre de usuario permite letras y números, sin espacios ni caracteres especiales")
    private String nombre;

    @Email(message = "El correo debe tener un formato válido")
    private String correo;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&_])[A-Za-z\\d@$!%*?&_]{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial"
    )
    private String password;

    @Size(min = 1, max = 2, message = "Puede asignar entre 1 y 2 roles")
    private List<@Positive(message = "El ID del rol debe ser un número positivo") Long> roles;
}
