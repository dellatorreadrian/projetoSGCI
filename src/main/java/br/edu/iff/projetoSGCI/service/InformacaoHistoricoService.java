package br.edu.iff.projetoSGCI.service;

import br.edu.iff.projetoSGCI.model.InformacaoHistorico;
import br.edu.iff.projetoSGCI.repository.InformacaoHistoricoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class InformacaoHistoricoService {
    @Autowired
    private InformacaoHistoricoRepository repo;
    
    public List<InformacaoHistorico> findAll(){
        return repo.findAll();
    }
    
    public List<InformacaoHistorico> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    
    public InformacaoHistorico findById(Long id){
        Optional<InformacaoHistorico> result = repo.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException("Informacao Historico não encontrado pelo ID");
        }
        return result.get();
    }
    
    public InformacaoHistorico save(InformacaoHistorico i){
        try {
            return repo.save(i);    
        } catch(Exception e){
            throw new RuntimeException("Falha ao salvar o Informacao Historico.");
        }
    }
    
    //Informação Histórico não pode ser excluído ou atualizado.
}
