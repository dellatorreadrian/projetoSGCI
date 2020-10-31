package br.edu.iff.projetoSGCI.repository;

import br.edu.iff.projetoSGCI.model.InformacaoHistorico;
import java.util.List;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InformacaoHistoricoRepository extends JpaRepository<InformacaoHistorico, Long> {
    @Query("SELECT DISTINCT(i) FROM Chamado c JOIN c.informacoesHistorico i WHERE c.id = :chamadoId")
    public List<InformacaoHistorico> findByChamadoId(Long chamadoId);
}
