package br.edu.iff.projetoSGCI.controller.view;

import br.edu.iff.projetoSGCI.model.Chamado;
import br.edu.iff.projetoSGCI.model.CriticidadeEnum;
import br.edu.iff.projetoSGCI.model.InformacaoHistorico;
import br.edu.iff.projetoSGCI.service.AtendenteService;
import br.edu.iff.projetoSGCI.service.ChamadoService;
import br.edu.iff.projetoSGCI.service.InformacaoHistoricoService;
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
@RequestMapping(path="/informacaoHistoricos")
public class InformacaoHistoricoViewController {
    @Autowired
    private InformacaoHistoricoService service;
    @Autowired
    private AtendenteService atendenteService;
    @Autowired
    private InformacaoHistoricoService informacaoHistoricoService;


    @GetMapping
    public String getAll(Model model){
        model.addAttribute("informacaoHistoricos",service.findAll());
        return "informacaoHistoricos";
    }
    
    @PostMapping(path="/informacaoHistorico")
    public String save(@Valid @ModelAttribute InformacaoHistorico informacaoHistorico, BindingResult result, Model model){
        model.addAttribute("informacaoHistorico", new InformacaoHistorico());
        model.addAttribute("atendentes", atendenteService.findAll());
        model.addAttribute("criticidade",CriticidadeEnum.values());
        List<FieldError> list = new ArrayList<>();
        for(FieldError fe : result.getFieldErrors()){
            if((!fe.getField().equals("dataAbertura")) && (!fe.getField().equals("prazo"))){
                list.add(fe);
            }
        }
        if (!list.isEmpty()){
            model.addAttribute("msgErros",result.getAllErrors());
            return "formInformacaoHistorico";
        }
        informacaoHistorico.setId(null);
        try{
            service.save(informacaoHistorico);
            model.addAttribute("msgSucesso","InformacaoHistorico cadastrado com sucesso!");
            model.addAttribute("informacaoHistorico", new InformacaoHistorico());
            return "formInformacaoHistorico";
        } catch(Exception e) {
            model.addAttribute("msgErros",new ObjectError ("InformacaoHistorico", e.getMessage()));
            return "formInformacaoHistorico";
        }
    }
    
    @GetMapping(path="/informacaoHistorico")
    public String cadastro(Model model){
        model.addAttribute("informacaoHistorico", new InformacaoHistorico());
        model.addAttribute("atendentes", atendenteService.findAll());
        return "formInformacaoHistorico";
    }
    
    @GetMapping(path="/{idInformacaoHistorico}/adicionarInfo")
    public String adicionarInfo(@PathVariable("id") Long id, Model model){
        model.addAttribute("informacaoHistorico", service.findById(id));
        model.addAttribute("atendentes", atendenteService.findAll());
        return "formInfoInformacaoHistorico";
    }  
}
