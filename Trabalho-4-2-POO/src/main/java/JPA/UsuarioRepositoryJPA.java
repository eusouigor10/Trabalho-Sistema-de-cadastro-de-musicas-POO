package JPA;

import Entidades.Usuario;
import Repository.UsuarioRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;

@Stateless
public class UsuarioRepositoryJPA implements UsuarioRepository {
    
    private EntityManager em;

    @Override
    public void editarNome(Usuario usuario, String novoNome) {
        usuario.setNome(novoNome);
        em.merge(usuario); 
    }

    @Override
    public void alterarSenha(Usuario usuario, String novaSenha) {
        usuario.setSenha(novaSenha);
        em.merge(usuario); 
    }
}
