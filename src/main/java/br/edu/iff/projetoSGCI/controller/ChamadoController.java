package br.edu.iff.projetoSGCI.controller;

import br.edu.iff.projetoSGCI.model.Chamado;
import br.edu.iff.projetoSGCI.service.ChamadoService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/apirest/chamados")
public class ChamadoController {
    @Autowired
    private ChamadoService service;
    
    @GetMapping
    public ResponseEntity getAll(@RequestParam(name = "page", defaultValue="0", required = false) int page, 
            @RequestParam(name = "size", defaultValue="10", required = false) int size, 
            @RequestParam(name = "clienteId", defaultValue="0", required = false) Long clienteId, 
            @RequestParam(name = "atendenteId", defaultValue="0", required = false) Long atendenteId, 
            @RequestParam(name = "servidorId", defaultValue="0", required = false) Long servidorId){
        return ResponseEntity.ok(service.findAll(page, size, clienteId, atendenteId, servidorId));
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Chamado chamado){
        chamado.setId(null);
        service.save(chamado);
        return ResponseEntity.status(HttpStatus.CREATED).body(chamado);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody Chamado chamado){
        chamado.setId(id);
        service.update(chamado);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
