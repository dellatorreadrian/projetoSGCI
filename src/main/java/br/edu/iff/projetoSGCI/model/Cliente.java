package br.edu.iff.projetoSGCI.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Cliente extends Pessoa {

    @Column(nullable = false, length = 20)
    private String setor;

    private List<Chamado> chamados = new ArrayList<Chamado>();

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
    
    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
 
    
}
