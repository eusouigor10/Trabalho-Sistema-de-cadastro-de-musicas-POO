
package Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;

@Entity
public class Genero {
    @Id
    @GeneratedValue
    int id;
    
    String nome;
    
    @OneToMany(mappedBy = "genero")
    private Set<Musica> musicas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Musica> getMusicas() {
        return musicas;
    }

    public void setMusica(Set<Musica> musica) {
        this.musicas = musica;
    }
}
