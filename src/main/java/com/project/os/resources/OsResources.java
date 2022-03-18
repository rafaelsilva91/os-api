package com.project.os.resources;

import com.project.os.model.entities.Tecnico;
import com.project.os.model.entities.dtos.OsDTO;
import com.project.os.model.entities.dtos.TecnicoDTO;
import com.project.os.model.services.OsService;
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
@RequestMapping(value = "/OS")
public class OsResources {

    @Autowired
    private OsService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OsDTO> findById(@PathVariable Integer id){
        OsDTO obj = new OsDTO(service.findById(id));
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<OsDTO>> findAll(){
       List<OsDTO> listDto = service.findAll()
               .stream().map(obj -> new OsDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);

    }

    @PostMapping
    public ResponseEntity<OsDTO> create(@Valid @RequestBody OsDTO obj){
        obj = new OsDTO(service.create(obj));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<OsDTO> update(@Valid @RequestBody OsDTO obj){
        obj = new OsDTO(service.update(obj));
        return ResponseEntity.ok().body(obj);

    }

//    @PutMapping("/{id}")
//    public ResponseEntity<OsDTO> update(@PathVariable Integer id, @Valid @RequestBody OsDTO obj){
//        OsDTO newObj = new TecnicoDTO(service.update(id, obj));
//        return ResponseEntity.ok().body(newObj);
//
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();

    }



}
