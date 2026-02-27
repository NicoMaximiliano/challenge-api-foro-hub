package com.nicode.challenge_api_foro_hub.configuration.security;

import com.nicode.challenge_api_foro_hub.configuration.security.filters.JwtFilter;
import com.nicode.challenge_api_foro_hub.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    private final PasswordUtil passwordUtil;

    //CONFIGURA LOS FILTROS DE SEGURIDAD PARA CONTROLAR COMO SE AUTENTICAN Y AUTORIZAN LAS SOLICITUDES
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    http.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
                    http.requestMatchers(HttpMethod.POST, "/auth/signup").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/topicos/**").hasRole("USER");
                    http.requestMatchers(HttpMethod.POST, "/topicos/create").hasRole("USER");
                    http.requestMatchers(HttpMethod.PUT, "/topicos/update/**").hasRole("USER");
                    http.requestMatchers(HttpMethod.DELETE, "/topicos/delete/**").hasRole("USER");
                    http.anyRequest().denyAll();
                })
                .addFilterBefore(jwtFilter, BasicAuthenticationFilter.class)
                .build();
    }

    //GESTIONA EL PROCESO DE AUTENTICACION, VALIDANDO LAS CREDENCIALES DEL USUARIO
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration){
        return authenticationConfiguration.getAuthenticationManager();
    }

    //BUSCA AL USUARIO CON EL USER DETAILS SERVICE Y VALIDA EL PASSWORD USANDO EL PASSWORD ENCODER
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordUtil.getPasswordEncoder());

        return  provider;
    }


}
