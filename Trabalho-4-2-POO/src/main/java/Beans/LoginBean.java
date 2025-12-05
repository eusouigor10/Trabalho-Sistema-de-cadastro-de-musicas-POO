package Beans;

import Entidades.Usuario;
import Repository.UsuarioRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
    private UsuarioRepository repository;

    private String login;
    private String senha;
    private String mensagem = "";
    private Usuario usuarioLogado;

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

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String fazerLogin() {

        Usuario usuarioAuxiliar = repository.buscarPorLogin(login);

        if (usuarioAuxiliar == null) {
            mensagem = "Usuário não encontrado!";
            return null;
        }

        if (usuarioAuxiliar.getSenha().equals(senha)) {

            usuarioLogado = usuarioAuxiliar;
            usuarioLogado.setLogado(true);
            mensagem = "";

            return "InicioJSF?faces-redirect=true";
        }

        mensagem = "Senha incorreta!";
        return null;
    }

    public String trazerParaPagina() {
        return "LoginJSF?faces-redirect=true";
    }
    
    public String logout(){
        usuarioLogado = null;
        return "BoasVindasJSF?faces-redirect=true";
    }
}
