package com.project.os.model.services;

import com.project.os.model.entities.Cliente;
import com.project.os.model.entities.Os;
import com.project.os.model.entities.Pessoa;
import com.project.os.model.entities.Tecnico;
import com.project.os.model.entities.dtos.OsDTO;
import com.project.os.model.entities.dtos.TecnicoDTO;
import com.project.os.model.entities.enums.Prioridade;
import com.project.os.model.entities.enums.Status;
import com.project.os.model.exceptions.DataIntegratyViolationException;
import com.project.os.model.exceptions.ObjectNotFoundExceptions;
import com.project.os.model.repositories.IClienteRepository;
import com.project.os.model.repositories.IOsRepository;
import com.project.os.model.repositories.IPessoaRepository;
import com.project.os.model.repositories.ITecnicoRepository;
import org.apache.tomcat.jni.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OsService {

    @Autowired
    private IOsRepository repository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public Os findById(Integer id){
        Optional<Os> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundExceptions(
                "Objeto não encontrado! Id: {" +id+ "}, Tipo: "+Os.class.getName()));
    }

    public List<Os> findAll() {
        return repository.findAll();
    }

    public Os create(@Valid OsDTO obj){
        return fromDTO(obj);
    }

    public Os update(@Valid OsDTO obj) {
        findById(obj.getId());
        return fromDTO(obj);
    }

    private Os fromDTO(OsDTO obj){
        Os newObj  = new Os();
        newObj.setId(obj.getId());
        newObj.setObservacoes(obj.getObservacoes());
        newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        newObj.setStatus(Status.toEnum(obj.getStatus()));

        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        newObj.setTecnico(tecnico);
        newObj.setCliente(cliente);

        if(newObj.getStatus().getCod().equals(2)){
            newObj.setDataFechamento(LocalDateTime.now());
        }
        return repository.save(newObj);
    }

    public void delete(Integer id) {
        Os obj = findById(id);
        if(obj.getDataAbertura() != null && obj.getDataFechamento() != null){
            throw new DataIntegratyViolationException("Não pode ser deletado!");
        }
        repository.deleteById(id);
    }

}
