
package Repository;

import Entidades.Banda;
import Entidades.Genero;
import Entidades.Musica;
import java.util.List;

public interface MusicaRepository {
    
    public List<Musica> listar();
    
    public List<Musica> buscarPorGenero();
    
    public List<Musica> buscarPorBanda();
    
    public void marcarFavorita(Musica musica);
    
    public void desmarcarFavorita(Musica musica);
    
    public void editarNome(Musica musica, String novoNome);
    
    public void editarGenero(Musica musica, Genero novoGenero);
    
    public void editarBanda(Musica musica, Banda novaBanda);
}
