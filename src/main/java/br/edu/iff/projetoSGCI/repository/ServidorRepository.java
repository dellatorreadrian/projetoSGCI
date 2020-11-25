package br.edu.iff.projetoSGCI.repository;

import br.edu.iff.projetoSGCI.model.Servidor;
import br.edu.iff.projetoSGCI.model.StatusServidorEnum;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorRepository extends JpaRepository<Servidor, Long>{
    public Optional<Servidor> findByIp(String ip);
    public List<Servidor> findByStatus(StatusServidorEnum status);
    public Optional<Servidor> findByNome(String nome);
}
