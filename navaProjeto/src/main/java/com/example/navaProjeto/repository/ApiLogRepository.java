package com.example.navaProjeto.repository;

import com.example.navaProjeto.model.ApiLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApiLogRepository extends JpaRepository<ApiLog, Long> {
    
    List<ApiLog> findByRequester(String requester);

    List<ApiLog> findByStatus(String status);

    Optional<ApiLog> findById(Long id);
    
}
