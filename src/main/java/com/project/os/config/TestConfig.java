package com.project.os.config;

import com.project.os.model.entities.Cliente;
import com.project.os.model.entities.Os;
import com.project.os.model.entities.Tecnico;
import com.project.os.model.entities.enums.Prioridade;
import com.project.os.model.entities.enums.Status;
import com.project.os.model.repositories.IClienteRepository;
import com.project.os.model.repositories.IOsRepository;
import com.project.os.model.repositories.ITecnicoRepository;
import com.project.os.model.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public void instanciaDB(){
        this.dbService.InstanciaDB();
    }

}
