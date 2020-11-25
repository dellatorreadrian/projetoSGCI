package br.edu.iff.projetoSGCI.controller.apirest;

import br.edu.iff.projetoSGCI.model.Atendente;
import br.edu.iff.projetoSGCI.service.AtendenteService;
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
@RequestMapping(path = "/apirest/atendentes")
public class AtendenteController {
    @Autowired
    private AtendenteService service;
    
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
    public ResponseEntity save(@Valid @RequestBody Atendente atendente){
        atendente.setId(null);
        service.save(atendente);
        return ResponseEntity.status(HttpStatus.CREATED).body(atendente);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Atendente atendente){
        atendente.setId(id);
        service.update(atendente,"","","");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}/alterarSenha")
    public ResponseEntity alterarSenha(@PathVariable("id") Long id,
            @RequestParam(name = "senhaAtual", defaultValue = "", required = true) String senhaAtual,
            @RequestParam(name = "novaSenha", defaultValue = "", required = true) String novaSenha,
            @RequestParam(name = "confirmarNovaSenha", defaultValue = "", required = true) String confirmarNovaSenha){
        Atendente atendente = service.findById(id);
        service.update(atendente,"senhaAtual","novaSenha","confirmarNovaSenha");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
