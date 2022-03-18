package com.project.os.model.services;

import com.project.os.model.entities.Cliente;
import com.project.os.model.entities.Pessoa;
import com.project.os.model.entities.dtos.ClienteDTO;
import com.project.os.model.exceptions.DataIntegratyViolationException;
import com.project.os.model.exceptions.ObjectNotFoundExceptions;
import com.project.os.model.repositories.IClienteRepository;
import com.project.os.model.repositories.IPessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private IClienteRepository repository;

    @Autowired
    private IPessoaRepository pessoaRepository;

    public Cliente findById(Integer id){
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundExceptions(
                "Objeto não encontrado! Id: {" +id+ "}, Tipo: "+Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDTO objDto){
        if(findByCPF(objDto) != null){
            throw new DataIntegratyViolationException("CPF Já cadastrado na base de dados");
        }

        Cliente newObj = new Cliente(null, objDto.getNome(), objDto.getCpf(), objDto.getTelefone());
        return repository.save(newObj);

    }

    public Cliente update(Integer id, ClienteDTO objDTO) {
        Cliente oldObj = findById(id);

        if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id){
            throw new DataIntegratyViolationException("CPF Já cadastrado na base de dados");
        }

        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());

        return repository.save(oldObj);

    }

    public void delete(Integer id) {
        Cliente obj = findById(id);

        if(obj.getList().size() > 0){
            throw new DataIntegratyViolationException("Existe Ordem de Servico vinculadas a este objeto. Não pode ser deletado!");
        }

        repository.deleteById(id);
    }

    private Pessoa findByCPF(ClienteDTO objDto){
        Pessoa obj = pessoaRepository.findByCPF(objDto.getCpf());
        if (obj != null){
            return obj;
        }
        return null;
    }

}
