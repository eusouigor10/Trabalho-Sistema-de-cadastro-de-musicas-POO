package Beans;

import Entidades.Banda;
import Entidades.Genero;
import Entidades.Musica;
import Repository.BandaRepository;
import Repository.GeneroRepository;
import Repository.MusicaRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("musicaBean")
@SessionScoped
public class MusicaBean implements Serializable {

    @Inject
    private MusicaRepository musicaRepository;

    @Inject
    private GeneroRepository generoRepository;

    @Inject
    private BandaRepository bandaRepository;

    private Genero genero;
    private Banda banda;
    private String nome;
    private List<Musica> listaFiltrada;
    
    private String nomeSelecionado;
    private String generoSelecionado;
    private String bandaSelecionada;

    public String cadastrarMusica() {
        Musica musica = new Musica();
        
        banda = bandaRepository.buscarPorNomeUnico(bandaSelecionada);
        genero = generoRepository.buscarPorNomeUnico(generoSelecionado);
        
        musica.setBanda(banda);
        musica.setGenero(genero);
        musica.setNome(nome);

        musicaRepository.cadastrar(musica);
        
        nome = "";
        return "MusicaJSF.xhtml";
    }
    
    public String irParaPaginaDeCadastroDeMusica(){
        return "CadastroMusicasJSF.xhtml";
    }

    public String getNomeSelecionado() {
        return nomeSelecionado;
    }

    public void setNomeSelecionado(String nomeSelecionado) {
        this.nomeSelecionado = nomeSelecionado;
    }

    public String getGeneroSelecionado() {
        return generoSelecionado;
    }

    public void setGeneroSelecionado(String generoSelecionado) {
        this.generoSelecionado = generoSelecionado;
    }

    public String getBandaSelecionada() {
        return bandaSelecionada;
    }

    public void setBandaSelecionada(String bandaSelecionada) {
        this.bandaSelecionada = bandaSelecionada;
    }

    public void filtrarPorNome() {
        if (nome == null || nome.isEmpty()) {
            listaFiltrada = musicaRepository.listar();
            return;
        }
        listaFiltrada = musicaRepository.buscarPorNome(nome);
    }
    
    public void filtrarPorBanda() {
        if (nome == null || nome.isEmpty()) {
            listaFiltrada = musicaRepository.listar();
            return;
        }
        listaFiltrada = musicaRepository.buscarPorBanda(banda);
    }
    
    public void filtrarPorGenero() {
        if (nome == null || nome.isEmpty()) {
            listaFiltrada = musicaRepository.listar();
            return;
        }
        listaFiltrada = musicaRepository.buscarPorGenero(genero);
    }

    public List<Musica> getListaFiltradaPorNome() {
        filtrarPorNome();
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Musica> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Musica> listarMusicas() {
        return this.musicaRepository.listar();
    }

    public List<Musica> buscarPorGenero(Genero genero) {
        return this.musicaRepository.buscarPorGenero(genero);
    }

    public List<Musica> buscarPorBanda(Banda banda) {
        return this.musicaRepository.buscarPorBanda(banda);
    }

    public void marcarMusicaFavorita(Musica musica) {
        this.musicaRepository.marcarFavorita(musica);
    }

    public void desmarcarMusicaFavorita(Musica musica) {
        this.musicaRepository.desmarcarFavorita(musica);
    }

    public void atualizarNomeMusica(Musica musica, String novoNome) {
        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new IllegalArgumentException("O novo nome não pode ser vazio.");
        }
        this.musicaRepository.editarNome(musica, novoNome);
    }

    public void atualizarGeneroMusica(Musica musica, Genero novoGenero) {
        this.musicaRepository.editarGenero(musica, novoGenero);
    }

    public void atualizarBandaMusica(Musica musica, Banda novaBanda) {
        this.musicaRepository.editarBanda(musica, novaBanda);
    }

    public void removerMusica(Musica musica) {
        this.musicaRepository.removerMusica(musica);
    }
}
