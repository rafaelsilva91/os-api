package com.project.os.model.repositories;

import com.project.os.model.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPessoaRepository extends JpaRepository<Pessoa, Integer> {

    @Query("SELECT obj FROM Pessoa obj WHERE obj.cpf =:cpf")
    Pessoa findByCPF(@Param("cpf") String cpf);
}
