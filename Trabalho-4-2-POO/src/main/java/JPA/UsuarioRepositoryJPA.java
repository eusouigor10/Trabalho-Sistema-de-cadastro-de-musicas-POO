package JPA;

import Entidades.Musica;
import Entidades.Usuario;
import Repository.UsuarioRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Stateless
public class UsuarioRepositoryJPA implements UsuarioRepository {

    @PersistenceContext(unitName = "BancoDeDadosProjetoFinal")
    private EntityManager em;

    @Override
    public Usuario criacaoUsuarioAdmin(Usuario usuario) {
        usuario.setNome("Admin");
        usuario.setLogin("admin");
        usuario.setSenha("admin");
        return usuario;
    }

    @Override
    public boolean verificacaoExistenciaUsuario(Usuario usuario) {
        if (em.contains(usuario)) {
            return true;
        } else {
            return false;
        }
    }

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

    @Override
    public Usuario buscarPorLogin(String login) {
        var lista = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.login = :login", Usuario.class)
                .setParameter("login", login)
                .getResultList();

        return lista.isEmpty() ? null : lista.get(0);
    }

    @Override
    public Usuario buscarPorNome(String nome) {
        return em.createQuery(
                "SELECT u FROM Usuario u WHERE u.nome = :nome", Usuario.class).setParameter("nome", nome)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void favoritar(Usuario usuario, Musica musica) {
        Usuario usuarioBanco = em.find(Usuario.class, usuario.getId());
        Musica musicaBanco = em.find(Musica.class, musica.getId());

        if (!usuarioBanco.getMusicasFavoritas().contains(musicaBanco)) {
            usuarioBanco.getMusicasFavoritas().add(musicaBanco);
            em.merge(usuarioBanco);
        }

        usuario.setMusicasFavoritas(usuarioBanco.getMusicasFavoritas());
    }

    @Override
    @Transactional
    public void desfavoritar(Usuario usuario, Musica musica) {
        Usuario usuarioBanco = em.find(Usuario.class, usuario.getId());
        Musica musicaBanco = em.find(Musica.class, musica.getId());

        if (usuarioBanco.getMusicasFavoritas().contains(musicaBanco)) {
            usuarioBanco.getMusicasFavoritas().remove(musicaBanco);
            em.merge(usuarioBanco);
        }

        usuario.setMusicasFavoritas(usuarioBanco.getMusicasFavoritas());
    }

    @Override
    public boolean verificarFavorita(Usuario usuario, Musica musica) {
        if (usuario.getMusicasFavoritas().contains(musica)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Usuario> listarTodos() {
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }

}
