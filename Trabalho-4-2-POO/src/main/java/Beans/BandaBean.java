package Beans;

import Entidades.Banda;
import Repository.BandaRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("bandaBean")
public class BandaBean {

    @Inject
    private BandaRepository repository;

    private String nome;
    
    private String resultadoRemocao = null;

    private List<Banda> listaFiltrada = new ArrayList<>();

    public void filtrar() {
        if (nome == null || nome.isEmpty()) {
            listaFiltrada = new ArrayList<>();
            return;
        }

        listaFiltrada = repository.buscarPorNome(nome);

    }
    
    public void cadastrar(){
        Banda banda = new Banda();
        banda.setNome(nome);
        repository.cadastrar(banda);
    }
    
    public void editarNome(Banda banda, String novoNome){
        repository.editarNome(banda, novoNome);
    }
    
    public void remover(Banda banda){
        if(repository.remover(banda)){
            resultadoRemocao = "Remoção concluída";
        }else{
            resultadoRemocao = "Falha na remoção";
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BandaRepositoryJPA getRepository() {
        return repository;
    }

    public void setRepository(BandaRepositoryJPA repository) {
        this.repository = repository;
    }

    public String getResultadoRemocao() {
        return resultadoRemocao;
    }

    public void setResultadoRemocao(String resultadoRemocao) {
        this.resultadoRemocao = resultadoRemocao;
    }

    public List<Banda> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Banda> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    
}
