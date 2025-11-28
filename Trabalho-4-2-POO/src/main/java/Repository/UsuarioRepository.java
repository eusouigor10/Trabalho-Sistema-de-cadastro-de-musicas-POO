
package Repository;

import Entidades.Usuario;

public interface UsuarioRepository {
    
    public void editarNome(Usuario usuario, String novoNome);
    
    public void alterarSenha(Usuario usuario, String novaSenha);
    
    public void cadastrar(Usuario usuario);
}
