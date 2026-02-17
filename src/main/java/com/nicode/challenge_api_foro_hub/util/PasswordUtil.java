package com.nicode.challenge_api_foro_hub.util;

import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Getter
@Component
public class PasswordUtil {

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //ENCRIPTA LA CONTRASEÑA
    public String encryptPassword(String rawPassword){
        return this.passwordEncoder.encode(rawPassword);
    }

    //VALIDA LA CONTRASEÑA
    public boolean matchesPassword(String rawPassword, String encodedPassword){
        return this.passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
