package com.example.navaProjeto.service;

import com.example.navaProjeto.dto.UserDetailsRequest;
import com.example.navaProjeto.dto.UserDetailsResponse;
import com.example.navaProjeto.model.Usuario;
import com.example.navaProjeto.model.UsuarioDetalhes;
import com.example.navaProjeto.repository.UserDetailsRepository;
import com.example.navaProjeto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    // Obtém os detalhes do usuário pelo username
    public UserDetailsResponse getUserDetails(String username) {
        Usuario user = userRepository.findByUsername(username);

        if (user != null) {
            UsuarioDetalhes details = userDetailsRepository.findByUsuarioId(user.getId());
            if (details != null) {
                return new UserDetailsResponse("Detalhes encontrados", mapToUserDetailsRequest(details));
            }
        }
        return new UserDetailsResponse("Usuário ou detalhes não encontrados", null);
    }

    // Atualiza os detalhes do usuário
    public boolean updateUserDetails(String username, UserDetailsRequest request) {
        Usuario user = userRepository.findByUsername(username);

        if (user != null) {
            UsuarioDetalhes details = userDetailsRepository.findByUsuarioId(user.getId());
            if (details != null) {
                details.setEmail(request.getEmail());
                details.setCep(request.getCep());
                details.setLogradouro(request.getLogradouro());
                details.setBairro(request.getBairro());
                details.setCidade(request.getCidade());
                details.setUf(request.getUf());
                userDetailsRepository.save(details);
                return true;
            }
        }
        return false;
    }

    // Mapeia os detalhes do usuário
    private UserDetailsRequest mapToUserDetailsRequest(UsuarioDetalhes details) {
        UserDetailsRequest request = new UserDetailsRequest();
        request.setEmail(details.getEmail());
        request.setCep(details.getCep());
        request.setLogradouro(details.getLogradouro());
        request.setBairro(details.getBairro());
        request.setCidade(details.getCidade());
        request.setUf(details.getUf());
        return request;
    }
}
