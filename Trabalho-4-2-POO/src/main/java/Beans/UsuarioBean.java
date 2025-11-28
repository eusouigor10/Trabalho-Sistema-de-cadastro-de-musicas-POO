package Beans;

import Entidades.Usuario;
import Repository.UsuarioRepository;
import jakarta.inject.Inject; 
import jakarta.inject.Named;

@Named("usuarioBean")
public class UsuarioBean {
    
    @Inject
    private UsuarioRepository usuarioRepository;

    public void editarNome(Usuario usuario, String novoNome) {
        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new IllegalArgumentException("O novo nome não pode ser vazio.");
        }
        this.usuarioRepository.editarNome(usuario, novoNome);
    }

    public void alterarSenha(Usuario usuario, String novaSenha) {
        if (novaSenha == null || novaSenha.trim().length() < 6) {
            throw new IllegalArgumentException("A nova senha deve ter pelo menos 6 caracteres.");
        }
        
        this.usuarioRepository.alterarSenha(usuario, novaSenha);
    }
}