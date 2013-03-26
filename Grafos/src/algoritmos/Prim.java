package algoritmos;

import estruturas_de_dados.Aresta;
import estruturas_de_dados.Grafo;
import estruturas_de_dados.OrderedVector;
import estruturas_de_dados.Vertice;
import java.util.ArrayList;
import java.util.List;

/**
 * O Algoritmo de Prim é um algoritmo guloso empregado para encontrar uma Árvore
 * Geradora Mínima (MST) num grafo conectado, valorado e não direcionado. Ou
 * seja, o algoritmo encontra um subgrafo no qual a soma total das arestas é
 * "mínima" e todos os vértices estão interligados.
 *
 * @author Jansepetrus Brasileiro Pereira - 11111976
 */
public class Prim {

    /**
     * Método que retorna a Árvore Geradora de Custo Mínimo utilizando o
     * Algoritmo de Prim.
     *
     * @param grafo O Grafo que deseja ser analisado.
     * @param inicio O Vértice de início da análise.
     *
     * @return A lista de Arestas que representa a Árvore de Custo Mínimo.
     */
    public List<Aresta> getArvoreGeradoraMinima(Grafo grafo, Vertice inicio) {
        /*
         * Tratamento de Exceções.
         */
        if (grafo == null) {
            throw (new NullPointerException("Grafo não pode ser nulo."));
        }
        
        if (grafo.getTipo() == Grafo.TIPO.DIRECIONADO) {
            throw (new IllegalArgumentException("Funciona apenas para Grafos Não-Direcionados"));
        }
        //  Fim do Tratamento de Exceções

        
        Vertice u = inicio;

        /*  Implementar o HEAP!!!!!!!!  */
        OrderedVector arestas = new OrderedVector(u.getArestas());
        List<Vertice> T = new ArrayList<Vertice>();
        List<Aresta> S = new ArrayList<Aresta>();
        
        
        T.add(u);                                       //  Adiciono o primeiro vértice (inicial) à lista de vértices
        while (T.size() < grafo.getVertices().size()) { //  Enquanto houver vértices a serem percorridos...
            Aresta uv = arestas.deleteFirst();          //  removo o primeiro elemento da lista de arestas (do Heap)...
            S.add(uv);                                  //  Adiciono a aresta à lista de arestas...
            Vertice v = uv.getVerticeDeDestino();       //  "Pego" o próximo vértice ao qual o primeiro estava ligado...
            T.add(v);                                   //  Adiciono à lista de vértices...

            int indice = 0;
                                                        //  "Vejo" quem são os vizinhos desse vértice...
            for (Vertice w : v.getVizinhos()) {         //  Para cada vértice na vizinhança...
                Aresta vw = v.getArestas().get(indice); //  "Pego" a aresta que liga esse vértice aos seus vizinhos...
                if (T.contains(w)) {                    //  Verifico se já está na lista de vértices visitados...
                    arestas.remove(vw);                 //  Se estiver, eu removo essa aresta (não pode ter ciclos)...
                } else {                                //  Se não estiver...
                    arestas.addElement(vw);             //  ...Adiciono à lista de arestas...
                }
                indice++;                               //  Incremento o índice para verificar todos os vizinhos...
            }
        }
        
        return S;                                       //  Ao fim, retorno a lista de arestas que contém a MST.
    }
}
