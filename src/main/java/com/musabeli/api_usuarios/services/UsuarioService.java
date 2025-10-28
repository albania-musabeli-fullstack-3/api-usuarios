package com.musabeli.api_usuarios.services;

import com.musabeli.api_usuarios.dto.CreateUsuarioDto;
import com.musabeli.api_usuarios.dto.ResponseUsuarioDto;

import java.util.List;


public interface UsuarioService {

    ResponseUsuarioDto createUsuario(CreateUsuarioDto usuarioDto);
    List<ResponseUsuarioDto> getAllUsuarios();
}
