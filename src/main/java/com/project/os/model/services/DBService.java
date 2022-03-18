package com.project.os.model.services;

import com.project.os.model.entities.Cliente;
import com.project.os.model.entities.Os;
import com.project.os.model.entities.Tecnico;
import com.project.os.model.entities.enums.Prioridade;
import com.project.os.model.entities.enums.Status;
import com.project.os.model.repositories.IClienteRepository;
import com.project.os.model.repositories.IOsRepository;
import com.project.os.model.repositories.ITecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private ITecnicoRepository tecnicoRepository;
    @Autowired
    private IClienteRepository clienteRepository;
    @Autowired
    private IOsRepository osRepository;

    public void InstanciaDB(){
        Tecnico t1 = new Tecnico(null, "Rafael Silva", "351.113.300-97", "(65)99001-0000");
		Tecnico t2 = new Tecnico(null, "Jose Alberto", "957.590.280-73", "(65)99002-0012");
        Tecnico t3 = new Tecnico(null, "Ramon Ribeiro", "025.804.720-88", "(65)99003-0013");

        Cliente c1 = new Cliente(null, "Ana Silva", "469.755.800-65", "(65)99333-0022");
		Cliente c2 = new Cliente(null, "Joao Leão", "157.105.800-15", "(65)99444-0033");
//		Cliente c3 = new Cliente(null, "Roberto Souza", "944.910.790-48", "9(65)99555-0044");

        Os os1 = new Os(null, Prioridade.BAIXA, 	Status.ANDAMENTO, "Descricao Obs: Teste Create OS",  t1, c1);
        Os os2 = new Os(null, Prioridade.BAIXA, 	Status.ANDAMENTO, "Descricao Obs: Teste Create OS",  t2, c2);

        t1.getList().add(os1);
        c1.getList().add(os1);

        t2.getList().add(os2);
        c2.getList().add(os2);

        tecnicoRepository.saveAll(Arrays.asList(t1,t2, t3));
        clienteRepository.saveAll(Arrays.asList(c1, c2));
        osRepository.saveAll(Arrays.asList(os1, os2));

    }
}
