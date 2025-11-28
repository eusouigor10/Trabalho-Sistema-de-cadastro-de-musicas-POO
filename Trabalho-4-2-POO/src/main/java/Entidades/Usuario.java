
package Entidades;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Set;

public class Usuario {
    
    @Id
    @GeneratedValue
    private int id;
    
    private String nome;
    private String login;
    private String senha;
    private boolean adm;
    
    private Set<Musica> musicasFavoritas;
}
