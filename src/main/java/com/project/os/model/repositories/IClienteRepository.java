package com.project.os.model.repositories;

import com.project.os.model.entities.Cliente;
import com.project.os.model.entities.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query("SELECT obj FROM Cliente obj WHERE obj.cpf =:cpf")
    Cliente findByCPF(@Param("cpf") String cpf);
}
