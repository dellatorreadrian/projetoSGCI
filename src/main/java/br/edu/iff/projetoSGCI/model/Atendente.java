package br.edu.iff.projetoSGCI.model;

import java.util.List;

public class Atendente extends Pessoa{
    
    private NivelAtendenteEnum nivel;
    
    private List<Chamado> chamados;

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
