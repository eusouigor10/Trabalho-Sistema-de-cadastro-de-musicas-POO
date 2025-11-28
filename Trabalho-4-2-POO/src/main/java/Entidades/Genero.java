
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
    private Set<Musica> musica;
    
    
}
