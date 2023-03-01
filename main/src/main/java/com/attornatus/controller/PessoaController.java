package com.attornatus.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.entities.Pessoa;
import com.attornatus.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    
    @Autowired
    private PessoaService service;

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarTodasPessoas(){
        return service.buscarTodasPessoas();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<?> buscarPorIdDasPessoas(@PathVariable Long id) {
        return service.procuraPorIdPessoa(id);
    }

    @PostMapping
    public ResponseEntity<?> salvarPessoa(@RequestBody Pessoa pessoa){
        return service.salvarPessoa(pessoa);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @PutMapping(path = { "{id}" })
    public ResponseEntity<?> excluirPessoa(@PathVariable long id){
        return service.excluirUsuario(id);
    }

}