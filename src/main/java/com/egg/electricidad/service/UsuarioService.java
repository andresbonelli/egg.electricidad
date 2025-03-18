package com.egg.electricidad.service;

import com.egg.electricidad.api.dto.UserRegisterDTO;
import com.egg.electricidad.domain.entity.Role;
import com.egg.electricidad.domain.entity.Usuario;
import com.egg.electricidad.domain.repository.UsuarioRepository;
import com.egg.electricidad.exception.RecordNotFoundException;
import com.egg.electricidad.exception.ValidationException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByEmail(email);
        if (null != user) {
            List<GrantedAuthority> permisos = new ArrayList<>();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" +
                    user.getRol().toString());
            permisos.add(p);
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("subject", user);
            return new User(user.getEmail(), user.getPasswordHash(), permisos);
        } else {
            log.error("Usuario no encontrado: {}", email);
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.findByEmail(email) != null;
    }

    public Usuario buscarPorId(UUID id) {
        return usuarioRepository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    @Transactional
    public void registro(UserRegisterDTO usuario) {
        validar(usuario.email(), usuario.password(), usuario.confirmPassword());
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(null != usuario.nombre() ? usuario.nombre() : null);
        nuevoUsuario.setApellido(null != usuario.apellido() ? usuario.apellido() : null);
        nuevoUsuario.setEmail(usuario.email());
        nuevoUsuario.setPasswordHash(new BCryptPasswordEncoder().encode(usuario.password()));
        nuevoUsuario.setRol(Role.USER);

        usuarioRepository.save(nuevoUsuario);
    }

    @Transactional
    public void cambiarPassword(UUID id, String password, String confirmPassword) {
        Usuario usuario = buscarPorId(id);
        validar(usuario.getEmail(), password, confirmPassword);
        usuario.setPasswordHash(new BCryptPasswordEncoder().encode(password));
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void actualizar(UUID id, String nombre, String apellido) {
        Usuario usuario = buscarPorId(id);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void cambiarRol(UUID id) {
        Usuario usuario = buscarPorId(id);
        usuario.setRol(usuario.getRol().equals(Role.USER) ? Role.ADMIN : Role.USER);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void eliminar(UUID id) {
        usuarioRepository.deleteById(id);
    }

    private void validar(String email, String password, String confirmPassword) throws ValidationException {
        if (null == email || email.isBlank()) {
            throw new ValidationException("el email no puede ser nulo o estar vacío");
        }
        if (null == password || password.isBlank() || password.length() < 4) {
            throw new ValidationException("La contraseña no puede estar vacía, y debe tener más de 4 caracteres");
        }
        if (!password.equals(confirmPassword)) {
            throw new ValidationException("Las contraseñas ingresadas deben ser iguales");
        }
    }
}
