package Beans;

import Entidades.Usuario;
import Repository.UsuarioRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("boasVindasBean")
public class BoasVindasBean {

    @Inject
    UsuarioRepository repository;

    Usuario usuario;

    public void criarUsuarioAdmin() {
        usuario = repository.criacaoUsuarioAdmin(usuario);
    }

    public String fazerCadastro() {
        if (repository.verificacaoExistenciaUsuario(usuario)) {
            return "CadastroJSF.xhtml";
        } else {
            repository.cadastrar(usuario);
            return "CadastroJSF.xhtml";
        }
    }

    public String fazerLogin() {
        if (repository.verificacaoExistenciaUsuario(usuario)) {
            return "LoginJSF.xhtml";
        } else {
            repository.cadastrar(usuario);
            return "LoginJSF.xhtml";
        }
    }

}
