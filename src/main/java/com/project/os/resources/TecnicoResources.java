package com.project.os.resources;

import com.project.os.model.entities.Tecnico;
import com.project.os.model.entities.dtos.TecnicoDTO;
import com.project.os.model.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResources {

    @Autowired
    private TecnicoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
        TecnicoDTO objDTO = new TecnicoDTO(service.findById(id));
        return ResponseEntity.ok().body(objDTO);
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
       List<TecnicoDTO> listDto = service.findAll()
               .stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);

       /**** Solucao 1
       List<Tecnico> list = service.findAll();
       List<TecnicoDTO> listDTO = new ArrayList<>();
       for(Tecnico obj : list){
           listDTO.add(new TecnicoDTO(obj));
       }
       return ResponseEntity.ok().body(listDTO);
       */

    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDto){
        Tecnico newObj = service.create(objDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDTO){
        TecnicoDTO newObj = new TecnicoDTO(service.update(id, objDTO));
        return ResponseEntity.ok().body(newObj);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();

    }



}
