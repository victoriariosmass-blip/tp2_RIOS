package com.aydsii.tp2.repository;

import com.aydsii.tp2.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    // Spring Data JPA traduce automáticamente este nombre de método a una consulta SQL: 
    // SELECT count(*) FROM clientes WHERE email = ?
    boolean existsByEmail(String email);
    
}