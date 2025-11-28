package Beans;

import Entidades.Banda;
import Entidades.Genero;
import Entidades.Musica;
import Repository.MusicaRepository;
import java.util.List;

public class MusicaBean {

    private final MusicaRepository musicaRepository;

    public MusicaBean(MusicaRepository musicaRepository) {
        this.musicaRepository = musicaRepository;
    }

    public void cadastrarMusica(Musica musica) {
        if (musica.getNome() == null || musica.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da música não pode ser vazio.");
        }
        this.musicaRepository.cadastrar(musica);
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