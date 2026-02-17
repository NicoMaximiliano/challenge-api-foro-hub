package com.nicode.challenge_api_foro_hub.domain.dtos.response.errors;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"status", "code", "messages"})
public record MultiErrorResponse(String status, String code, List<String> messages) {
}
