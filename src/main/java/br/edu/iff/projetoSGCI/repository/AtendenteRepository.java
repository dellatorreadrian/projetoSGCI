
package br.edu.iff.projetoSGCI.repository;

import br.edu.iff.projetoSGCI.model.Atendente;
import br.edu.iff.projetoSGCI.model.NivelAtendenteEnum;
import br.edu.iff.projetoSGCI.model.Pessoa;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendenteRepository extends JpaRepository<Atendente, Long>{
    public List<Pessoa> findByNivel(NivelAtendenteEnum nivel);
    public Optional<Pessoa> findByNome(String nome);
}
