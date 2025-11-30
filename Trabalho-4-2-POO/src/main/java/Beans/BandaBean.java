package Beans;

import Entidades.Banda;
import Repository.BandaRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("bandaBean")
@SessionScoped
public class BandaBean implements Serializable {

    @Inject
    private BandaRepository repository;

    private String nome;
    private String novoNome;

    private Banda bandaSelecionada;
    private String resultadoRemocao;

    private boolean admin = true;

    private List<Banda> listaFiltrada = new ArrayList<>();

    @PostConstruct
    public void init() {
        listaFiltrada = repository.listar();
    }

    public void filtrar() {
        if (nome == null || nome.trim().isEmpty()) {
            listaFiltrada = repository.listar();
        } else {
            listaFiltrada = repository.buscarPorNome(nome);
        }
    }

    public String irParaCadastro() {
        nome = "";
        return "CadastrarBandaJSF?faces-redirect=true";
    }

    public String cadastrar() {
        Banda banda = new Banda();
        banda.setNome(nome);
        repository.cadastrar(banda);

        nome = "";
        listaFiltrada = repository.listar();

        return "BandaJSF?faces-redirect=true";
    }

    public String irParaEdicao(Banda banda) {
        bandaSelecionada = banda;
        novoNome = banda.getNome();
        return "EditarBandaJSF?faces-redirect=true";
    }

    public String salvarEdicao() {
        repository.editarNome(bandaSelecionada, novoNome);
        listaFiltrada = repository.listar();
        return "BandaJSF?faces-redirect=true";
    }

    public void remover(Banda banda) {
        if (!admin) {
            resultadoRemocao = "Apenas administradores podem remover bandas!";
            return;
        }

        boolean removida = repository.remover(banda);

        if (removida) {
            resultadoRemocao = "Banda removida com sucesso!";
        } else {
            resultadoRemocao = "Não é possível remover: a banda possui músicas cadastradas.";
        }

        listaFiltrada = repository.listar();
    }

    public String selecionar(Banda banda) {
        bandaSelecionada = banda;
        return "MusicasPorBandaJSF?faces-redirect=true";
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNovoNome() {
        return novoNome;
    }

    public void setNovoNome(String novoNome) {
        this.novoNome = novoNome;
    }

    public Banda getBandaSelecionada() {
        return bandaSelecionada;
    }

    public List<Banda> getListaFiltrada() {
        return listaFiltrada;
    }

    public String getResultadoRemocao() {
        return resultadoRemocao;
    }

    public void setResultadoRemocao(String resultadoRemocao) {
        this.resultadoRemocao = resultadoRemocao;
    }
}
