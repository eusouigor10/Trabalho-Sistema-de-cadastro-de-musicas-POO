package JPA;

import Entidades.Usuario;
import Repository.UsuarioRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Stateless
public class UsuarioRepositoryJPA implements UsuarioRepository {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void editarNome(Usuario usuario, String novoNome) {
        usuario.setNome(novoNome);
        em.merge(usuario); 
    }

    @Override
    @Transactional
    public void alterarSenha(Usuario usuario, String novaSenha) {
        usuario.setSenha(novaSenha);
        em.merge(usuario); 
    }

    @Override
    @Transactional
    public void cadastrar(Usuario usuario) {
        em.persist(usuario);
    }
    
    
}
