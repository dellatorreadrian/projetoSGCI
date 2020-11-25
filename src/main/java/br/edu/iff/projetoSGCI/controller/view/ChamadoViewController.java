package br.edu.iff.projetoSGCI.controller.view;

import br.edu.iff.projetoSGCI.model.Chamado;
import br.edu.iff.projetoSGCI.model.CriticidadeEnum;
import br.edu.iff.projetoSGCI.model.InformacaoHistorico;
import br.edu.iff.projetoSGCI.model.StatusChamadoEnum;
import br.edu.iff.projetoSGCI.service.AtendenteService;
import br.edu.iff.projetoSGCI.service.ChamadoService;
import br.edu.iff.projetoSGCI.service.ClienteService;
import br.edu.iff.projetoSGCI.service.InformacaoHistoricoService;
import br.edu.iff.projetoSGCI.service.ServidorService;
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

@Controller
@RequestMapping(path="/chamados")
public class ChamadoViewController {
    @Autowired
    private ChamadoService service;
    @Autowired
    private AtendenteService atendenteService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private InformacaoHistoricoService informacaoHistoricoService;
    @Autowired
    private ServidorService servidorService;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("chamados",service.findAll());
        return "chamados";
    }
    
    @PostMapping(path="/chamado")
    public String save(@Valid @ModelAttribute Chamado chamado, BindingResult result, Model model){
        model.addAttribute("chamado", new Chamado());
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("atendentes", atendenteService.findAll());
        model.addAttribute("servidores", servidorService.findAll());
        model.addAttribute("statusChamado",StatusChamadoEnum.values());
        model.addAttribute("criticidade",CriticidadeEnum.values());
        List<FieldError> list = new ArrayList<>();
        for(FieldError fe : result.getFieldErrors()){
            if((!fe.getField().equals("dataAbertura")) && (!fe.getField().equals("prazo"))){
                list.add(fe);
            }
        }
        if (!list.isEmpty()){
            model.addAttribute("msgErros",result.getAllErrors());
            return "formChamado";
        }
        chamado.setId(null);
        
        try{
            service.save(chamado);
            model.addAttribute("msgSucesso","Chamado cadastrado com sucesso!");
            model.addAttribute("chamado", new Chamado());
            return "formChamado";
        } catch(Exception e) {
            model.addAttribute("msgErros",new ObjectError ("Chamado", e.getMessage()));
            return "formChamado";
        }
    }
    
    @GetMapping(path="/chamado/{id}")
    public String alterar(@PathVariable("id") Long id, Model model){
        model.addAttribute("chamado", service.findById(id));
        model.addAttribute("atendentes", atendenteService.findAll());
        model.addAttribute("statusChamado",StatusChamadoEnum.values());
        return "formChamado";
    }
    
    @PostMapping(path="/chamado/{id}")
    public String update(@Valid @ModelAttribute Chamado chamado, BindingResult result, @PathVariable("id") Long id, Model model){
        model.addAttribute("chamado", new Chamado());
        model.addAttribute("atendentes", atendenteService.findAll());
        model.addAttribute("statusChamado",StatusChamadoEnum.values());
        List<FieldError> list = new ArrayList<>();
        for(FieldError fe : result.getFieldErrors()){
            if((!fe.getField().equals("dataAbertura")) && (!fe.getField().equals("prazo"))){
                list.add(fe);
            }
        }
        if (!list.isEmpty()){
            model.addAttribute("msgErros",result.getAllErrors());
            return "formChamado";
        }
        chamado.setId(id);
        try{
            service.update(chamado);
            model.addAttribute("msgSucesso","Chamado atualizado com sucesso!");
            model.addAttribute("chamado", chamado);
            return "formChamado";
        } catch(Exception e) {
            model.addAttribute("msgErros",new ObjectError ("Chamado", e.getMessage()));
            return "formChamado";
        }
    }
    
    @GetMapping(path="/chamado")
    public String cadastro(Model model){
        model.addAttribute("chamado", new Chamado());
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("atendentes", atendenteService.findAll());
        model.addAttribute("servidores", servidorService.findAll());
        model.addAttribute("statusChamado",StatusChamadoEnum.values());
        model.addAttribute("criticidade",CriticidadeEnum.values());
        return "formChamado";
    }
    
    @GetMapping(path="/{idChamado}/adicionarInfo")
    public String adicionarInfo(@PathVariable("id") Long id, Model model){
        model.addAttribute("chamado", service.findById(id));
        model.addAttribute("infoChamado", new InformacaoHistorico());
        model.addAttribute("atendentes", atendenteService.findAll());
        return "formInfoChamado";
    }  
}
