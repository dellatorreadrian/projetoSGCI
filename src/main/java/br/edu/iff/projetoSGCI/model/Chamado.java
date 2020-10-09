package br.edu.iff.projetoSGCI.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Chamado implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String descricao, conclusao;
    private StatusChamadoEnum status;
    private CriticidadeEnum criticidade;
    private Calendar prazo, dataAbertura;
    
    private List<InformacaoHistorico> informacoesHistorico;
    private Servidor servidor;
    private Atendente atendente;
    private Cliente cliente;

    public List<InformacaoHistorico> getInformacoesHistorico() {
        return informacoesHistorico;
    }

    public void setInformacoesHistorico(List<InformacaoHistorico> informacoesHistorico) {
        this.informacoesHistorico = informacoesHistorico;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public CriticidadeEnum getCriticidade() {
        return criticidade;
    }

    public void setCriticidade(CriticidadeEnum criticidade) {
        this.criticidade = criticidade;
    }

    public StatusChamadoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusChamadoEnum status) {
        this.status = status;
    }

    public String getConclusao() {
        return conclusao;
    }

    public void setConclusao(String conclusao) {
        this.conclusao = conclusao;
    }

    public Calendar getPrazo() {
        return prazo;
    }

    public void setPrazo(Calendar prazo) {
        this.prazo = prazo;
    }

    public Calendar getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Calendar dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Chamado other = (Chamado) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
