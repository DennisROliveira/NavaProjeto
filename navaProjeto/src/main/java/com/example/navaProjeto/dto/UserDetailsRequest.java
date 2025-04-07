package com.example.navaProjeto.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserDetailsRequest {

    @Schema(description = "Email do usuário", example = "dennis@example.com")
    private String email;

    @Schema(description = "CEP do endereço do usuário", example = "01001000")
    private String cep;

    @Schema(description = "Logradouro do endereço do usuário", example = "Rua Exemplo")
    private String logradouro;

    @Schema(description = "Bairro do endereço do usuário", example = "Centro")
    private String bairro;

    @Schema(description = "Cidade do endereço do usuário", example = "São Paulo")
    private String cidade;

    @Schema(description = "Unidade Federativa (UF) do endereço do usuário", example = "SP")
    private String uf;

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
