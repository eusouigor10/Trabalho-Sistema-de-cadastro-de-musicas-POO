
package JPA;

import Entidades.Genero;
import Repository.GeneroRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

public class GeneroRepositoryJPA implements GeneroRepository{

    @PersistenceContext
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
        genero.setNome(novoNome);
        em.merge(genero);
    }

    @Override
    @Transactional
    public boolean remover(Genero genero) {
        if(genero.getMusicas().isEmpty()){
            return false;
        }else{
            em.remove(genero);
            return true;
        }
    }

    @Override
    public List<Genero> buscarPorNome(String nome) {
        return em.createQuery("SELECT g FROM Genero g WHERE LOWER(g.nome) LIKE :nome", Genero.class)
                .setParameter("nome", nome.toLowerCase() + "%").getResultList();
    }
    
}
