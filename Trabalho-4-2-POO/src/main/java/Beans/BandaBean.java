package Beans;

import Entidades.Banda;
import Repository.BandaRepository;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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
    
    /*public String selecionamento(Banda banda){
        
    }*/
    
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
}
