package com.nicode.challenge_api_foro_hub.domain.services.impl;

import com.nicode.challenge_api_foro_hub.web.exceptions.ContraseniaInvalidException;
import com.nicode.challenge_api_foro_hub.web.exceptions.NombreUsuarioInvalidException;
import com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario.UsuarioAuthRequestDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.request.usuario.UsuarioLoginRequestDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.response.ResponseDto;
import com.nicode.challenge_api_foro_hub.domain.dtos.UsuarioDto;
import com.nicode.challenge_api_foro_hub.persistence.repositories.UsuarioRepository;
import com.nicode.challenge_api_foro_hub.util.JwtUtil;
import com.nicode.challenge_api_foro_hub.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    private final JwtUtil jwtUtil;

    private final PasswordUtil passwordUtil;


    public ResponseDto createUser(UsuarioAuthRequestDto authRequest) {
        usuarioRepository.saveUsuario(authRequest);
        UsuarioDto usuarioDto = usuarioRepository.getUsuarioByNombre(authRequest.getNombre());

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        Authentication authentication = new UsernamePasswordAuthenticationToken(usuarioDto.getNombre(), usuarioDto.getContrasenia(), authorities);

        String token = jwtUtil.createToken(authentication);

        return new ResponseDto(201, "Exito","Usuario creado exitosamente", token);
    }

    public ResponseDto login(UsuarioLoginRequestDto loginRequest) {
        String nombre = loginRequest.getNombre();
        String password = loginRequest.getContrasenia();

        Authentication authentication = this.authenticate(nombre, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtil.createToken(authentication);

        return new ResponseDto(200, "Exito","Login exitoso", token);
    }

    public Authentication authenticate(String nombre, String contrasenia) {

        UserDetails userDetails = this.loadUserByUsername(nombre);

        if(passwordUtil.matchesPassword(contrasenia, userDetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(nombre, userDetails.getPassword(), userDetails.getAuthorities());
        }
        else{
            throw new ContraseniaInvalidException("Acceso no autorizado, contraseña incorrecta");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {

        if(usuarioRepository.existsByNombre(nombre)){
            UsuarioDto usuarioDto = usuarioRepository.getUsuarioByNombre(nombre);

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            return new User(usuarioDto.getNombre(), usuarioDto.getContrasenia(), authorities);
        }
        else{
            throw new NombreUsuarioInvalidException("Acceso no autorizado, nombre de usuario incorrecto");
        }
    }


}
