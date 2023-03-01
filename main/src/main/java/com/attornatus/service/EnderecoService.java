package com.attornatus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.dao.DataIntegrityViolationException;
import org.hibernate.exception.GenericJDBCException;

import com.attornatus.entities.Endereco;
import com.attornatus.entities.Pessoa;
import com.attornatus.repository.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository repository;

    public List buscarTodosEnderecos() {
        return repository.findAll();
    }

    public ResponseEntity<?> buscarPorIdDoEndereco(Long id) {
        return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> salvarEndereco(Endereco endereco) {
        try {
            return new ResponseEntity<Endereco>(this.repository.save(endereco), HttpStatus.CREATED);
        } catch (JpaSystemException | GenericJDBCException | HttpMessageNotReadableException
                | DataIntegrityViolationException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Dados informados inválidos. Verificar os dados.",
                    HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> atualizarEndereco(long id, Endereco endereco) {
        try {
            return repository.findById(id).map(record -> {
                record.setCep(endereco.getCep());
                record.setCidade(endereco.getCidade());
                record.setLogradouro(endereco.getLogradouro());
                record.setNumero(endereco.getNumero());

                Endereco update = repository.save(record);

                return new ResponseEntity(update, HttpStatus.OK);
            }).orElse(ResponseEntity.badRequest().body("Não foi possível atualizar o Endereço. Tente novamente"));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Erro não identificado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> excluirEndereco(long id) {
        return repository.findById(id).map(record -> {
            repository.deleteById(id);

            return ResponseEntity.ok().body("Endereco de ID" + id + " foi deletado com sucesso!");
        }).orElse(ResponseEntity.notFound().build());
    }

}