package br.edu.iff.projetoSGCI.controller.view;

import br.edu.iff.projetoSGCI.model.Servidor;
import br.edu.iff.projetoSGCI.model.StatusServidorEnum;
import br.edu.iff.projetoSGCI.service.ServidorService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/servidores")
public class ServidorViewController {
    @Autowired
    private ServidorService service;
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("servidores",service.findAll());
        return "servidores";
    }
    
    @GetMapping(path="/servidor")
    public String cadastro(Model model){
        model.addAttribute("servidor",new Servidor());
        model.addAttribute("status",StatusServidorEnum.values());
        return "formServidor";
    }
    
    @PostMapping(path="/servidor")
    public String save(@Valid @ModelAttribute Servidor servidor, BindingResult result, Model model){
        model.addAttribute("status",StatusServidorEnum.values());
        if (result.hasErrors()){
            model.addAttribute("msgErros",result.getAllErrors());
            return "formServidor";
        }
        servidor.setId(null);
        try{
            service.save(servidor);
            model.addAttribute("msgSucesso","Servidor cadastrado com sucesso!");
            model.addAttribute("servidor", new Servidor());
            return "formServidor";
        } catch(Exception e) {
            model.addAttribute("msgErros",new ObjectError ("Servidor", e.getMessage()));
            return "formServidor";
        }
    }
    
    @GetMapping(path="/servidor/{id}")
    public String alterar(@PathVariable("id") Long id, Model model){
        model.addAttribute("servidor", service.findById(id));
        model.addAttribute("status",StatusServidorEnum.values());
        return "formServidor";
    }
    
    @PostMapping(path="/servidor/{id}")
    public String update(@Valid @ModelAttribute Servidor servidor, BindingResult result, @PathVariable("id") Long id, Model model){
        model.addAttribute("status",StatusServidorEnum.values());
        if (result.hasErrors()){
            model.addAttribute("msgErros",result.getAllErrors());
            return "formServidor";
        }
        servidor.setId(id);
        try{
            service.update(servidor);
            model.addAttribute("msgSucesso","Servidor atualizado com sucesso!");
            model.addAttribute("servidor", servidor);
            return "formServidor";
        } catch(Exception e) {
            model.addAttribute("msgErros",new ObjectError ("Servidor", e.getMessage()));
            return "formServidor";
        }
    }
    
    @GetMapping(path="/{id}/deletar")
    public String deletar(@PathVariable("id") Long id){
        service.delete(id);
        return "redirect:/servidores";
    }
   
}
