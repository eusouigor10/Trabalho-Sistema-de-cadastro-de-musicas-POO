
package Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Musica {
    @Id
    @GeneratedValue
    private int id;
    
    private Genero genero;
    
    private Banda banda;
    
    @ManyToMany(mappedBy = "musicasFavoritas")
    private Set<Usuario> usuarios;
}
