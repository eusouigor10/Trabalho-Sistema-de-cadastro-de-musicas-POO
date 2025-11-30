package Repository;

import Entidades.Banda;
import java.util.List;

public interface BandaRepository {

    void cadastrar(Banda banda);

    boolean remover(Banda banda);

    List<Banda> listar();

    void editarNome(Banda banda, String novoNome);

    List<Banda> buscarPorNome(String nome);

    Banda buscarPorId(int id);
}
