package Beans;

import Entidades.Genero;
import Repository.GeneroRepository;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
<<<<<<< Updated upstream
import java.io.Serializable;
import java.util.List;

@Named("generoBean")
@ViewScoped
public class GeneroBean implements Serializable{
=======
import java.util.List;

@Named("generoBean")
public class GeneroBean {
>>>>>>> Stashed changes

    @Inject
    private GeneroRepository generoRepository;
    
    private Genero genero = new Genero();
    private List<Genero> generos;
    private String nomeBusca;
    private String novoNome;

    public List<Genero> getGeneros() {
        if (generos == null) {
            generos = generoRepository.listar();
        }
        return generos;
    }

    public String cadastrar() {
        try {
            generoRepository.cadastrar(genero);
            genero = new Genero(); 
            generos = null;
            return "GeneroJSF.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 
    public String remover(Genero genero) {
        try {
            boolean removido = generoRepository.remover(genero);
            if (removido) {
                generos = null; 
            } else {
            }
            return null; 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public String buscar() {
        if (nomeBusca != null && !nomeBusca.trim().isEmpty()) {
            generos = generoRepository.buscarPorNome(nomeBusca);
        } else {
            generos = generoRepository.listar();
        }
        return null; 
    }
  
    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getNomeBusca() {
        return nomeBusca;
    }

    public void setNomeBusca(String nomeBusca) {
        this.nomeBusca = nomeBusca;
    }

    public String getNovoNome() {
        return novoNome;
    }

    public void setNovoNome(String novoNome) {
        this.novoNome = novoNome;
    }

}