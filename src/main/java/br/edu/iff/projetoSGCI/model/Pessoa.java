
package br.edu.iff.projetoSGCI.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Login obrigatório.")
    @Length(max=10, message = "Login deve possuir no máximo 10 caracteres.")
    @Column(nullable = false, length = 10, unique = true, updatable = false)
    private String login;
    
    @NotBlank(message = "Senha obrigatória.")
    @Length(min=8, message = "Senha deve possuir no mínimo 8 caracteres.")
    @Column(nullable = false)
    private String senha;
    
    @NotBlank(message = "Nome obrigatório.")
    @Length(max=50, message = "Nome deve possuir no máximo 50 caracteres.")
    @Column(nullable = false, length = 5, unique = true)
    private String nome;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
