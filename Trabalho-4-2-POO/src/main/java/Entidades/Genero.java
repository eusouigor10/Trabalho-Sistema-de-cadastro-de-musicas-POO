
package Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
public class Genero {
    @Id
    @GeneratedValue
    int id;

    String nome;

    @OneToMany(mappedBy = "genero")
    private List<Musica> musicas;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public List<Musica> getMusicas() { return musicas; }
    public void setMusica(List<Musica> musica) { this.musicas = musica; }
}

