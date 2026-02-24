package com.nicode.challenge_api_foro_hub.configuration.security.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.nicode.challenge_api_foro_hub.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //EXTRAIGO EL HEADER COMPLETO DE LA PARTE DE AUTHORIZATION DE LA SOLICITUD
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        //VALIDANDO SI EL TOKEN ES DIFERENTE DE NULO (si existe dentro de la solicitud)
        if(jwtToken != null){

            //EXTRAIGO SOLAMENTE EL TOKEN
            jwtToken = jwtToken.substring(7);

            //VALIDO EL TOKEN Y LO DEVUELVO DECODIFICADO
            DecodedJWT decodedJWT = jwtUtil.validateToken(jwtToken);

            //EXTRAYENDO EL NOMBRE Y LOS PERMISOS DEL TOKEN
            String username = jwtUtil.extractUser(decodedJWT);
            String authorities = jwtUtil.getSpecificClaim(decodedJWT, "authorities").asString();

            //AGREGANDO LOS PERMISOS A UNA COLECCION
            Collection<? extends GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

            Authentication authentication = new UsernamePasswordAuthenticationToken(username,null, grantedAuthorities);

            //LLAMAR A EL SECURITY CONTEXT
            SecurityContext securityContext = SecurityContextHolder.getContext();

            //RESETEO EL SECURITY CONTEXT
            securityContext.setAuthentication(authentication);

            //RESETEO EL SECURITY CONTEXT HOLDER
            SecurityContextHolder.setContext(securityContext);

        }

        filterChain.doFilter(request, response);
    }


}
