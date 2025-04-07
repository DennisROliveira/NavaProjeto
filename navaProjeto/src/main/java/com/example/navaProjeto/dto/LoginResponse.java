package com.example.navaProjeto.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class LoginResponse {

    @Schema(description = "Mensagem de retorno do login", example = "Login bem-sucedido")
    private String message;

    @Schema(description = "Nome do usuário autenticado", example = "NOME TESTE")
    private String name;

    public LoginResponse(String message, String name) {
        this.message = message;
        this.name = name;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
