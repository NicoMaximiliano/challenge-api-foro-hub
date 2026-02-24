package com.nicode.challenge_api_foro_hub.domain.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

    private Integer codigo;
    private String status;
    private Object mensaje;
    private String token;


    public ResponseDto(Integer codigo, String status, Object mensaje) {
        this.codigo = codigo;
        this.status = status;
        this.mensaje = mensaje;
    }

    public Map<String, Object> showResponse() {
        return Map.of(
                "Codigo", codigo,
                "Status", status,
                "Mensaje", mensaje
        );
    }

    public Map<String, Object> showResponseWithToken() {
        return Map.of(
                "Codigo", codigo,
                "Status", status,
                "Mensaje", mensaje,
                "Token", token
        );
    }


}
