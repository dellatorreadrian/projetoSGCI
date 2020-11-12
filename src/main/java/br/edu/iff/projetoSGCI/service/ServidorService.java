package br.edu.iff.projetoSGCI.service;

import br.edu.iff.projetoSGCI.exception.NotFoundException;
import br.edu.iff.projetoSGCI.model.Servidor;
import br.edu.iff.projetoSGCI.repository.ServidorRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ServidorService {
    @Autowired
    private ServidorRepository repo;
    
    public List<Servidor> findAll(){
        return repo.findAll();
    }
    
    public List<Servidor> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    
    public Servidor findById(Long id){
        Optional<Servidor> result = repo.findById(id);
        if(result.isEmpty()){
            throw new NotFoundException("Servidor não encontrado pelo ID");
        }
        return result.get();
    }
    
    public Servidor save(Servidor s){
        try {
            return repo.save(s);    
        } catch(Exception e){
            throw new RuntimeException("Falha ao salvar o Servidor.");
        }
    }
    
    public Servidor update(Servidor s){
        Servidor obj = findById(s.getId());
        verificaExclusaoChamadosComServidores(obj);
        try {
            return repo.save(s);    
        } catch(Exception e){
            Throwable t = e;
            while (t.getCause() != null){
                t = t.getCause();
                if (t instanceof ConstraintViolationException){
                    throw ((ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Falha ao atualizar o Servidor.");
        }
    }
    
    public void delete(Long id){
        Servidor obj = findById(id);
        verificaExclusaoChamadosComServidores(obj);
        try {
            repo.delete(obj);    
        } catch(Exception e){
            throw new RuntimeException("Falha ao excluir o Servidor.");
        }
    }
    
    private void verificaExclusaoChamadosComServidores(Servidor s){
        if (!s.getChamados().isEmpty()){
            throw new RuntimeException("Não foi possível excluir o Servidor, pois existem Chamados relacionados a ele."); 
        }
    }
}
