package com.nicode.challenge_api_foro_hub.domain.dtos.response.success;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"status","message"})
public record SuccessTopicoResponse(String status, String message) {
}
