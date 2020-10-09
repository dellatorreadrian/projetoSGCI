package br.edu.iff.projetoSGCI.model;

import java.util.List;

public class Cliente extends Pessoa {

    private String setor;

    private List<Chamado> chamados;

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
