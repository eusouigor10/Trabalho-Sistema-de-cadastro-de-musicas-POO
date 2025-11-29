
package JPA;

import Entidades.Genero;
import Repository.GeneroRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Stateless
public class GeneroRepositoryJPA implements GeneroRepository{

    @PersistenceContext(unitName = "BancoDeDadosProjetoFinal")
    EntityManager em;
    
    @Override
    public List<Genero> listar() {
        return em.createQuery("SELECT g FROM Genero g", Genero.class).getResultList();
    }

    @Override
    @Transactional
    public void cadastrar(Genero genero) {
        em.persist(genero);
    }

    @Override
    @Transactional
    public void editarNome(Genero genero, String novoNome) {
        Genero g = em.find(Genero.class, genero.getId());

        if (g != null) {
         g.setNome(novoNome);
        }
    }


    @Override
    @Transactional
    public boolean remover(Genero genero) {

        genero = em.merge(genero); // garantir que está gerenciado

    // só remove se NÃO tiver músicas
        if (!genero.getMusicas().isEmpty()) {
            return false;
    }

        em.remove(genero);
        return true;
    }


    @Override
    public List<Genero> buscarPorNome(String nome) {
        return em.createQuery("SELECT g FROM Genero g WHERE LOWER(g.nome) LIKE :nome", Genero.class)
                .setParameter("nome", nome.toLowerCase() + "%").getResultList();
    }
    
}
