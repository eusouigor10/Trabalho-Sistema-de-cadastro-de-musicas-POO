package Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;

@Entity
public class Banda {
    
    @Id
    @GeneratedValue
    private int id;
    
    @Column(unique = true)
    private String nome;
    
    @OneToMany(mappedBy = "banda")
    private Set<Musica> musicas;

    public Set<Musica> getMusicas() {
        return musicas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
