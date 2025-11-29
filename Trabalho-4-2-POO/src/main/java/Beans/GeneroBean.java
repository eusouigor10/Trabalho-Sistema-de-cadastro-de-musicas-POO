package Beans;

import Entidades.Genero;
import Entidades.Musica;
import Repository.GeneroRepository;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("generoBean")
@ViewScoped
public class GeneroBean implements Serializable {

    @Inject
    private GeneroRepository generoRepository;

    private Genero genero = new Genero();
    private List<Genero> generos;
    private String nome;
    private String novoNome;
    private String nomeCadastro;
    private List<Genero> listaFiltrada;
    private String mensagemResultadoCadastro = "";
    private Genero generoPassado;

    private Genero generoEditando;

    public List<Genero> getGeneros() {
        if (generos == null) {
            generos = generoRepository.listar();
        }
        return generos;
    }

    public String getMensagemResultadoCadastro() {
        return mensagemResultadoCadastro;
    }

    public List<Musica> listarMusicas(Genero genero) {
        return genero.getMusicas();
    }

    public void filtrar() {
        if (nome == null || nome.isEmpty()) {
            listaFiltrada = new ArrayList<>();
            return;
        }
        listaFiltrada = generoRepository.buscarPorNome(nome);
    }

    public String cadastrar() {
        try {
            Genero genero = new Genero();
            genero.setNome(nomeCadastro);

            generoRepository.cadastrar(genero);
            mensagemResultadoCadastro = "Cadastro concluído com sucesso!";

            nomeCadastro = "";          // limpa campo
            generos = null;    
            listaFiltrada = null; // força recarregar a tabela
            return "GeneroJSF.xhtml?faces-redirect=true";

        } catch (Exception e) {
            e.printStackTrace();
            mensagemResultadoCadastro = "Erro ao cadastrar gênero.";
            return null;
        }
    }

    public String enviarParaPaginaDeCadastro() {
        return "CadastroGenerosJSF.xhtml";
    }

    public Genero getGeneroPassado() {
        return generoPassado;
    }

    public String enviarParaPaginaDeMusicas(Genero genero) {
        generoPassado = genero;
        return "MusicasDoGeneroJSF.xhtml";
    }

    public String getNomeCadastro() {
        return nomeCadastro;
    }

    public void setNomeCadastro(String nomeCadastro) {
        this.nomeCadastro = nomeCadastro;
    }

    public String remover(Genero genero) {
        try {
            boolean removido = generoRepository.remover(genero);
            if (removido) {
                generos = null;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String buscar() {
        if (nome != null && !nome.trim().isEmpty()) {
            generos = generoRepository.buscarPorNome(nome);
        } else {
            generos = generoRepository.listar();
        }
        return null;
    }

    public Genero getGenero() {
        return genero;
    }

    public List<Genero> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Genero> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getNomeBusca() {
        return nome;
    }

    public void setNomeBusca(String nomeBusca) {
        this.nome = nomeBusca;
        filtrar();
    }

    public String getNovoNome() {
        return novoNome;
    }

    public void setNovoNome(String novoNome) {
        this.novoNome = novoNome;
    }

    public Genero getGeneroEditando() {
        return generoEditando;
    }

    public void setGeneroEditando(Genero generoEditando) {
        this.generoEditando = generoEditando;
    }

    public String enviarParaPaginaDeEdicao(Genero genero) {
        this.generoEditando = genero;
        this.novoNome = genero.getNome();
        return "EditarGeneroJSF.xhtml?faces-redirect=true";
    }

    public String salvarEdicaoGenero() {
        try {
            generoRepository.editarNome(generoEditando, novoNome);
            mensagemResultadoCadastro = "Gênero alterado com sucesso!";
            generos = null;
            return "GeneroJSF.xhtml?faces-redirect=true";
        } catch (Exception e) {
            mensagemResultadoCadastro = "Erro ao editar gênero.";
            return null;
        }
    }
}
