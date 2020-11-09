package br.edu.iff.projetoSGCI.service;

import br.edu.iff.projetoSGCI.model.Atendente;
import br.edu.iff.projetoSGCI.repository.AtendenteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AtendenteService {
    @Autowired
    private AtendenteRepository repo;
    
    public List<Atendente> findAll(){
        return repo.findAll();
    }
    
    public List<Atendente> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    
    public Atendente findById(Long id){
        Optional<Atendente> result = repo.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException("Atendente não encontrado pelo ID");
        }
        return result.get();
    }
    
    public Atendente save(Atendente a){
        try {
            return repo.save(a);    
        } catch(Exception e){
            throw new RuntimeException("Falha ao salvar o Atendente.");
        }
    }
    
    public Atendente update(Atendente a, String senhaAtual, String novaSenha, String confirmarNovaSenha){
        Atendente obj = findById(a.getId());
        alterarSenha(obj,senhaAtual,novaSenha,confirmarNovaSenha);
        verificaExclusaoChamadosComAtendentes(obj);
        try {
            a.setLogin(obj.getLogin());
            a.setSenha(obj.getSenha());
            return repo.save(a);    
        } catch(Exception e){
            throw new RuntimeException("Falha ao atualizar o Atendente.");
        }
    }
    
    public void delete(Long id){
        Atendente obj = findById(id);
        verificaExclusaoChamadosComAtendentes(obj);
        try {
            repo.delete(obj);    
        } catch(Exception e){
            throw new RuntimeException("Falha ao excluir o Atendente.");
        }
    }
    
    private void verificaExclusaoChamadosComAtendentes(Atendente a){
        if (!a.getChamados().isEmpty()){
            throw new RuntimeException("Não foi possível excluir o Atendente, pois existem Chamados relacionados a ele."); 
        }
    }
    
    private void alterarSenha(Atendente obj, String senhaAtual, String novaSenha, String confirmarNovaSenha){
        if(!senhaAtual.isBlank() && !novaSenha.isBlank() && !confirmarNovaSenha.isBlank()){
            if(senhaAtual.equals(obj.getSenha())){
                throw new RuntimeException("Senha Atual está incorreta.");
            }
            if (!novaSenha.equals(confirmarNovaSenha)){
                throw new RuntimeException("Senha não foi confirmada corretamente.");
            }
            obj.setSenha(novaSenha);
        }
    }
}
