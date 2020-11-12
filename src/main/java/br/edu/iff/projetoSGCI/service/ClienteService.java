package br.edu.iff.projetoSGCI.service;

import br.edu.iff.projetoSGCI.exception.NotFoundException;
import br.edu.iff.projetoSGCI.model.Cliente;
import br.edu.iff.projetoSGCI.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repo;
    
    public List<Cliente> findAll(){
        return repo.findAll();
    }
    
    public List<Cliente> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    
    public Cliente findById(Long id){
        Optional<Cliente> result = repo.findById(id);
        if(result.isEmpty()){
            throw new NotFoundException("Cliente não encontrado pelo ID");
        }
        return result.get();
    }
    
    public Cliente save(Cliente c){
        try {
            return repo.save(c);    
        } catch(Exception e){
            throw new RuntimeException("Falha ao salvar o Cliente.");
        }
    }
    
    public Cliente update(Cliente c, String senhaAtual, String novaSenha, String confirmarNovaSenha){
        Cliente obj = findById(c.getId());
        alterarSenha(obj,senhaAtual,novaSenha,confirmarNovaSenha);
        verificaExclusaoChamadosComClientes(obj);
        try {
            c.setLogin(obj.getLogin());
            c.setSenha(obj.getSenha());
            return repo.save(c);    
        } catch(Exception e){
            Throwable t = e;
            while (t.getCause() != null){
                t = t.getCause();
                if (t instanceof ConstraintViolationException){
                    throw ((ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Falha ao atualizar o Cliente.");
        }
    }
    
    public void delete(Long id){
        Cliente obj = findById(id);
        verificaExclusaoChamadosComClientes(obj);
        try {
            repo.delete(obj);    
        } catch(Exception e){
            throw new RuntimeException("Falha ao excluir o Cliente.");
        }
    }
    
    private void verificaExclusaoChamadosComClientes(Cliente c){
        if (!c.getChamados().isEmpty()){
            throw new RuntimeException("Não foi possível excluir o Cliente, pois existem Chamados relacionados a ele."); 
        }
    }
    
    private void alterarSenha(Cliente obj, String senhaAtual, String novaSenha, String confirmarNovaSenha){
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
