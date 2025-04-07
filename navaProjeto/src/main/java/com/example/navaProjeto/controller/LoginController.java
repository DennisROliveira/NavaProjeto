package com.example.navaProjeto.controller;

import com.example.navaProjeto.service.LoginService;
import com.example.navaProjeto.dto.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Operation(
            summary = "Autentica o usuário com base no username e senha",
            description = "Esse endpoint recebe um username e uma senha para autenticar o usuário."
    )
    @PostMapping
    public ResponseEntity<LoginResponse> login(
            @Parameter(description = "Nome de usuário para login", required = true) @RequestParam String username,
            @Parameter(description = "Senha do usuário", required = true) @RequestParam String password) {

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().body(new LoginResponse("Usuário ou senha inválidos", null));
        }

        String userName = loginService.authenticate(username, password);

        if (userName != null) {
            LoginResponse response = new LoginResponse("Login bem-sucedido", userName);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(new LoginResponse("Credenciais inválidas", null));
        }
    }
}
