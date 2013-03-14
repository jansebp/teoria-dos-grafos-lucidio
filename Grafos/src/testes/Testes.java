package testes;

import algoritmos.Dijsktra;
import algoritmos.Prim;
import estruturas_de_dados.Grafo;
import estruturas_de_dados.Grafo.Aresta;
import estruturas_de_dados.Grafo.Vertice;
import estruturas_de_dados.HeapBinario;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Jansepetrus Brasileiro Pereira - 11111976
 */
public class Testes {

    private static int[] vetor = null;
    private static final int TAM = 20;

    public static void main(String[] args) {
        Random rnd = new Random(TAM);

        vetor = new int[TAM];
        int i = 0;
        while (i < vetor.length) {
            int j = rnd.nextInt(vetor.length * 10);
            vetor[i++] = j;
        }

        System.out.print("Vetor = [");
        for (i = 0; i < vetor.length; i++) {
            if (i != vetor.length - 1) {
                System.out.print(vetor[i] + ",");
            } else {
                System.out.print(vetor[i]);
            }
        }
        System.out.println("]\n");

        HeapBinario heapMin = new HeapBinario(vetor);
        System.out.println("HEAP Mínimo = " + heapMin.toString() + "\n");
        

        /*
         * Criação do meu Grafo Não-Direcionado
         */
        {
            System.out.println("Grafo Não-Direcionado");

            List<Vertice> vertices = new ArrayList<Vertice>();
            HeapBinario heap = heapMin;

            Grafo.Vertice v1 = new Grafo.Vertice(heap.getValorRaiz());
            heap.remove(heap.getValorRaiz());
            Grafo.Vertice v2 = new Grafo.Vertice(heap.getValorRaiz());
            heap.remove(heap.getValorRaiz());
            Grafo.Vertice v3 = new Grafo.Vertice(heap.getValorRaiz());
            heap.remove(heap.getValorRaiz());
            Grafo.Vertice v4 = new Grafo.Vertice(heap.getValorRaiz());
            heap.remove(heap.getValorRaiz());
            Grafo.Vertice v5 = new Grafo.Vertice(heap.getValorRaiz());
            heap.remove(heap.getValorRaiz());
            Grafo.Vertice v6 = new Grafo.Vertice(heap.getValorRaiz());
            heap.remove(heap.getValorRaiz());
            Grafo.Vertice v7 = new Grafo.Vertice(heap.getValorRaiz());
            heap.remove(heap.getValorRaiz());
            Grafo.Vertice v8 = new Grafo.Vertice(heap.getValorRaiz());
            heap.remove(heap.getValorRaiz());
            Grafo.Vertice v9 = new Grafo.Vertice(heap.getValorRaiz());
            heap.remove(heap.getValorRaiz());
            Grafo.Vertice v10 = new Grafo.Vertice(heap.getValorRaiz());
            heap.remove(heap.getValorRaiz());

            vertices.add(v1);
            vertices.add(v2);
            vertices.add(v3);
            vertices.add(v4);
            vertices.add(v5);
            vertices.add(v6);
            vertices.add(v7);
            vertices.add(v8);
            vertices.add(v9);
            vertices.add(v10);

            List<Aresta> arestas = new ArrayList<Aresta>();

            Grafo.Aresta a1_2 = new Grafo.Aresta(9, v1, v2);
            Grafo.Aresta a1_4 = new Grafo.Aresta(7, v1, v4);
            Grafo.Aresta a1_8 = new Grafo.Aresta(15, v1, v8);
            Grafo.Aresta a1_9 = new Grafo.Aresta(3, v1, v9);
            Grafo.Aresta a2_3 = new Grafo.Aresta(5, v2, v3);
            Grafo.Aresta a2_7 = new Grafo.Aresta(8, v2, v7);
            Grafo.Aresta a2_10 = new Grafo.Aresta(2, v2, v10);
            Grafo.Aresta a3_6 = new Grafo.Aresta(3, v3, v6);
            Grafo.Aresta a3_10 = new Grafo.Aresta(9, v3, v10);
            Grafo.Aresta a4_5 = new Grafo.Aresta(1, v4, v5);
            Grafo.Aresta a4_7 = new Grafo.Aresta(12, v4, v7);
            Grafo.Aresta a5_6 = new Grafo.Aresta(4, v5, v6);
            Grafo.Aresta a5_8 = new Grafo.Aresta(20, v5, v8);
            Grafo.Aresta a6_9 = new Grafo.Aresta(6, v6, v9);
            Grafo.Aresta a7_9 = new Grafo.Aresta(2, v7, v9);
            Grafo.Aresta a8_9 = new Grafo.Aresta(11, v8, v9);

            arestas.add(a1_2);
            arestas.add(a1_4);
            arestas.add(a1_8);
            arestas.add(a1_9);
            arestas.add(a2_3);
            arestas.add(a2_7);
            arestas.add(a2_10);
            arestas.add(a3_6);
            arestas.add(a3_10);
            arestas.add(a4_5);
            arestas.add(a4_7);
            arestas.add(a5_6);
            arestas.add(a5_8);
            arestas.add(a6_9);
            arestas.add(a7_9);
            arestas.add(a8_9);

            Grafo grafoND = new Grafo(vertices, arestas);
//            System.out.println(grafoND.toString());
            
            Grafo.Vertice inicio = v1;
//            System.out.println("Menor caminho (Dijsktra) partindo de " + inicio.getValor() + " para todos os nós do Grafo.\n");
//            Map<Grafo.Vertice, Grafo.CustoCaminho> mapa1 = Dijsktra.getMenorCaminho(grafoND, inicio);
//            System.out.println(getMapaCaminho(inicio, mapa));
            
            Grafo.Vertice fim = v4;
            System.out.println("Menor caminho (Dijsktra) partindo de " + inicio.getValor() + " para " + fim.getValor() + "\n");
            Grafo.CustoCaminho par1 = Dijsktra.getMenorCaminho(grafoND, inicio, fim);
            if (par1 != null)
                System.out.println(par1.toString());
            else
                System.out.println("Nenhum caminho encontrado partindo de " + inicio.getValor() + " em direção a " + fim.getValor() + "\n");
            
            System.out.println("Árvore de Custo Mínimo (Prim) partindo de " + inicio.getValor());
            Grafo.CustoCaminho par2 = Prim.getArvoreGeradoraMinima(grafoND, inicio);
            System.out.println(par2.toString());
            System.out.println();

        }


        /*
         * Criação do meu Grafo Direcionado
         */
        System.out.println("Grafo Direcionado");

//        List<Vertice> vertices = new ArrayList<Vertice>();
//
//        Grafo.Vertice v1 = new Grafo.Vertice(vetor[0]);
//        Grafo.Vertice v2 = new Grafo.Vertice(vetor[1]);
//        Grafo.Vertice v3 = new Grafo.Vertice(vetor[2]);
//        Grafo.Vertice v4 = new Grafo.Vertice(vetor[3]);
//        Grafo.Vertice v5 = new Grafo.Vertice(vetor[4]);
//        Grafo.Vertice v6 = new Grafo.Vertice(vetor[5]);
//        Grafo.Vertice v7 = new Grafo.Vertice(vetor[6]);
//        Grafo.Vertice v8 = new Grafo.Vertice(vetor[7]);
//        Grafo.Vertice v9 = new Grafo.Vertice(vetor[8]);
//        Grafo.Vertice v10 = new Grafo.Vertice(vetor[9]);
//
//        vertices.add(v1);
//        vertices.add(v2);
//        vertices.add(v3);
//        vertices.add(v4);
//        vertices.add(v5);
//        vertices.add(v6);
//        vertices.add(v7);
//        vertices.add(v8);
//        vertices.add(v9);
//        vertices.add(v10);
//
//        List<Aresta> arestas = new ArrayList<Aresta>();
//
//        Grafo.Aresta a1_2 = new Grafo.Aresta(9, v1, v2);
//        Grafo.Aresta a1_4 = new Grafo.Aresta(7, v1, v4);
//        Grafo.Aresta a1_8 = new Grafo.Aresta(15, v1, v8);
//        Grafo.Aresta a1_9 = new Grafo.Aresta(3, v1, v9);
//        Grafo.Aresta a2_3 = new Grafo.Aresta(5, v2, v3);
//        Grafo.Aresta a2_7 = new Grafo.Aresta(8, v2, v7);
//        Grafo.Aresta a2_10 = new Grafo.Aresta(2, v2, v10);
//        Grafo.Aresta a3_6 = new Grafo.Aresta(3, v3, v6);
//        Grafo.Aresta a3_10 = new Grafo.Aresta(9, v3, v10);
//        Grafo.Aresta a4_5 = new Grafo.Aresta(1, v4, v5);
//        Grafo.Aresta a4_7 = new Grafo.Aresta(12, v4, v7);
//        Grafo.Aresta a5_6 = new Grafo.Aresta(4, v5, v6);
//        Grafo.Aresta a5_8 = new Grafo.Aresta(20, v5, v8);
//        Grafo.Aresta a6_9 = new Grafo.Aresta(6, v6, v9);
//        Grafo.Aresta a7_9 = new Grafo.Aresta(2, v7, v9);
//        Grafo.Aresta a8_9 = new Grafo.Aresta(11, v8, v9);
//
//        arestas.add(a1_2);
//        arestas.add(a1_4);
//        arestas.add(a1_8);
//        arestas.add(a1_9);
//        arestas.add(a2_3);
//        arestas.add(a2_7);
//        arestas.add(a2_10);
//        arestas.add(a3_6);
//        arestas.add(a3_10);
//        arestas.add(a4_5);
//        arestas.add(a4_7);
//        arestas.add(a5_6);
//        arestas.add(a5_8);
//        arestas.add(a6_9);
//        arestas.add(a7_9);
//        arestas.add(a8_9);
//
//        Grafo grafoND = new Grafo(vertices, arestas);
//        System.out.println(grafoND.toString());
    }
    
    private static final String getMapaCaminho(Grafo.Vertice inicio, Map<Grafo.Vertice, Grafo.CustoCaminho> mapa){
        StringBuilder texto = new StringBuilder();
        for (Grafo.Vertice v : mapa.keySet()){
            Grafo.CustoCaminho par = mapa.get(v);
            texto.append("De ").append(inicio.getValor()).append(" para o Vértice ").append(v.getValor()).append("\n");
            if (par != null){
                texto.append(par.toString()).append("\n");
            }
        }

        return texto.toString();
    }
}
