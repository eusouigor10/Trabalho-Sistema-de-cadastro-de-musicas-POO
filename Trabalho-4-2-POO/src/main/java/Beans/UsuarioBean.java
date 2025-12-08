package Beans;

import Entidades.Usuario;
import Repository.UsuarioRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("usuarioBean")
@RequestScoped
public class UsuarioBean {

    private String novoNome;
    private String nomeAtual;
    private Usuario usuarioAtual;
    private String novaSenha;
    private String senhaAtual;
    private String mensagemSenha = "";
    boolean mostrarSenha = false;

    @Inject
    private UsuarioRepository usuarioRepository;
    
    @Inject
    private LoginBean loginBean;

    public void editarNome() {
        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new IllegalArgumentException("O novo nome não pode ser vazio.");
        }
        usuarioAtual = loginBean.getUsuarioLogado();
        this.usuarioRepository.editarNome(usuarioAtual, novoNome);
    }
    
    public String irParaPaginaDeMusicas(){
        return "MusicaJSF.xhtml";
    }

    public String getNomeAtual() {
        return loginBean.getUsuarioLogado().getNome();
    }

    public void setNomeAtual(String nomeAtual) {
        this.nomeAtual = nomeAtual;
    }
    
    public String getLogin(){
        return loginBean.getUsuarioLogado().getLogin();
    }
    
    public String getNovoNome() {
        return novoNome;
    }

    public void setNovoNome(String novoNome) {
        this.novoNome = novoNome;
    }

    public String alterarSenha() {
        usuarioAtual = loginBean.getUsuarioLogado();
        if (!senhaAtual.equals(usuarioAtual.getSenha()) || novaSenha.isEmpty()) {
            mensagemSenha = "As senhas não são iguais ou a nova senha está vazia";
            return "";
        } else {
            this.usuarioRepository.alterarSenha(usuarioAtual, novaSenha);
            mensagemSenha = "Senha alterada";
            return "";
        }
    }
    
    public void toggleMostrarSenha(){
        this.mostrarSenha = true;
    }

    public boolean isMostrarSenha() {
        return mostrarSenha;
    }

    public void setMostrarSenha(boolean mostrarSenha) {
        this.mostrarSenha = mostrarSenha;
    }
    
    public Usuario getUsuarioLogado(){
        return usuarioRepository.buscarPorNome(nomeAtual);
    }

    public String getMensagemSenha() {
        return mensagemSenha;
    }

    public void setMensagemSenha(String mensagemSenha) {
        this.mensagemSenha = mensagemSenha;
    }

    public String getSenhaAtual() {
        return loginBean.getUsuarioLogado().getSenha();
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public Usuario getUsuarioAtual() {
        return loginBean.getUsuarioLogado();
    }

    public void setUsuarioAtual(Usuario usuarioAtual) {
        this.usuarioAtual = usuarioAtual;
    }
}
