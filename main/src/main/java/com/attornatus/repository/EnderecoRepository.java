package com.attornatus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attornatus.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    
}
