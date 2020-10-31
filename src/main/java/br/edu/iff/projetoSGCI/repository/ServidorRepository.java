package br.edu.iff.projetoSGCI.repository;

import br.edu.iff.projetoSGCI.model.Servidor;
import br.edu.iff.projetoSGCI.model.StatusServidorEnum;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorRepository extends JpaRepository<Servidor, Long>{
    public List<Servidor> findByIp(String ip);
    public List<Servidor> findByStatus(StatusServidorEnum status);
}
