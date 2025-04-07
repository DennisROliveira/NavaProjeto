package com.example.navaProjeto.controller;

import com.example.navaProjeto.dto.UserDetailsRequest;
import com.example.navaProjeto.dto.UserDetailsResponse;
import com.example.navaProjeto.model.ApiLog;
import com.example.navaProjeto.repository.ApiLogRepository;
import com.example.navaProjeto.service.UserDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ApiLogRepository apiLogRepository;  

    @Autowired
    private ObjectMapper objectMapper;  

    @Operation(summary = "Obter detalhes do usuário", 
               description = "Esse endpoint retorna os detalhes do usuário baseado no username.")
    @GetMapping("/details")
    public ResponseEntity<UserDetailsResponse> getUserDetails(
            @Parameter(description = "Nome de usuário para buscar os detalhes", required = true) @RequestParam String username) {
        
        UserDetailsResponse details = userDetailsService.getUserDetails(username);
        
        if (details != null) {
            return ResponseEntity.ok(details);
        } else {
            return ResponseEntity.status(404).body(new UserDetailsResponse("Usuário não encontrado", null));
        }
    }

    @Operation(summary = "Atualizar detalhes do usuário", 
               description = "Esse endpoint permite a atualização dos detalhes do usuário com base no username.")
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<Map<String, String>> updateUserDetails(
        @Parameter(description = "Nome de usuário para atualizar detalhes", required = true) @RequestParam String username,
        @RequestBody UserDetailsRequest request) {

        
        UserDetailsResponse currentDetails = userDetailsService.getUserDetails(username);
        
        // Se o usuário não for encontrado, retorna erro
        if (currentDetails == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuário não encontrado.");
            
            // Registra log de erro
            logApiRequest(username, request, null, "Erro - Usuário não encontrado");
            
            return ResponseEntity.status(404).body(response);
        }

        // Salvar os dados atuais do usuário (history)
        String historyJson = convertToJson(currentDetails); 
        
        // Atualizar os detalhes do usuário
        boolean updated = userDetailsService.updateUserDetails(username, request);
        
        // Salvar log após a atualização
        if (updated) {
            String payloadJson = convertToJson(request);  
            String status = "Sucesso";  

            ApiLog log = new ApiLog();
            log.setDataCreated(LocalDateTime.now());
            log.setPayload(payloadJson);
            log.setHistory(historyJson);
            log.setRequester(username);
            log.setStatus(status);

            apiLogRepository.save(log);  

            Map<String, String> response = new HashMap<>();
            response.put("message", "Detalhes do usuário atualizados com sucesso.");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Erro ao atualizar os detalhes do usuário.");

            // Registra log de erro
            logApiRequest(username, request, historyJson, "Erro - Falha na atualização");

            return ResponseEntity.status(404).body(response);  
        }
    }

    // Método para registrar log da API
    private void logApiRequest(String username, UserDetailsRequest request, String historyJson, String status) {
        String payloadJson = convertToJson(request); 
        
        ApiLog log = new ApiLog();
        log.setDataCreated(LocalDateTime.now());
        log.setPayload(payloadJson);
        log.setHistory(historyJson);
        log.setRequester(username);
        log.setStatus(status);

        apiLogRepository.save(log);
    }

    private String convertToJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);  
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";  
        }
    }
}
