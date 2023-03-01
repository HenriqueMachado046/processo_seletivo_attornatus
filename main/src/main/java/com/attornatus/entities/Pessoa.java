package com.attornatus.entities;

import com.attornatus.entities.Endereco;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Pessoa")
public class Pessoa {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idUsuario;

    @Column(name = "nome")
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "nascimento")
    private LocalDate nascimento;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "fk_endereco_pessoa"))
    @JsonManagedReference
    private List <Endereco> enderecoList = new ArrayList<>();

    public void addEndereco(Endereco endereco){
        enderecoList.add(endereco);
    }

    public void removerEndereco(Endereco endereco) {
        enderecoList.remove(endereco);
    }


}
