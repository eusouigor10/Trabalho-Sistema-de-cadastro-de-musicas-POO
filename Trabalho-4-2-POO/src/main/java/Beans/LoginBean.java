package Beans;

import Entidades.Usuario;
import Repository.UsuarioRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable{
 
    @Inject
    private UsuarioRepository repository;
    
    private String login;
    private String senha;
    private String mensagem = "";

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public String fazerLogin(){
        Usuario usuarioAuxiliar = repository.buscarPorLogin(login);
        if(usuarioAuxiliar.getSenha().equals(senha)){
            repository.buscarPorLogin(login).setLogado(true);
            return "InicioJSF.xhtml";
        }else{
            mensagem = "Não foi possível efetuar o login";
            return "";
        }
    }
    
}
