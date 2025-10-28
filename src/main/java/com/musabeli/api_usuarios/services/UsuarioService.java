package com.musabeli.api_usuarios.services;

import com.musabeli.api_usuarios.dto.CreateUsuarioDto;
import com.musabeli.api_usuarios.dto.ResponseUsuarioDto;
import com.musabeli.api_usuarios.entities.Usuario;


public interface UsuarioService {

    ResponseUsuarioDto createUsuario(CreateUsuarioDto usuarioDto);
}
