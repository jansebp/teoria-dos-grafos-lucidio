package main;

import algoritmos.Dijkstra;
import algoritmos.Prim;
import estruturas_de_dados.Aresta;
import estruturas_de_dados.Grafo;
import estruturas_de_dados.Vertice;
import java.util.List;

/**
 * Classe da Aplicação
 *
 * @author Jansepetrus Brasileiro Pereira - 11111976
 */
public class Main {

    /**
     * Método inicial da Aplicação
     *
     * @param args Argumentos
     */
    public static void main(String args[]) {
        /**
         * *********************************************************************
         * Criação do meu Grafo Não-Direcionado.
         *********************************************************************
         */
        System.out.println("Grafo Não-Direcionado");

        Grafo grafo = new Grafo(Grafo.TIPO.NAO_DIRECIONADO);
        /*
         * Criação do Vértices.
         */
        Vertice a = new Vertice("A");
        Vertice b = new Vertice("B");
        Vertice c = new Vertice("C");
        Vertice d = new Vertice("D");
        Vertice e = new Vertice("E");
        Vertice f = new Vertice("F");
        Vertice g = new Vertice("G");
        /*
         * Adição dos Vértices ao Grafo.
         */
        grafo.addVertice(a);
        grafo.addVertice(b);
        grafo.addVertice(c);
        grafo.addVertice(d);
        grafo.addVertice(e);
        grafo.addVertice(f);
        grafo.addVertice(g);
        /*
         * Criação das Arestas.
         */
        Aresta ab = new Aresta(a, b, 7);
        Aresta ad = new Aresta(a, d, 5);
        Aresta bd = new Aresta(b, d, 9);
        Aresta bc = new Aresta(b, c, 8);
        Aresta be = new Aresta(b, e, 7);
        Aresta ce = new Aresta(c, e, 5);
        Aresta de = new Aresta(d, e, 15);
        Aresta df = new Aresta(d, f, 6);
        Aresta ef = new Aresta(e, f, 8);
        Aresta fg = new Aresta(f, g, 11);
        Aresta eg = new Aresta(e, g, 9);
        /*
         * Adição das Arestas ao Grafo.
         */
        grafo.addAresta(ab);
        grafo.addAresta(ad);
        grafo.addAresta(bd);
        grafo.addAresta(bc);
        grafo.addAresta(be);
        grafo.addAresta(ce);
        grafo.addAresta(de);
        grafo.addAresta(df);
        grafo.addAresta(ef);
        grafo.addAresta(fg);
        grafo.addAresta(eg);

        System.out.println(grafo);  /*  Impressão do Grafo. */

        Vertice inicio = a; /*  Definição do Vértice de Início. */
        Vertice fim    = g; /*  Definição do Vértice de Fim.    */

        /**
         * *********************************************************************
         * Algoritmo de Prim
         *********************************************************************
         */
        {
            System.out.println("Árvore de Custo Mínimo (Prim) partindo do Vértice " + inicio.getNome() + ":");
            System.out.println(new Prim().getArvoreGeradoraMinima(grafo, inicio) + "\n");
        }
        /**
         * *********************************************************************
         * Algoritmo de Dijkstra
         *********************************************************************
         */
        {
            List<Vertice> menorCaminho = new Dijkstra().getMenorCaminho(grafo, inicio, fim);

            StringBuilder texto = new StringBuilder();
            texto.append("Menor caminho (Dijsktra) partindo de ").append(inicio.getNome()).append(" para ").append(fim.getNome()).append(":\n");
            for (int i = 0; i < menorCaminho.size() - 1; i++) {
                texto.append(menorCaminho.get(i).getNome()).append(" -> ");
            }
            texto.append(fim.getNome() + "\nCusto do Caminho: " + menorCaminho.get(menorCaminho.size() - 1).getValor());
            System.out.println(texto);

        }
    }
}
