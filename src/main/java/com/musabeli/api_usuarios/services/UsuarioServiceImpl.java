package com.musabeli.api_usuarios.services;

import com.musabeli.api_usuarios.dto.CreateUsuarioDto;
import com.musabeli.api_usuarios.dto.LoginRequestDto;
import com.musabeli.api_usuarios.dto.ResponseUsuarioDto;
import com.musabeli.api_usuarios.dto.UpdateUsuarioDto;
import com.musabeli.api_usuarios.entities.Rol;
import com.musabeli.api_usuarios.entities.Usuario;
import com.musabeli.api_usuarios.exceptions.InvalidCredentialsException;
import com.musabeli.api_usuarios.exceptions.ResourceNotFoundException;
import com.musabeli.api_usuarios.exceptions.UserEmailExistsException;
import com.musabeli.api_usuarios.mapper.UsuarioMapper;
import com.musabeli.api_usuarios.repositories.RolRepository;
import com.musabeli.api_usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;


    private Usuario findUsuarioById(Long id){
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);

        if (usuario.isPresent()){
            return usuario.get();
        }
        else {
            throw new ResourceNotFoundException("No existen registros de usuario con id " + id);
        }
    }



    @Override
    public ResponseUsuarioDto createUsuario(CreateUsuarioDto usuarioDto){
        // Validar si el usuario existe con correo
        boolean correoExiste = this.usuarioRepository.findByCorreo(usuarioDto.getCorreo()).isPresent();

        if (correoExiste) throw new UserEmailExistsException("El correo ingresado ya se encuentra registrado");

        // Dto a entidad
        Usuario usuario = UsuarioMapper.fromCreateDto(usuarioDto);

        if (usuarioDto.getRoles() != null && !usuarioDto.getRoles().isEmpty()) {
            Set<Rol> roles = usuarioDto.getRoles().stream()
                    .map(id -> rolRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id)))
                    .collect(Collectors.toSet());
            UsuarioMapper.asignarRoles(usuario, roles);
        }

        // guarda el bd
        Usuario usuarioBd = this.usuarioRepository.save(usuario);
        return UsuarioMapper.toResponseDto(usuarioBd);
    }

    @Override
    //@Transactional(readOnly = true)
    public List<ResponseUsuarioDto> getAllUsuarios(){
        return usuarioRepository.findAll().stream().map(UsuarioMapper::toResponseDto).toList();
    }

    @Override
    public ResponseUsuarioDto getUsuarioById(Long id){
        Usuario usuario = this.findUsuarioById(id);

        return UsuarioMapper.toResponseDto(usuario);
    }

    @Override
    public ResponseUsuarioDto updateUsuario(Long id, UpdateUsuarioDto usuarioDto){
        Usuario usuario = findUsuarioById(id);

        if (usuarioDto.getCorreo() != null && !usuarioDto.getCorreo().equals(usuario.getCorreo())) {
            boolean correoExiste = usuarioRepository.findByCorreo(usuarioDto.getCorreo())
                    .map(u -> !u.getId().equals(id))
                    .orElse(false);
            if (correoExiste) {
                throw new UserEmailExistsException("El correo ingresado ya se encuentra registrado");
            }
        }

        if (usuarioDto.getNombre() != null) {
            usuario.setNombre(usuarioDto.getNombre());
        }
        if (usuarioDto.getCorreo() != null) {
            usuario.setCorreo(usuarioDto.getCorreo());
        }
        if (usuarioDto.getPassword() != null) {
            usuario.setPassword(usuarioDto.getPassword());
        }

        if (usuarioDto.getRoles() != null && !usuarioDto.getRoles().isEmpty()) {
            Set<Rol> nuevosRoles = usuarioDto.getRoles().stream()
                    .map(rolId -> rolRepository.findById(rolId)
                            .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + rolId)))
                    .collect(Collectors.toSet());
            UsuarioMapper.asignarRoles(usuario, nuevosRoles);
        }

        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return UsuarioMapper.toResponseDto(usuarioActualizado);
    }

    @Override
    public ResponseUsuarioDto deleteUsuario(Long id) {
        Usuario usuario = findUsuarioById(id);
        // convertir a Dto para devolverlo
        ResponseUsuarioDto usuarioEliminadoDto = UsuarioMapper.toResponseDto(usuario);

        usuario.getRoles().forEach(rol -> rol.getUsuarios().remove(usuario));
        usuario.getRoles().clear();

        this.usuarioRepository.deleteById(id);
        return usuarioEliminadoDto;
    }

    @Override
    public ResponseUsuarioDto login(LoginRequestDto loginDto) {
        Optional<Usuario> usuario = this.usuarioRepository.findByCorreoAndPassword(
                loginDto.getCorreo(),
                loginDto.getPassword()
        );
        if (usuario.isPresent()){
            return UsuarioMapper.toResponseDto(usuario.get());
        }
        else {
            throw new InvalidCredentialsException("Error al iniciar sesión. Correo y/o contraseña inválidos");
        }
    }


}