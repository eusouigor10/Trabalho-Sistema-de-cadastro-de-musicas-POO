
package Beans;

import Entidades.Usuario;
import JPA.UsuarioRepositoryJPA;
import jakarta.inject.Inject;

public class CadastroBean {
    
    @Inject
    UsuarioRepositoryJPA repository;
    
    private String nome;
    private String login;
    private String senha;
    private String senhaConfirmada;
    private String resultadoCadastro;
    private String mensagem;
 
    public String cadastrar(String nome, String login, String senha, String senhaConfirmada){
        Usuario usuario = new Usuario();
        
        if(senha.equals(senhaConfirmada)){
            usuario.setNome(nome);
            usuario.setLogin(login);
            usuario.setSenha(senha);
            repository.cadastrar(usuario);
            return "LoginJSG.xhtml";
        }else{
            mensagem = "Cadastro não concluído";
            return "";
        }
    }
}
