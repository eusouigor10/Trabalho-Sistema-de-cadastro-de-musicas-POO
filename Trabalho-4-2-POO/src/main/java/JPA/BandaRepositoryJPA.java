package JPA;

import Entidades.Banda;
import Repository.BandaRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Stateless
public class BandaRepositoryJPA implements BandaRepository {

    @PersistenceContext(unitName = "BancoDeDadosProjetoFinal")
    EntityManager em;

    @Override
    @Transactional
    public void cadastrar(Banda banda) {
        em.persist(banda);
    }

    @Override
    @Transactional
    public boolean remover(Banda banda) {

        Banda gerenciada = em.merge(banda);

        if (!gerenciada.getMusicas().isEmpty()) {
            return false;
        }

        em.remove(gerenciada);
        return true;
    }

    @Override
    public List<Banda> listar() {
        return em.createQuery("SELECT b FROM Banda b", Banda.class).getResultList();
    }

    @Override
    public List<Banda> buscarPorNome(String nome) {
        return em.createQuery(
                "SELECT b FROM Banda b WHERE LOWER(b.nome) LIKE :nome",
                Banda.class
        ).setParameter("nome", nome.toLowerCase() + "%")
         .getResultList();
    }
    
    @Override
    public Banda buscarPorNomeUnico(String nome) {
        return em.createQuery(
                "SELECT b FROM Banda b WHERE LOWER(b.nome) LIKE :nome",
                Banda.class
        ).setParameter("nome", nome.toLowerCase() + "%")
         .getSingleResult();
    }

    @Override
    @Transactional
    public void editarNome(Banda banda, String novoNome) {
        Banda b = em.merge(banda);
        b.setNome(novoNome);
    }

    @Override
    public Banda buscarPorId(int id) {
        return em.find(Banda.class, id);
    }
}
