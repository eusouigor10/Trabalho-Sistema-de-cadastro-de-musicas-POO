
package Repository;

import Entidades.Banda;
import java.util.List;

public interface BandaRepository {
    
    public void cadastrar(Banda banda);
    
    public boolean remover(Banda banda);
    
    public List<Banda> listar();
    
    public void editarNome(Banda banda, String novoNome);

    public List<Banda> buscarPorNome(String nome);
}
