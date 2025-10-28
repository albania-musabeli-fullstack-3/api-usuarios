package com.musabeli.api_usuarios.repositories;

import com.musabeli.api_usuarios.entities.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Metodo personalizado para buscar un usuario por su correo
    Optional<Usuario> findByCorreo(String correo);

    // Metodo personalizado para inicio sesion (validar datos)
    Optional<Usuario> findByCorreoAndPassword(String correo, String password);

    // UsuarioRepository.java
//    @EntityGraph(attributePaths = {"roles"})
//    @Override
//    List<Usuario> findAll();

}
