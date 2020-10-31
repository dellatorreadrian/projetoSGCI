package br.edu.iff.projetoSGCI.model;

import br.edu.iff.projetoSGCI.annotation.DataChamadoValidation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@DataChamadoValidation
public class Chamado implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Descrição obrigatória.")
    @Length(max=350, message = "Descrição deve possuir no máximo 350 caracteres.")
    @Column(nullable = false, length = 350, updatable = false)
    private String descricao;

    @Length(max=350, message = "Conclusão deve possuir no máximo 10 caracteres.")
    @Column(length = 350, updatable = false)
    private String conclusao;
    
    @NotNull(message = "Status obrigatório.")
    @Column(nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private StatusChamadoEnum status;
    
    @NotNull(message = "Criticidade obrigatória.")
    @Column(nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    private CriticidadeEnum criticidade;
    
    @NotNull(message = "Prazo obrigatório.")
    @Column (nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar prazo;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Data de abertura obrigatória.")
    @Column (nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataAbertura;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataEncerramento;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "chamado_id")
    private List<InformacaoHistorico> informacoesHistorico  = new ArrayList<InformacaoHistorico>();
    
    @Valid
    @ManyToOne
    @NotNull(message = "Chamado deve possuir 1 Servidor.")
    private Servidor servidor;
    
    @Valid
    @NotNull(message = "Chamado deve possuir 1 Atendente.")
    @ManyToOne
    private Atendente atendente;
    
    @Valid
    @NotNull(message = "Chamado deve possuir 1 Cliente.")
    @ManyToOne
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

    public Calendar getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(Calendar dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
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
