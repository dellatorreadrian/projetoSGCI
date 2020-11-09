package br.edu.iff.projetoSGCI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties(value = "senha", allowGetters = false, allowSetters = true)
public class Atendente extends Pessoa{
    
    @NotNull(message = "Nivel obrigatório.")
    @Column(nullable = false, length = 6)
    @Enumerated(EnumType.STRING)
    private NivelAtendenteEnum nivel;
    
    @JsonIgnore
    @OneToMany(mappedBy = "atendente")
    private List<Chamado> chamados  = new ArrayList<Chamado>();

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    public NivelAtendenteEnum getNivel() {
        return nivel;
    }

    public void setNivel(NivelAtendenteEnum nivel) {
        this.nivel = nivel;
    }
    
}
