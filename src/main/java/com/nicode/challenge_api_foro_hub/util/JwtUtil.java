package com.nicode.challenge_api_foro_hub.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${security.jwt.key.private}")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;


    //CREAR EL JWT TOKEN
    public String createToken(Authentication authentication){
        //CREANDO OBJETO DE ALGORITMO DE ENCRIPTACION
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

        //OBTENER EL USUARIO AUTENTICADO
        String username = authentication.getPrincipal().toString();

        //OBTENER LOS PERMISOS DEL USUARIO
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        //CREO EL TOKEN
        return JWT.create()
                .withIssuer(this.userGenerator)
                .withSubject(username)
                .withClaim("authorities", authorities)
                .withIssuedAt(new Date()) //Fecha que se generara el token
                .withExpiresAt(new Date(System.currentTimeMillis() + 1800000)) //Tiempo en la que expira el token en 1800000 milisegundos = 30 minutos
                .withJWTId(UUID.randomUUID().toString()) //Asignar un ID al token
                .withNotBefore(new Date(System.currentTimeMillis())) //Fecha en la que el token sera valido
                .sign(algorithm);
    }

    //VALIDAR EL JWT TOKEN
    public DecodedJWT validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

            //CREO UN OBJETO VERIFICADOR DEL TOKEN
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.userGenerator)
                    .build();

            //VALIDARA Y DEVOLVERA EL TOKEN DECODIFICADO
            return verifier.verify(token);

        } catch (TokenExpiredException e) {
            throw new RuntimeException("El token ha expirado el: " + e.getExpiredOn());
        } catch (SignatureVerificationException e) {
            throw new RuntimeException("La firma del token no es válida");
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Error al verificar el token");
        }
    }

    //EXTRAER EL USERNAME DEL TOKEN DECODIFICADO
    public String extractUser(DecodedJWT decodedJWT){
        return decodedJWT.getSubject();
    }

    //EXTRAER UN CLAIM ESPECIFICO DEL TOKEN DECODIFICADO
    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName){
        return decodedJWT.getClaim(claimName);
    }

    //EXTRAER TODOS LOS CLAIMS DEL TOKEN DECODIFICADO
    public Map<String, Claim> getAllClaims(DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }

}
