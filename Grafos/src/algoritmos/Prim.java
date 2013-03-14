package algoritmos;

import estruturas_de_dados.Grafo;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Jansepetrus Brasileiro Pereira   -   11111976
 */
public class Prim {
    
    private static int custo = 0;
    /*
     * A interface Set<> representa uma coleção que não contém elementos duplicados.
     * Ou seja, não contém pares de elementos e1 e e2, de como que e1.equals(e2).
     */
    private static Set<Grafo.Aresta> caminho = null;
    /*
     * A interface List<> representa uma lista ordenada de objetos. Ou seja,
     * podemos acessar os elementos pela ordem ou pelo seu índice. Os elementos podem
     * constar de forma duplicada.
     */
    private static List<Grafo.Vertice> vertices = null;
    /*
     * A interface Queue<> é usada para enfileirar elementos, normalmente, em uma FIFO.
     * No caso de uma Priority Queue, os elementos são enfileirados em uma LIFO.
     */
    private static Queue<Grafo.Aresta> verticesDisponiveis = null;
    
    /**
     * Construtor
     */
    private Prim(){}
    
    /**
     * Método que retorna a Árvore de Custo Mínimo de um dado Grafo.
     * 
     * @param g         o Grafo.
     * @param inicio    o Vértice do qual se deseja partir.
     * 
     * @return          a Árvore de Custo Mínimo.
     */
    public static Grafo.CustoCaminho getArvoreGeradoraMinima(Grafo g, Grafo.Vertice inicio){
        /*
         * Tratamento de Exceções
         */
        if (g == null) {
            throw (new NullPointerException("Grafo não pode ser nulo."));
        }
        
        if (g.getTipo() == Grafo.TIPO.DIRECIONADO) {
            throw (new IllegalArgumentException("Funciona apenas para Grafos Não-Direcionados"));
        }
        //  Fim do Tratamento de Exceções
        
        caminho = new LinkedHashSet<Grafo.Aresta>();
        
        vertices = new ArrayList<Grafo.Vertice>();
        vertices.addAll(g.getVertices());
        vertices.remove(inicio);
        
        verticesDisponiveis = new PriorityQueue<Grafo.Aresta>();
        
        Grafo.Vertice v = inicio;                               //  Pego o vértice que desejo iniciar minha árvore,
        while (!vertices.isEmpty()){                            //  Enquanto a lista de vértices não estiver vazia,
            for (Grafo.Aresta e : v.getArestas()){              //  Faço iterações sobre as arestas do meu nó inicial
                                                                //(de análise)
                if (vertices.contains(e.getVerticeDeDestino())) //  Se minha lista contiver o vértice de destino
                    verticesDisponiveis.add(e);                 //  Adiciono à lista de vértices disponíveis
            }
            Grafo.Aresta e = verticesDisponiveis.remove();      //  Pego o vértice da lista
            custo = custo + e.getCusto();                       //  Adiciono o custo da aresta ao custo total
                                                                //da minha árvore
            caminho.add(e);                                     //  E adiciono a aresta ao Caminho
            
            v = e.getVerticeDeDestino();                        //  Vou para o próximo vértice de destino do meu nó
            vertices.remove(v);                                 //  Pego esse vértice e recomeço a iteração
        }
        
        return (new Grafo.CustoCaminho(custo, caminho));        //  Ao final, retorno a Árvore de Custo Mínimo
    }
}
