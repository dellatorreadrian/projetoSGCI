package br.edu.iff.projetoSGCI.repository;

import br.edu.iff.projetoSGCI.model.Cliente;
import br.edu.iff.projetoSGCI.model.Pessoa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    public List<Pessoa> findBySetor(String setor);
}
