
package Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue
    private int id;
    
    private String nome;
    
    @Column(unique = true)
    
    private String login;
    private String senha;
    private boolean adm = false;
    private boolean logado = false;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isAdm() {
        return adm;
    }

    public void setAdm(boolean adm) {
        this.adm = adm;
    }
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Musica> musicasFavoritas; 

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public List<Musica> getMusicasFavoritas() {
        return musicasFavoritas;
    }

    public void setMusicasFavoritas(List<Musica> musicasFavoritas) {
        this.musicasFavoritas = musicasFavoritas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
