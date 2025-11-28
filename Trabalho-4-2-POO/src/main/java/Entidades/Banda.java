package Entidades;

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
    
    private String nome;
    
    @OneToMany(mappedBy = "banda")
    private Set<Musica> musicas;
}
