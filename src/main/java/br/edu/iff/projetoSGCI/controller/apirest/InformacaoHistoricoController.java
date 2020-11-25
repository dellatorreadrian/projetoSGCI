package br.edu.iff.projetoSGCI.controller.apirest;

import br.edu.iff.projetoSGCI.model.InformacaoHistorico;
import br.edu.iff.projetoSGCI.service.InformacaoHistoricoService;
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
@RequestMapping(path = "/apirest/informacoesHistorico")
public class InformacaoHistoricoController {
    @Autowired
    private InformacaoHistoricoService service;
    
    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(name = "page", defaultValue="0", required = false) int page, 
            @RequestParam(name = "size", defaultValue="10", required = false) int size){
        return ResponseEntity.ok(service.findAll(page, size));
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody InformacaoHistorico informacaoHistorico){
        informacaoHistorico.setId(null);
        service.save(informacaoHistorico);
        return ResponseEntity.status(HttpStatus.CREATED).body(informacaoHistorico);
    }

}
