package br.edu.iff.projetoSGCI.controller.view;

import br.edu.iff.projetoSGCI.model.Atendente;
import br.edu.iff.projetoSGCI.model.NivelAtendenteEnum;
import br.edu.iff.projetoSGCI.service.AtendenteService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path="/atendentes")
public class AtendenteViewController {
    @Autowired
    private AtendenteService service;
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("atendentes",service.findAll());
        return "atendentes";
    }
    
    @GetMapping(path="/atendente")
    public String cadastro(Model model){
        model.addAttribute("atendente",new Atendente());
        model.addAttribute("nivel",NivelAtendenteEnum.values());
        return "formAtendente";
    }
    
    @PostMapping(path="/atendente")
    public String save(@Valid @ModelAttribute Atendente atendente, BindingResult result, @RequestParam("confirmarSenha") String confirmarSenha, Model model){
        model.addAttribute("nivel",NivelAtendenteEnum.values());
        if (result.hasErrors()){
            model.addAttribute("msgErros",result.getAllErrors());
            return "formAtendente";
        }
        if (!atendente.getSenha().equals(confirmarSenha)){
            model.addAttribute("msgErros",new ObjectError ("Atendente", "Campos Senha e Confirmar Senha devem ser iguais."));
            return "formAtendente";
        }
        atendente.setId(null);
        try{
            service.save(atendente);
            model.addAttribute("msgSucesso","Atendente cadastrado com sucesso!");
            model.addAttribute("atendente", new Atendente());
            return "formAtendente";
        } catch(Exception e) {
            model.addAttribute("msgErros",new ObjectError ("Atendente", e.getMessage()));
            return "formAtendente";
        }
    }
    
    @GetMapping(path="/atendente/{id}")
    public String alterar(@PathVariable("id") Long id, Model model){
        model.addAttribute("atendente", service.findById(id));
        model.addAttribute("nivel",NivelAtendenteEnum.values());
        return "formAtendente";
    }
    
    @PostMapping(path="/atendente/{id}")
    public String update(@Valid @ModelAttribute Atendente atendente, BindingResult result, @PathVariable("id") Long id, Model model){
        model.addAttribute("nivel",NivelAtendenteEnum.values());
        List<FieldError> list = new ArrayList<>();
        for (FieldError fe : result.getFieldErrors()){
            if((!fe.getField().equals("senha")) && (!fe.getField().equals("login"))){
                list.add(fe);
            }
        }
        if (!list.isEmpty()){
            model.addAttribute("msgErros",result.getAllErrors());
            return "formAtendente";
        }
        atendente.setId(id);
        try{
            service.update(atendente,"","","");
            model.addAttribute("msgSucesso","Atendente atualizado com sucesso!");
            model.addAttribute("atendente", atendente);
            return "formAtendente";
        } catch(Exception e) {
            model.addAttribute("msgErros",new ObjectError ("Atendente", e.getMessage()));
            return "formAtendente";
        }
    }
    
    @GetMapping(path="/{id}/deletar")
    public String deletar(@PathVariable("id") Long id){
        service.delete(id);
        return "redirect:/atendentes";
    }    
}
