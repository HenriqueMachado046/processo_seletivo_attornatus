package com.attornatus.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.dao.DataIntegrityViolationException;
import org.hibernate.exception.GenericJDBCException;


import com.attornatus.entities.Pessoa;
import com.attornatus.repository.PessoaRepository;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository repository;

    
    public ResponseEntity<?> procuraPorIdPessoa(Long id){
        Optional<Pessoa> record = this.repository.findById(id);

        if (record.orElseGet(() -> null) != null) {
            return new ResponseEntity<Pessoa>(record.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Pessoa não localizada.", HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<?> buscarTodasPessoas(){
        try{
            Collection<Pessoa> lista = this.repository.findAll();
            return new ResponseEntity<Collection<Pessoa>>(lista, HttpStatus.OK);
        }catch (MethodArgumentTypeMismatchException | NumberFormatException e){
            e.printStackTrace();
            return new ResponseEntity<String>("Não foi possivel encontrar os dados. Verifique a URL", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> salvarPessoa(Pessoa pessoa){
        try{
            return new ResponseEntity<Pessoa>(this.repository.save(pessoa), HttpStatus.CREATED);
        }catch(JpaSystemException | GenericJDBCException | HttpMessageNotReadableException | DataIntegrityViolationException e){
            e.printStackTrace();
            return new ResponseEntity<String>("Dados informados inválidos. Verificar dados.", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> atualizarUsuario(long id, Pessoa pessoa){
        try{
            return repository.findById(id).map(record ->{
                record.setNome(pessoa.getNome());
                record.setNascimento(pessoa.getNascimento());
   
                Pessoa update = repository.save(record);
                return new ResponseEntity(update, HttpStatus.OK);
            }).orElse(ResponseEntity.badRequest().body("Não foi possível atualizar o usuário. Tente novamente."));
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity("Erro não identificado.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> excluirUsuario(Long id){
        return repository.findById(id).map(record->{
            repository.deleteById(id);
            return ResponseEntity.ok().body("Pessoa de ID" + id + "foi deletado com sucesso.");
        }).orElse(ResponseEntity.notFound().build());
    }


}
