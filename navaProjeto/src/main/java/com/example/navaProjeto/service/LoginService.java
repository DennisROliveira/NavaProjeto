package com.example.navaProjeto.service;

import com.example.navaProjeto.model.Usuario;
import com.example.navaProjeto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Método para autenticar um usuário baseado no username e senha.
     * @param username Nome de usuário
     * @param password Senha do usuário
     * @return O nome do usuário se as credenciais estiverem corretas, ou null se não forem válidas.
     */
    public String authenticate(String username, String password) {
        // Verifica se o usuário existe
        Usuario user = userRepository.findByUsername(username);

        // Se o usuário existir e a senha for igual, retorna o nome do usuário
        if (user != null && user.getPassword().equals(password)) {
            return user.getName();
        }
        return null; 
    }
}
