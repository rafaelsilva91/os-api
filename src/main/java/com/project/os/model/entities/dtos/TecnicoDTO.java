package com.project.os.model.entities.dtos;

import com.project.os.model.entities.Tecnico;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class TecnicoDTO implements Serializable {

    private static final long serialVersionUID = -2621463932216279743L;

    private Integer id;

    @NotEmpty(message = "O campo NOME deve ser preenchido!")
    private String nome;

    @CPF
    @NotEmpty(message = "O campo CPF deve ser preenchido!")
    private String cpf;

    @NotEmpty(message = "O campo TELEFONE deve ser preenchido!")
    private String telefone;

    public TecnicoDTO() {
        super();
    }

    public TecnicoDTO(Tecnico obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
