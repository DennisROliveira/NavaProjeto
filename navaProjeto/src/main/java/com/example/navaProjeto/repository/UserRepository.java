package com.example.navaProjeto.repository;

import com.example.navaProjeto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer> {

    // Método para buscar um usuário com base no 'username'
    Usuario findByUsername(String username);
}
