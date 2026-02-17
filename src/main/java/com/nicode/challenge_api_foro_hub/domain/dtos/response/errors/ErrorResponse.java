package com.nicode.challenge_api_foro_hub.domain.dtos.response.errors;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({"status", "code", "message"})
public record ErrorResponse(String status, String code, String message) {

}
