package estruturas_de_dados;

/**
 * Classe que representa a estrutura das Arestas do Grafo
 *
 * @author Jansepetrus Brasileiro Pereira - 11111976
 */
public class Aresta implements Comparable<Aresta> {

    private Vertice origem = null;
    private Vertice destino = null;
    private int peso = 0;

    /**
     * Construtor da Classe
     *
     * @param origem Vértice inicial da aresta.
     * @param destino Vértice final da aresta.
     * @param peso Peso da Aresta.
     */
    public Aresta(Vertice origem, Vertice destino, int peso) {
        if (origem == null || destino == null) {
            throw (new NullPointerException("Os vértices de origem e destino devem ser não-nulos."));
        }

        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    /**
     * *************************************************************************
     * Métodos Get & Set
     * ************************************************************************
     */
    /**
     * Método que retorna o Peso de uma Aresta.
     *
     * @return O Peso da Aresta.
     */
    public int getPeso() {
        return peso;
    }

    /**
     * Método que retorna o Vértice de Origem da Aresta.
     *
     * @return O Vértice do qual a aresta parte.
     */
    public Vertice getVerticeDeOrigem() {
        return origem;
    }

    /**
     * Método que retorna o Vértice de Destino da Aresta.
     *
     * @return O Vértice no qual a aresta incide.
     */
    public Vertice getVerticeDeDestino() {
        return destino;
    }

    /**
     * *************************************************************************
     * Outros métodos
     * ************************************************************************
     */
    /**
     * Sobrescrita do Método toString().
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder();
        texto.append("(").append(this.origem.getNome()).append(" --[").append(this.getPeso())
             .append("]-->").append(this.destino.getNome()).append(") ");

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
        if (!(obj instanceof Aresta)) {
            return false;
        }

        Aresta e = (Aresta) obj;
        boolean pesos = (this.peso == e.peso);
        if (!pesos) {
            return false;
        }

        boolean origens = this.origem.equals(e.origem);
        if (!origens) {
            return false;
        }

        boolean destinos = this.destino.equals(e.destino);
        if (!destinos) {
            return false;
        }

        return true;
    }

    /**
     * Sobrescrita do Método compareTo().
     *
     * @param aresta
     * @return
     */
    @Override
    public int compareTo(Aresta e) {
        if (this.peso < e.getPeso()) {
            return -1;
        }
        if (this.peso > e.getPeso()) {
            return 1;
        }

        return 0;
    }
}