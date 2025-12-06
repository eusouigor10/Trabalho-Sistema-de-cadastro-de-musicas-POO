package JPA;

import Entidades.Banda;
import Entidades.Genero;
import Entidades.Musica;
import Repository.MusicaRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;

@Stateless
public class MusicaRepositoryJPA implements MusicaRepository {

    @PersistenceContext(unitName = "BancoDeDadosProjetoFinal")
    private EntityManager em;

    @Override
    public List<Musica> listar() {
        TypedQuery<Musica> query = em.createQuery("SELECT m FROM Musica m", Musica.class);
        return query.getResultList();
    }
    
    @Override
    public List<Musica> buscarPorNome(String nome) {
        return em.createQuery("SELECT m FROM Musica m WHERE LOWER(m.nome) LIKE :nome", Musica.class)
                .setParameter("nome", nome.toLowerCase() + "%").getResultList();
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
    @Transactional
    public void cadastrar(Musica musica) {
        em.persist(musica);
    }

    @Override
    @Transactional
    public void marcarFavorita(Musica musica) {
        musica.setFavorita(true);
        em.merge(musica);
    }

    @Override
    @Transactional
    public void desmarcarFavorita(Musica musica) {
        musica.setFavorita(false);
        em.merge(musica);
    }

    @Override
    @Transactional
    public void editarNome(Musica musica, String novoNome) {
        musica.setNome(novoNome);
        em.merge(musica);
    }

    @Override
    @Transactional
    public void editarGenero(Musica musica, Genero novoGenero) {
        musica.setGenero(novoGenero);
        em.merge(musica);
    }

    @Override
    @Transactional
    public void editarBanda(Musica musica, Banda novaBanda) {
        musica.setBanda(novaBanda);
        em.merge(musica);
    }

    @Override
    @Transactional
    public void removerMusica(Musica musica) {
        Musica m = em.find(Musica.class, musica.getId());
        if (m != null) {
            em.remove(m);
        }
    }

}
