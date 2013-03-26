package estruturas_de_dados;

import java.util.ArrayList;

/**
 * Classe que representa a estrutura dos Vértices do Grafo
 *
 * @author Jansepetrus Brasileiro Pereira - 11111976
 */
public class Vertice implements Comparable<Vertice> {

    private String nome;
    private ArrayList<Aresta> arestas = new ArrayList<Aresta>();
    private ArrayList<Vertice> vizinhanca = new ArrayList<Vertice>();
    private Vertice pai;
    private boolean visitado = false;
    private static int count;
    private int myId;
    private int valor;

    /**
     * Construtor Default da Classe.
     */
    public Vertice() {
        this.myId = ++count;
    }

    /**
     * Construtor da Classe
     *
     * @param nome Define um nome para o Vértice.
     */
    public Vertice(String nome) {
        this();
        this.nome = nome;
    }

    /**
     * *************************************************************************
     * Métodos Get & Set
     * ************************************************************************
     */
    /**
     * Método que retorna a Vizinhança de um Vértice. .
     *
     * @return A lista de vizinhos do Vértice.
     */
    public ArrayList<Vertice> getVizinhos() {
        return vizinhanca;
    }

    /**
     * Método que retorna o ID de um Vértice.
     *
     * @return O ID do Vértice.
     */
    public int getMyId() {
        return myId;
    }

    /**
     * Método que retorna o nome de um Vértice.
     *
     * @return O nome do Vértice.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método que retorna as Arestas do Grafo.
     *
     * @return A lista de Arestas do Grafo.
     */
    public ArrayList<Aresta> getArestas() {
        return arestas;
    }

    /**
     * Método que retorna o valor de um determinado Vértice com relação ao custo
     * da Aresta.
     *
     * @return O valor do Vértice.
     */
    public int getValor() {
        return this.valor;
    }

    /**
     * Método que seta o valor de um determinado Vértice.
     *
     * @param valor O valor a ser setado para o Vértice.
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * Método que seta o pai do Vértice.
     *
     * @param pai O pai do Vértice que você deseja que seja setado.
     */
    public void setPai(Vertice pai) {

        this.pai = pai;
    }
    /**
     * Método que retorna o pai do Vértice.
     * 
     * @return O pai do Vértice.
     */
    public Vertice getPai() {

        return this.pai;
    }

    /**
     * *************************************************************************
     * Outros métodos
     * ************************************************************************
     */
    /**
     * Método que adiciona um vizinho ao Vértice (com o peso da aresta que os
     * liga).
     *
     * @param vizinho O Vértice vizinho.
     * @param peso O Peso da Aresta que os liga.
     */
    public void addVizinho(Vertice vizinho, int peso) {
        Aresta a = new Aresta(this, vizinho, peso);
        this.vizinhanca.add(vizinho);
        this.arestas.add(a);
    }

    /**
     * Método que indica que o Vértice foi visitado.
     */
    public void visitar() {
        this.visitado = true;
    }

    /**
     * Método que "diz" se um Vértice já foi ou não visitado.
     *
     * @return <b>true</b> se o Vértice foi visitado. <b>false</b> se o Vértice
     * não tiver sido visitado.
     */
    public boolean isVisitado() {
        return visitado;
    }

    /**
     * Sobrescrita do Método compareTo()
     *
     * @param v
     * @return
     */
    @Override
    public int compareTo(Vertice v) {
        if (this.getValor() < v.getValor()) {
            return -1;
        }
        if (this.getValor() > v.getValor()) {
            return 1;
        }

        return 0;
    }

    /**
     * Sobrescrita do Método toString().
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder();
        texto.append(this.getNome()).append(": ");

        for (Aresta a : arestas) {
            texto.append(a.toString());
        }

        return texto.toString();
    }

    /**
     * Sobrescrita do Método equals().
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vertice)) {
            return false;
        }

        Vertice v = (Vertice) obj;

        boolean nomes = this.nome.equals(v.nome);
        if (!nomes) {
            return false;
        }

        boolean id = (v.myId == this.myId);
        if (!id) {
            return false;
        }

        boolean aresta = v.getArestas().equals(this.getArestas());
        if (!aresta) {
            return false;
        }

        return true;
    }
}
