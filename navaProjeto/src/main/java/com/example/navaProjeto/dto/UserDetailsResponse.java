package com.example.navaProjeto.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserDetailsResponse {

    @Schema(description = "Mensagem de resposta da operação", example = "Detalhes do usuário obtidos com sucesso.")
    private String message;

    @Schema(description = "Detalhes do usuário", implementation = UserDetailsRequest.class)
    private UserDetailsRequest userDetails;

    public UserDetailsResponse(String message, UserDetailsRequest userDetails) {
        this.message = message;
        this.userDetails = userDetails;
    }

    // Getters e Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDetailsRequest getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetailsRequest userDetails) {
        this.userDetails = userDetails;
    }
}
