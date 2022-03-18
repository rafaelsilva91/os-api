package com.project.os.model.entities.dtos;

import com.project.os.model.entities.Cliente;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = -6452601221233636425L;

    private Integer id;

    @NotEmpty(message = "O campo NOME deve ser preenchido!")
    private String nome;

    @CPF
    @NotEmpty(message = "O campo CPF deve ser preenchido!")
    private String cpf;

    @NotEmpty(message = "O campo TELEFONE deve ser preenchido!")
    private String telefone;

    public ClienteDTO() {
        super();
    }

    public ClienteDTO(Cliente obj) {
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
