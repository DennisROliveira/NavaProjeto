package com.example.navaProjeto.repository;

import com.example.navaProjeto.model.UsuarioDetalhes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UsuarioDetalhes, Integer> {

    // Busca os detalhes do usuário com base no ID do usuário
    UsuarioDetalhes findByUsuarioId(Integer usuarioId);
}
