package br.edu.iff.projetoSGCI.repository;

import br.edu.iff.projetoSGCI.model.Atendente;
import br.edu.iff.projetoSGCI.model.Chamado;
import br.edu.iff.projetoSGCI.model.Cliente;
import br.edu.iff.projetoSGCI.model.CriticidadeEnum;
import br.edu.iff.projetoSGCI.model.StatusChamadoEnum;
import java.util.Calendar;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long>{
    public List<Chamado> findByCriticidade(CriticidadeEnum criticidade, Pageable page);
    public List<Chamado> findByStatus(StatusChamadoEnum status, Pageable page);
    public List<Chamado> findByAtendente(Long atendenteId, Pageable page);
    public List<Chamado> findByCliente(Long clienteId, Pageable page);
    public List<Chamado> findByServidor(Long servidorId, Pageable page);
    public List<Chamado> findByClienteAndAtendente(Long clienteId, Long atendenteId, Pageable page);
    public List<Chamado> findByClienteAndAtendenteAndServidor(Long clienteId, Long atendenteId, Long servidorId, Pageable page);
    
    @Query("SELECT DISTINCT(c) FROM Chamado c WHERE (c.dataAbertura between :inicio AND :fim)")
    public List<Chamado> findChamadosAbertosEntreDatas(Calendar inicio, Calendar fim);
    
    @Query("SELECT DISTINCT(c) FROM Chamado c WHERE (c.dataEncerramento between :inicio AND :fim)")
    public List<Chamado> findChamadosFechadosEntreDatas(Calendar inicio, Calendar fim);
}
