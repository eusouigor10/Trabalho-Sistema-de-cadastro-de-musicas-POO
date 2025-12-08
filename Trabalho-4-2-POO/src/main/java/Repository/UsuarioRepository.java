
package Repository;

import Entidades.Musica;
import Entidades.Usuario;

public interface UsuarioRepository {
    
    public void editarNome(Usuario usuario, String novoNome);
    
    public void alterarSenha(Usuario usuario, String novaSenha);
    
    public void cadastrar(Usuario usuario);
    
    public Usuario buscarPorLogin(String login);
    
    public Usuario criacaoUsuarioAdmin(Usuario usuario);
    
    public boolean verificacaoExistenciaUsuario(Usuario usuario);
    
    public Usuario buscarPorNome(String nome);
    
    public void favoritar(Usuario usuario, Musica musica);
    
    public void desfavoritar(Usuario usuario, Musica musica);
    
    public boolean verificarFavorita(Usuario usuario, Musica musica);
}
