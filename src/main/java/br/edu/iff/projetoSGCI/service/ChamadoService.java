package br.edu.iff.projetoSGCI.service;

import br.edu.iff.projetoSGCI.exception.NotFoundException;
import br.edu.iff.projetoSGCI.model.Chamado;
import br.edu.iff.projetoSGCI.repository.ChamadoRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository repo;
    
    public List<Chamado> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    
    public List<Chamado> findAll(int page, int size, Long clienteId, Long atendenteId, Long servidorId){
        Pageable p = PageRequest.of(page, size);
        
        if (clienteId!=0 && atendenteId!=0 && servidorId!=0){
            return repo.findByClienteAndAtendenteAndServidor(clienteId, atendenteId, servidorId, p);
        } else if (clienteId!=0 && atendenteId!=0) {
            return repo.findByClienteAndAtendente(clienteId, atendenteId, p);
        } else if (clienteId!=0){
            return repo.findByCliente(clienteId, p);
        } else if (atendenteId!=0){
            return repo.findByAtendente(atendenteId, p);
        }
        return repo.findAll(p).toList();
    }
    
    public Chamado findById(Long id){
        Optional<Chamado> result = repo.findById(id);
        if(result.isEmpty()){
            throw new NotFoundException("Chamado não encontrado pelo ID");
        }
        return result.get();
    }
    
    public Chamado save(Chamado c){
        try {
            return repo.save(c);    
        } catch(Exception e){
            throw new RuntimeException("Falha ao salvar o Chamado.");
        }
    }
    
    public Chamado update(Chamado c){
        Chamado obj = findById(c.getId());
        c.setDescricao(obj.getDescricao());
        c.setDataAbertura(obj.getDataAbertura());
        c.setCliente(obj.getCliente());
        try {
            return repo.save(c);    
        } catch(Exception e){
            Throwable t = e;
            while (t.getCause() != null){
                t = t.getCause();
                if (t instanceof ConstraintViolationException){
                    throw ((ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Falha ao atualizar o Chamado.");
        }
    }
    
    // Chamado não pode ser excluído.
}
