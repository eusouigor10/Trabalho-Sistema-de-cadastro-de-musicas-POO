
package Repository;

import Entidades.Genero;
import java.util.List;


public interface GeneroRepository {
    
    public List<Genero> listar();
    
    public void cadastrar(Genero genero);
    
    public void editarNome(Genero genero, String novoNome);
    
    public boolean remover(Genero genero);
    
    public List<Genero> buscarPorNome(String nome);
}
