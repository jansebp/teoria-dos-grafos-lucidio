package estruturas_de_dados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe que implementa a estrutura do Grafo.
 * 
 * @author Jansepetrus Brasileiro Pereira   -   11111976
 */
public class Grafo {
    private List<Vertice>   vertices = new ArrayList<Vertice>();
    private List<Aresta>    arestas  = new ArrayList<Aresta>();

    public enum TIPO {DIRECIONADO, NAO_DIRECIONADO};
    private TIPO tipo = TIPO.NAO_DIRECIONADO;   /* Por padrão, crio um Grafo Não-Direcionado */

    /***************************************************************************
     * Construtores da Classe
     **************************************************************************/
    
    /**
     * Construtor Default.
     */
    public Grafo(){
    }

    /**
     * Construtor da Classe.
     * 
     * @param tipo Se o Grafo é do tipo Direcionado ou Não-Direcionado.
     */
    public Grafo(TIPO tipo){
        this();
        this.tipo = tipo;
    }
	
    
    /***************************************************************************
     * Métodos "Get & Set"
     **************************************************************************/
    
    /**
     * Método que retorna se um Grafo é Direcionado ou Não-Direcionado.
     * 
     * @return O tipo do Grafo.
     */
    public TIPO getTipo(){
        return this.tipo;
    }
    /**
     * Método que retorna os vértices do Grafo.
     * 
     * @return A lista de vértices do Grafo.
     */
    public List<Vertice> getVertices() {
        return vertices;
    }
    /**
     * Método que seta a lista de vértices do Grafo.
     * 
     * @param vertices A lista de vértices.
     */
    public void setVertices(List<Vertice> vertices) {
        this.vertices = vertices;
    }
    /**
     * Método que retorna as arestas do Grafo.
     * 
     * @return A lista de arestas do Grafo.
     */
    public List<Aresta> getArestas() {
        return arestas;
    }
    /**
     * Método que seta a lista de arestas do Grafo.
     * 
     * @param arestas A lista de arestas.
     */
    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }
    
    /***************************************************************************
     * Outros Métodos
     **************************************************************************/
    /**
     * Método que verifica se o Grafo possui Arestas de valor negativo.
     * @param listaVertices
     * @return 
     */
    public boolean temArestaNegativa() {
        List<Vertice> listaVertices = this.getVertices();
        for (Vertice v : listaVertices) {
            for (Aresta e : v.getArestas()) {
                if (e.getPeso() < 0) {
                    return true;
                }
            }
        }

        return false;
    }
    /**
     * Método para adicionar um vértice à lista de vértices do Grafo.
     * 
     * @param v O vértice a ser adicionado.
     */
    public void addVertice(Vertice v){
        this.vertices.add(v);
    }
    /**
     * Método para adicionar uma aresta à lista de arestas do Grafo.
     * 
     * @param a A aresta a ser adicionada.
     */
    public void addAresta(Aresta a){
        Vertice v1 = a.getVerticeDeOrigem();
        Vertice v2 = a.getVerticeDeDestino();
        
        v1.addVizinho(v2, a.getPeso());
        v2.addVizinho(v1, a.getPeso());
        
        this.arestas.add(a);
    }
    /**
     * Método que ordena as arestas do grafo
     */
    public void ordenarArestas(){
        Collections.sort(this.getArestas());
    }
    /**
     * Sobrescrita do Método toString()
     * 
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder();
        for (Vertice v : this.getVertices()){
            texto.append(v.toString()).append("\n");
        }
        
        return texto.toString();
    }
}
