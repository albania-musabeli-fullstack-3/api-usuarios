package com.musabeli.api_usuarios.controllers;

import com.musabeli.api_usuarios.dto.CreateUsuarioDto;
import com.musabeli.api_usuarios.dto.ResponseUsuarioDto;
import com.musabeli.api_usuarios.entities.Usuario;
import com.musabeli.api_usuarios.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<ResponseUsuarioDto> createUsuario(@Valid @RequestBody CreateUsuarioDto usuarioDto){
        ResponseUsuarioDto nuevoUsuario = this.usuarioService.createUsuario(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }
}