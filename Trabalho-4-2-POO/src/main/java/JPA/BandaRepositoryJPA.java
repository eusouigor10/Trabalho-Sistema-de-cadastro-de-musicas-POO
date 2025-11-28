
package JPA;

import Entidades.Banda;
import Repository.BandaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

public class BandaRepositoryJPA implements BandaRepository{
    
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void cadastrar(Banda banda) {
        em.persist(banda);
    }

    @Override
    @Transactional
    public boolean remover(Banda banda) {
        if(banda.getMusicas().isEmpty()){
            return false;
        }else{
            em.remove(banda);
            return true;
        }
    }

    @Override
    public List<Banda> listar() {
        return em.createQuery("SELECT b FROM Banda b", Banda.class).getResultList();
    }
    
    @Override
    public List<Banda> buscarPorNome(String nome){
        return em.createQuery("SELECT b FROM Banda b WHERE LOWER(b.nome) LIKE :nome", Banda.class)
                .setParameter("nome", nome.toLowerCase() + "%").getResultList();
    }

    @Override
    @Transactional
    public void editarNome(Banda banda, String novoNome) {
        banda.setNome(novoNome);
        em.merge(banda);
    }
    
}
