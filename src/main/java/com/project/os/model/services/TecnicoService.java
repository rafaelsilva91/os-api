package com.project.os.model.services;

import com.project.os.model.entities.Pessoa;
import com.project.os.model.entities.Tecnico;
import com.project.os.model.entities.dtos.TecnicoDTO;
import com.project.os.model.exceptions.DataIntegratyViolationException;
import com.project.os.model.exceptions.ObjectNotFoundExceptions;
import com.project.os.model.repositories.IPessoaRepository;
import com.project.os.model.repositories.ITecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private ITecnicoRepository repository;

    @Autowired
    private IPessoaRepository pessoaRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundExceptions(
                "Objeto não encontrado! Id: {" +id+ "}, Tipo: "+Tecnico.class.getName()));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDto){
        if(findByCPF(objDto) != null){
            throw new DataIntegratyViolationException("CPF Já cadastrado na base de dados");
        }

        Tecnico newObj = new Tecnico(null, objDto.getNome(), objDto.getCpf(), objDto.getTelefone());
        return repository.save(newObj);

    }

    public Tecnico update(Integer id, TecnicoDTO objDTO) {
        Tecnico oldObj = findById(id);

        if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id){
            throw new DataIntegratyViolationException("CPF Já cadastrado na base de dados");
        }

        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());

        return repository.save(oldObj);

    }

    public void delete(Integer id) {
        Tecnico obj = findById(id);

        if(obj.getList().size() > 0){
            throw new DataIntegratyViolationException("Existe Ordem de Servico vinculadas a este objeto. Não pode ser deletado!");
        }

        repository.deleteById(id);
    }

    private Pessoa findByCPF(TecnicoDTO objDto){
        Pessoa obj = pessoaRepository.findByCPF(objDto.getCpf());
        if (obj != null){
            return obj;
        }
        return null;
    }

}
