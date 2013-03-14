package algoritmos;

import estruturas_de_dados.Grafo;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

/**
 * Implementação do Algoritmo de Dijsktra para Cálculo do Menor Caminho
 *
 * Bibliografia:
 * http://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 * http://programming-technique.blogspot.com.br/2012/01/implementation-of-dijkstras-shortest.html
 * 
 * @author Jansepetrus Brasileiro Pereira   -   11111976
 */
public class Dijsktra {
    /*
     * A interface Map<> é, basicamente, um conjunto de mapeamentos entre um objeto chave
     * e um objeto valor-associado, onde cada chave é única.
     */
    private static Map<Grafo.Vertice, Grafo.CustoVertice>   custos   = null;
    /*
     * A interface Set<> representa uma coleção que não contém elementos duplicados.
     * Ou seja, não contém pares de elementos e1 e e2, de como que e1.equals(e2).
     */
    private static Map<Grafo.Vertice, Set<Grafo.Aresta>>    caminhos = null;
    /*
     * A interface Queue<> é usada para enfileirar elementos, normalmente, em uma FIFO.
     * No caso de uma Priority Queue, os elementos são enfileirados em uma LIFO.
     */
    private static Queue<Grafo.CustoVertice>                vertices = null;
    
    /**
     * Construtor
     */
    private Dijsktra(){}
    
    public static Grafo.CustoCaminho getMenorCaminho(Grafo g, Grafo.Vertice inicio, Grafo.Vertice fim){
        /*
         * Tratamento de Exceções
         */
        if (g == null){
            throw (new NullPointerException("Grafo não pode ser nulo."));
        }
        
        boolean temArestaNegativa = checarArestaNegativa(g.getVertices());
        if(temArestaNegativa){
            throw (new IllegalArgumentException("Não é permitido ter Arestas de custo negativo."));
        }
        /*
         * Fim do Tratamento de Exceções
         */
        
        /*
         * Crio um mapeamento entre vértices e arestas, onde os vértices são os do meu Grafo.
         * Ou seja, o mapeamento representa um caminho.
         */
        caminhos = new TreeMap<Grafo.Vertice, Set<Grafo.Aresta>>();
        for (Grafo.Vertice v : g.getVertices()){
            caminhos.put(v, new LinkedHashSet<Grafo.Aresta>());
        }
        
        /*
         * Crio um mapeamento entre vértices e os custos.
         */
        custos = new TreeMap<Grafo.Vertice, Grafo.CustoVertice>();
        for (Grafo.Vertice v : g.getVertices()){
            if (v.equals(inicio)){
                custos.put(v, new Grafo.CustoVertice(0, v));
            } else {
                custos.put(v, new Grafo.CustoVertice(Integer.MAX_VALUE, v));
            }
        }
        
        /*
         * Crio uma fila de vértices disponíveis para caminhamento, e seus custos.
         */
        vertices = new PriorityQueue<Grafo.CustoVertice>();
        vertices.addAll(custos.values());
        
        
        Grafo.Vertice vertice = inicio;
        while (true){
            for (Grafo.Aresta e : vertice.getArestas()){                            //  Itero sobre as arestas do meu Grafo
                Grafo.CustoVertice par = custos.get(e.getVerticeDeDestino());       //  Um par custo-vértice definido baseado no vértice de destino do meu nó inicial
                Grafo.CustoVertice menorCustoDoVertice = custos.get(vertice);       //  Pego esse vértice de destino
                int custo = menorCustoDoVertice.getCusto() + e.getCusto();          //  Adiciono o valor do custo ao valor total
                if (par.getCusto() == Integer.MAX_VALUE){                           //  Se não tiver visitado esse vértice ainda
                    par.setCusto(custo);
                    Set<Grafo.Aresta> aux = caminhos.get(e.getVerticeDeDestino());
                    aux.addAll(caminhos.get(e.getVerticeDeOrigem()));
                    aux.add(e);
                } else if (custo < par.getCusto()){                                 //  Encontre o menor caminho para o vértice de destino
                    par.setCusto(custo);
                    Set<Grafo.Aresta> aux = caminhos.get(e.getVerticeDeDestino());
                    aux.clear();
                    aux.addAll(caminhos.get(e.getVerticeDeOrigem()));
                    aux.add(e);
                }
            }
            
            if (fim != null && vertice.equals(fim)){        //  Encontrou o menor caminho!
                break;
            } else if (vertices.size() > 0) {               //  Se houver mais vértices (que não foram visitados) 
                                                            //a visitar ainda
                Grafo.CustoVertice par = vertices.remove();
                vertice = par.getVertice();
                if (par.getCusto() == Integer.MAX_VALUE){   //  Único vértice restante não pode é acessível a partir deste
                    break;
                }
            } else {                                        //  Não há mais vértices para percorrer
                break;
            }
        }
        
        if (fim != null){
            Grafo.CustoVertice par = custos.get(fim);
            Set<Grafo.Aresta> aux = caminhos.get(fim);
            return (new Grafo.CustoCaminho(par.getCusto(), aux));
        }
        
        return null;
    }
    
    public static Map<Grafo.Vertice, Grafo.CustoCaminho> getMenorCaminho(Grafo g, Grafo.Vertice inicio){
        getMenorCaminho(g, inicio, null);
        Map<Grafo.Vertice, Grafo.CustoCaminho> mapa = new HashMap<Grafo.Vertice, Grafo.CustoCaminho>();
        for (Grafo.CustoVertice par : custos.values()){
            int custo = par.getCusto();
            Grafo.Vertice vertice = par.getVertice();
            Set<Grafo.Aresta> caminho = caminhos.get(vertice);
            mapa.put(vertice, new Grafo.CustoCaminho(custo, caminho));
        }
        
        return mapa;
    }
    
    private static boolean checarArestaNegativa(List<Grafo.Vertice> listaVertices){
        for (Grafo.Vertice v : listaVertices){
            for (Grafo.Aresta e : v.getArestas()){
                if (e.getCusto() < 0)
                    return true;
            }
        }
        
        return false;
    }
}
