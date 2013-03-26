package algoritmos;

import estruturas_de_dados.Grafo;
import estruturas_de_dados.Vertice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementação do Algoritmo de Dijsktra para Cálculo do Menor Caminho
 * 
 * Bibliografia:    http://informatica.hsw.uol.com.br/algoritmos-de-roteamento3.htm
 *                  http://www.lcad.icmc.usp.br/~nonato/ED/Dijkstra/node84.html
 *                  http://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 *                  http://www.personal.kent.edu/~rmuhamma/Algorithms/MyAlgorithms/GraphAlgor/dijkstraAlgor.htm
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

        menorCaminho.add(inicio);   //  Adicionando o Vértice inicial à lista do Menor Caminho.

        /*  Deve-se colocar as distâncias (custos) iniciais de cada Vértice.
         * O Vértice atual possui distância zero (custo para chegar até ele é zero).
         * Todos os outros começam com distância infinita.
         */
        for (int i = 0; i < grafo.getVertices().size(); i++) {
            if (grafo.getVertices().get(i).getNome().equals(inicio.getNome())) {
                grafo.getVertices().get(i).setValor(0); //  Zero para o Vértice atual
            } else {
                grafo.getVertices().get(i).setValor(Integer.MAX_VALUE); //  Infinito para os outros
            }

            this.naoVisitados.add(grafo.getVertices().get(i));  //  Adiciona o Vértice atual à lista de Vértices não visitados.
        }

        Collections.sort(naoVisitados); //  Ordena a lista. DEVE SER UTILIZADO O HEAP! =/

        while (!this.naoVisitados.isEmpty()) {  //  Enquanto houver Vértice para ser visitado, continuar o algoritmo.
            /*
             * Nesse ponto, após a ordenação, o primeiro Vértice da lista é o de menor distância.
             */
            atual = this.naoVisitados.get(0);
//            System.out.println("Pegou o Vértice:  " + atual);
            /*
             * É calculada a distância do Vértice atual para cada vizinho (cada aresta),
             * somando-se a distância do Vértice atual com a da aresta correspondente.
             * No caso da distância ser menor que a distância do vizinho, o valor é atualizado.
             */
            for (int i = 0; i < atual.getArestas().size(); i++) {
                vizinho = atual.getArestas().get(i).getVerticeDeDestino();
//                System.out.println("Olhando o vizinho de " + atual + ": " + vizinho);
                if (!vizinho.isVisitado()) {
                    if (vizinho.getValor() > (atual.getValor() + atual.getArestas().get(i).getPeso())) {    //  Faz a comparação das distâncias, como citado mais acima
                        vizinho.setValor(atual.getValor() + atual.getArestas().get(i).getPeso());   //  No caso da distância menor, o valor é atualizado.
                        vizinho.setPai(atual);

                        if (vizinho == fim) {       //  Se o vizinho é o Vértice final (o que está sendo procurado),
                                                    //indica que a distância foi atualizada.
                            menorCaminho.clear();   //Portanto, a lista é apagada, já que existe um caminho menor.
                            verticeCaminho = vizinho;
                            menorCaminho.add(vizinho);
                            while (verticeCaminho.getPai() != null) {
                                menorCaminho.add(verticeCaminho.getPai());
                                verticeCaminho = verticeCaminho.getPai();

                            }

                            Collections.sort(menorCaminho); //  Ordena a lista de menor caminho.
                        }
                    }
                }

            }
            atual.visitar();    //  Indica que o Vértice atual foi visitado.
            this.naoVisitados.remove(atual);    //  Remove o vértice atual da lista de não visitados.

            Collections.sort(naoVisitados); //  Ordena a lista de Vértices não visitados, de forma que o de menor
                                            //distância fique na primeira posição.
//            System.out.println(naoVisitados);
        }

        return menorCaminho;
    }
}
