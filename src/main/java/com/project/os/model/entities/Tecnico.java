package com.project.os.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tecnico extends Pessoa implements Serializable {

    private static final long serialVersionUID = -4612485639300547156L;

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<Os> list = new ArrayList<>();

    public Tecnico(){
        super();
    }

    public Tecnico(Integer id, String nome, String cpf, String telefone) {super(id, nome, cpf, telefone); }

    public List<Os> getList() {
        return list;
    }

    public void setList(List<Os> list) {
        this.list = list;
    }


}
