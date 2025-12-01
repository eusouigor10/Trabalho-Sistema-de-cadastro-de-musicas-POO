
package Beans;

import Entidades.Usuario;
import JPA.UsuarioRepositoryJPA;
import Repository.UsuarioRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("cadastroBean")
@SessionScoped
public class CadastroBean implements Serializable{
    
    @Inject
    private UsuarioRepository repository;
    
    private String nome;
    private String login;
    private String senha;
    private String senhaConfirmada;
    private String resultadoCadastro;
    private String mensagem;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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

    public String getSenhaConfirmada() {
        return senhaConfirmada;
    }

    public void setSenhaConfirmada(String senhaConfirmada) {
        this.senhaConfirmada = senhaConfirmada;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
 
    public String cadastrar(){
        
        if(senha.equals(senhaConfirmada)){
            Usuario usuario = new Usuario();
            usuario.setLogin(login);
            usuario.setNome(nome);
            usuario.setSenha(senha);
            repository.cadastrar(usuario);
            return "LoginJSF.xhtml";
        }else{
            mensagem = "Cadastro não concluído";
            return "";
        }
    }
    
    public String trazerParaPagina(){
        return "CadastroJSF.xhtml";
    }
}
