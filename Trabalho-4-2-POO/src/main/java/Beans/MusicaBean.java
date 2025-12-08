package Beans;

import Entidades.Banda;
import Entidades.Genero;
import Entidades.Musica;
import Entidades.Usuario;
import Repository.BandaRepository;
import Repository.GeneroRepository;
import Repository.MusicaRepository;
import Repository.UsuarioRepository;
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
    
    @Inject
    private UsuarioRepository usuarioRepository;
    
    @Inject
    private LoginBean loginBean;

    private Genero genero;
    private Banda banda;
    private String nome;
    private List<Musica> listaFiltrada;
    private Usuario usuarioAtual;
    private List<Musica> listaFavoritas;
    
    private String nomeSelecionado;
    private String generoSelecionado;
    private String bandaSelecionada;
    
    private Musica musicaSelecionada;
    private String novoNome;
    private String novaBanda;
    private String novoGenero;
    private String mensagemBotaoFavorita;
    private String resultadoRemocao = "";

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

    public List<Usuario> getListaDeUsuarios() {
        return usuarioRepository.listarTodos();
    }
    
    public String irParaPaginaDeCadastroDeMusica(){
        return "CadastroMusicasJSF.xhtml";
    }

    public String getResultadoRemocao() {
        return resultadoRemocao;
    }

    public void setResultadoRemocao(String resultadoRemocao) {
        this.resultadoRemocao = resultadoRemocao;
    }
    
    public String getMensagemBotaoFavorita(Musica musica){
        usuarioAtual = loginBean.getUsuarioLogado();
        if(usuarioRepository.verificarFavorita(usuarioAtual, musica)){
            mensagemBotaoFavorita = "Desfavoritar";
            return mensagemBotaoFavorita;
        }else{
            mensagemBotaoFavorita = "Favoritar";
            return mensagemBotaoFavorita;
        }
    }
    
    public void acaoFavoritarOuDesfavoritar(Musica musica){
        usuarioAtual = loginBean.getUsuarioLogado();
        if(usuarioRepository.verificarFavorita(usuarioAtual, musica)){
            usuarioRepository.desfavoritar(usuarioAtual, musica);
        }else{
            usuarioRepository.favoritar(usuarioAtual, musica);
        }
        
        listaFavoritas = usuarioAtual.getMusicasFavoritas();
    }

    public Usuario getUsuarioAtual() {
        return usuarioAtual;
    }
    
    public void atualizarFavoritas(){
        listaFavoritas = loginBean.getUsuarioLogado().getMusicasFavoritas();
    }

    public void setUsuarioAtual(Usuario usuarioAtual) {
        this.usuarioAtual = usuarioAtual;
    }
    
    public void favoritar(Musica musica){
        usuarioAtual = loginBean.getUsuarioLogado();
        usuarioRepository.favoritar(usuarioAtual, musica);
        listaFavoritas = usuarioAtual.getMusicasFavoritas();
    }
    
    public void desfavoritar(Musica musica){
        usuarioAtual = loginBean.getUsuarioLogado();
        usuarioRepository.desfavoritar(usuarioAtual, musica);
        listaFavoritas = usuarioAtual.getMusicasFavoritas();
    }

    public List<Musica> getListaFavoritas() {
        return listaFavoritas;
    }

    public void setListaFavoritas(List<Musica> listaFavoritas) {
        this.listaFavoritas = listaFavoritas;
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
        for(Usuario u : getListaDeUsuarios()){
            if(u.getMusicasFavoritas().contains(musica)){
                resultadoRemocao = "Não foi possível remover a música pois ela é favorita de algum usuário";
                return;
            }
        }
        resultadoRemocao = "";
        this.musicaRepository.removerMusica(musica);
    }

    public Musica getMusicaSelecionada() {
        return musicaSelecionada;
    }

    public void setMusicaSelecionada(Musica musicaSelecionada) {
        this.musicaSelecionada = musicaSelecionada;
    }

    public String getNovoNome() {
        return novoNome;
    }

    public void setNovoNome(String novoNome) {
        this.novoNome = novoNome;
    }

    public String getNovaBanda() {
        return novaBanda;
    }

    public void setNovaBanda(String novaBanda) {
        this.novaBanda = novaBanda;
    }

    public String getNovoGenero() {
        return novoGenero;
    }

    public void setNovoGenero(String novoGenero) {
        this.novoGenero = novoGenero;
    }
    
    public String irParaPaginaDeEdicao(Musica musica) {
        this.musicaSelecionada = musica;
        this.novoNome = musica.getNome();
        this.novaBanda = musica.getBanda().getNome();
        this.novoGenero = musica.getGenero().getNome();

        return "EditarMusicasJSF.xhtml?faces-redirect=true";
    }
    
    public String salvarEdicao() {
        musicaSelecionada.setNome(novoNome);
        Banda b = bandaRepository.buscarPorNomeUnico(novaBanda);
        musicaSelecionada.setBanda(b);
        Genero g = generoRepository.buscarPorNomeUnico(novoGenero);
        musicaSelecionada.setGenero(g);
        musicaRepository.editar(musicaSelecionada);

        return "MusicaJSF.xhtml?faces-redirect=true";
    }
}
