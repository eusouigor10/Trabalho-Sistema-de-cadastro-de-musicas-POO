package JPA;

import Entidades.Banda;
import Entidades.Genero;
import Entidades.Musica;
import Repository.MusicaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class MusicaRepositoryJPA implements MusicaRepository {

    private EntityManager em;

    @Override
    public List<Musica> listar() {
        TypedQuery<Musica> query = em.createQuery("SELECT m FROM Musica m", Musica.class);
        return query.getResultList();
    }

    @Override
    public List<Musica> buscarPorGenero(Genero genero) {
        TypedQuery<Musica> query = em.createQuery(
                "SELECT m FROM Musica m WHERE m.genero = :genero", Musica.class);
        query.setParameter("genero", genero);
        return query.getResultList();
    }

    @Override
    public List<Musica> buscarPorBanda(Banda banda) {
        TypedQuery<Musica> query = em.createQuery(
                "SELECT m FROM Musica m WHERE m.banda = :banda", Musica.class);
        query.setParameter("banda", banda);
        return query.getResultList();
    }

    @Override
    public void cadastrar(Musica musica) {
        em.persist(musica);
    }

    @Override
    public void marcarFavorita(Musica musica) {
        musica.setFavorita(true);
        em.merge(musica);
    }

    @Override
    public void desmarcarFavorita(Musica musica) {
        musica.setFavorita(false);
        em.merge(musica);
    }

    @Override
    public void editarNome(Musica musica, String novoNome) {
        musica.setNome(novoNome);
        em.merge(musica);
    }

    @Override
    public void editarGenero(Musica musica, Genero novoGenero) {
        musica.setGenero(novoGenero);
        em.merge(musica);
    }

    @Override
    public void editarBanda(Musica musica, Banda novaBanda) {
        musica.setBanda(novaBanda);
        em.merge(musica);
    }

    @Override
    public void removerMusica(Musica musica) {
        Musica m = em.find(Musica.class, musica.getId());
        if (m != null) {
            em.remove(m);
        }
    }

}
