package com.musabeli.api_usuarios.services;

import com.musabeli.api_usuarios.dto.*;

import java.util.List;


public interface UsuarioService {

    ResponseUsuarioDto createUsuario(CreateUsuarioDto usuarioDto);
    List<ResponseUsuarioDto> getAllUsuarios();
    ResponseUsuarioDto getUsuarioById(Long id);
    ResponseUsuarioDto updateUsuario(Long id, UpdateUsuarioDto usuarioDto);
    ResponseUsuarioDto deleteUsuario(Long id);
    ResponseUsuarioDto login(LoginRequestDto loginDto);
    ResponseUsuarioPasswordDto recuperarPassword(String correo);
}
