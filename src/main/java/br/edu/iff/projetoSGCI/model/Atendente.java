package br.edu.iff.projetoSGCI.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

@Entity
public class Atendente extends Pessoa{
    
    @Column(nullable = false, length = 6)
    @Enumerated(EnumType.STRING)
    private NivelAtendenteEnum nivel;
    
    @JsonBackReference
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
