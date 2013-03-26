package algoritmos;

import estruturas_de_dados.Grafo;
import estruturas_de_dados.Vertice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementação do Algoritmo de Dijsktra para Cálculo do Menor Caminho
 *
 * @author Jansepetrus Brasileiro Pereira - 11111976
 */
public class Dijkstra {

    List<Vertice> menorCaminho = new ArrayList<Vertice>();
    Vertice verticeCaminho = new Vertice();
    Vertice atual = new Vertice();
    Vertice vizinho = new Vertice();
    List<Vertice> naoVisitados = new ArrayList<Vertice>();
    int custo = 0;

    public List<Vertice> getMenorCaminho(Grafo grafo, Vertice inicio, Vertice fim) {
        /*
         * Tratamento de Exceções
         */
        if (grafo == null) {
            throw (new NullPointerException("Grafo não pode ser nulo."));
        }

        if (grafo.temArestaNegativa()) {
            throw (new IllegalArgumentException("Não é permitido ter Arestas de custo negativo."));
        }
        /*
         * Fim do Tratamento de Exceções
         */

        // Adiciona a origem na lista do menor caminho
        menorCaminho.add(inicio);

        // Colocando a distancias iniciais
        for (int i = 0; i < grafo.getVertices().size(); i++) {

            // Vertice atual tem distancia zero, e todos os outros, 9999("infinita")
            if (grafo.getVertices().get(i).getNome().equals(inicio.getNome())) {
                grafo.getVertices().get(i).setValor(0);
            } else {
                grafo.getVertices().get(i).setValor(Integer.MAX_VALUE);
            }

            //Insere o vertice na lista de vertices nao visitados 
            this.naoVisitados.add(grafo.getVertices().get(i));
        }

        Collections.sort(naoVisitados);

        // O algoritmo continua ate que todos os vertices sejam visitados
        while (!this.naoVisitados.isEmpty()) {

            // Toma-se sempre o vertice com menor distancia, que eh o primeiro da
            // lista

            atual = this.naoVisitados.get(0);
//            System.out.println("Pegou esse vertice:  " + atual);
            /*
             * Para cada vizinho (cada aresta), calcula-se a sua possivel
             * distancia, somando a distancia do vertice atual com a da aresta
             * correspondente. Se essa distancia for menor que a distancia do
             * vizinho, esta eh atualizada.
             */
            for (int i = 0; i < atual.getArestas().size(); i++) {

                vizinho = atual.getArestas().get(i).getVerticeDeDestino();
//                System.out.println("Olhando o vizinho de " + atual + ": " + vizinho);
                if (!vizinho.isVisitado()) {

                    // Comparando a distância do vizinho com a possível
                    // distância
                    if (vizinho.getValor() > (atual.getValor() + atual.getArestas().get(i).getPeso())) {

                        vizinho.setValor(atual.getValor() + atual.getArestas().get(i).getPeso());
                        vizinho.setPai(atual);

                        /*
                         * Se o vizinho eh o vertice procurado, e foi feita uma
                         * mudanca na distancia, a lista com o menor caminho
                         * anterior eh apagada, pois existe um caminho menor
                         * vertices pais, ateh o vertice origem.
                         */
                        if (vizinho == fim) {
                            menorCaminho.clear();
                            verticeCaminho = vizinho;
                            menorCaminho.add(vizinho);
                            while (verticeCaminho.getPai() != null) {

                                menorCaminho.add(verticeCaminho.getPai());
                                verticeCaminho = verticeCaminho.getPai();

                            }
                            // Ordena a lista do menor caminho, para que ele
                            // seja exibido da origem ao destino.
                            Collections.sort(menorCaminho);

                        }
                    }
                }

            }
            // Marca o vertice atual como visitado e o retira da lista de nao visitados
            atual.visitar();
            this.naoVisitados.remove(atual);
            /*
             * Ordena a lista, para que o vertice com menor distancia
             * fique na primeira posicao
             */

            Collections.sort(naoVisitados);
//            System.out.println(naoVisitados);
        }
        
        return menorCaminho;
    }
}
