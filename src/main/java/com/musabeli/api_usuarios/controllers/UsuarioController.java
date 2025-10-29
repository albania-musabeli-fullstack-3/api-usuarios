package com.musabeli.api_usuarios.controllers;

import com.musabeli.api_usuarios.dto.CreateUsuarioDto;
import com.musabeli.api_usuarios.dto.LoginRequestDto;
import com.musabeli.api_usuarios.dto.ResponseUsuarioDto;
import com.musabeli.api_usuarios.dto.UpdateUsuarioDto;
import com.musabeli.api_usuarios.entities.Usuario;
import com.musabeli.api_usuarios.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getAllUsuarios")
    public ResponseEntity<List<ResponseUsuarioDto>> getAllUsuarios(){
        List<ResponseUsuarioDto> usuarios = this.usuarioService.getAllUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUsuarioDto> getUsuarioById(@PathVariable Long id){
        ResponseUsuarioDto usuario = this.usuarioService.getUsuarioById(id);
        return  ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseUsuarioDto> updateUsuario(@PathVariable Long id, @Valid
            @RequestBody UpdateUsuarioDto usuarioDto){
        ResponseUsuarioDto usuarioActualizado = this.usuarioService.updateUsuario(id, usuarioDto);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseUsuarioDto> deleteUsuario(@PathVariable Long id){
        ResponseUsuarioDto usuarioEliminado = this.usuarioService.deleteUsuario(id);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioEliminado);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseUsuarioDto> login(@Valid @RequestBody LoginRequestDto loginDto){
        ResponseUsuarioDto usuarioLogin = this.usuarioService.login(loginDto);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioLogin);
    }
}