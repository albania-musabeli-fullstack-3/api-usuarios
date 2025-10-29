package com.musabeli.api_usuarios.services;

import com.musabeli.api_usuarios.dto.CreateUsuarioDto;
import com.musabeli.api_usuarios.dto.ResponseUsuarioDto;
import com.musabeli.api_usuarios.dto.UpdateUsuarioDto;

import java.util.List;


public interface UsuarioService {

    ResponseUsuarioDto createUsuario(CreateUsuarioDto usuarioDto);
    List<ResponseUsuarioDto> getAllUsuarios();
    ResponseUsuarioDto getUsuarioById(Long id);
    ResponseUsuarioDto updateUsuario(Long id, UpdateUsuarioDto usuarioDto);
    ResponseUsuarioDto deleteUsuario(Long id);
}
