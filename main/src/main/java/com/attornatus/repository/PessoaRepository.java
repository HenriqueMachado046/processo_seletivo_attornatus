package com.attornatus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attornatus.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
