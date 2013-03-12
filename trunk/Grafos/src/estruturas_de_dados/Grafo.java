package estruturas_de_dados;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Jansepetrus Brasileiro   -   11111976
 * @author Ana Beatrice Severo      -   
 */
public class Grafo {
    private List<Vertice>   vertices = new ArrayList<Vertice>();
    private List<Aresta>    arestas  = new ArrayList<Aresta>();
    
    public enum TIPO {DIRECIONADO, NAO_DIRECIONADO};
    private TIPO tipo = TIPO.NAO_DIRECIONADO;
    
    public Grafo(){
    }
    
    public Grafo(TIPO tipo){
        this();
        this.tipo = tipo;
    }
    
    public Grafo(List<Vertice> vertices, List<Aresta> arestas){
        this(TIPO.NAO_DIRECIONADO, vertices, arestas);
    }
    
    public Grafo(TIPO tipo, List<Vertice> vertices, List<Aresta> arestas){
        this(tipo);
        this.vertices.addAll(vertices);
        this.arestas.addAll(arestas);
        
        for (Aresta e : arestas){
            Vertice origem = e.origem;
            Vertice destino = e.destino;
            
            if (!this.vertices.contains(origem) || !this.vertices.contains(destino)) continue;
            
            int indice = this.vertices.indexOf(origem);
            Vertice verticeDeOrigem = this.vertices.get(indice);
            indice = this.vertices.indexOf(destino);
            Vertice verticeDeDestino = this.vertices.get(indice);
            verticeDeOrigem.addAresta(e);
            if(this.tipo == TIPO.NAO_DIRECIONADO){
                Aresta loop = new Aresta (e.custo, verticeDeDestino, verticeDeOrigem);
                verticeDeDestino.addAresta(loop);
                this.arestas.add(loop);
            }
        }
    }
    
    public TIPO getTipo(){
        return tipo;
    }
    
    public List<Vertice> getVertices(){
        return vertices;
    }
    
    public List<Aresta> getArestas(){
        return arestas;
    }
    
    @Override
    public String toString(){
        StringBuilder texto = new StringBuilder();
        for (Vertice v : vertices){
            texto.append(v.toString());
        }
        
        return texto.toString();
    }
    
    public static class Vertice implements Comparable<Vertice>{
        private int valor = Integer.MIN_VALUE;
        private List<Aresta> arestas = new ArrayList<Aresta>();
        
        public Vertice(int valor){
            this.valor = valor;
        }
        
        public int getValor(){
            return valor;
        }
        
        public void addAresta(Aresta e){
            arestas.add(e);
        }
        
        public List<Aresta> getArestas(){
            return arestas;
        }
        
        public boolean caminhoPara(Vertice v){
            for (Aresta e : arestas){
                if(e.destino.equals(v))
                    return true;
            }
            return false;
        }
        
        @Override
        public int compareTo(Vertice v){
            if (this.valor < v.valor)
                return -1;
            if (this.valor > v.valor)
                return 1;
            
            return 0;
        }
        
        @Override
        public boolean equals(Object v1){
            if(!(v1 instanceof Vertice))
                return false;
            
            Vertice v = (Vertice)v1;
            
            boolean valores = this.valor == v.valor;
            if(!valores)
                return false;
            
            boolean arestas = v.getArestas().equals(this.getArestas());
            if(!arestas)
                return false;
            
            return true;
        }
        
        @Override
        public String toString(){
            StringBuilder texto = new StringBuilder();
            texto.append(valor).append("\n");
            for(Aresta e : arestas){
                texto.append("\t").append(e.toString());
            }
            
            return texto.toString();
        }
    }
    
    public static class Aresta implements Comparable<Aresta>{
        private Vertice origem = null;
        private Vertice destino = null;
        private int custo = 0;
        
        public Aresta(int custo, Vertice origem, Vertice destino){
            if (origem == null || destino == null)
                throw (new NullPointerException("Os vértices de origem e destino devem ser não-nulos."));
            
            this.custo = custo;
            this.origem = origem;
            this.destino = destino;
        }
        
        public int getCusto(){
            return custo;
        }
        
        public Vertice getVerticeDeOrigem(){
            return origem;
        }
        
        public Vertice getVerticeDeDestino(){
            return destino;
        }
        
        @Override
        public int compareTo(Aresta e){
            if(this.custo < e.custo)
                return -1;
            if(this.custo > e.custo)
                return 1;
            
            return 0;
        }
        
        @Override
        public boolean equals(Object e1){
            if(!(e1 instanceof Aresta))
                return false;
            
            Aresta e = (Aresta)e1;
            
            boolean custos = this.custo == e.custo;
            if(!custos)
                return false;
            
            boolean origens = this.origem.equals(e.origem);
            if(!origens)
                return false;
            
            boolean destinos = this.destino.equals(e.destino);
            if(!destinos)
                return false;
            
            return true;
        }
        
        public String toString(){
            StringBuilder texto = new StringBuilder();
            texto.append("[").append(origem.valor).append("]").append("->").append("[").append(destino.valor).append("]").append(" = ").append(custo).append("\n");
            
            return texto.toString();
        }
    }
    
    public static class CustoVertice implements Comparable<CustoVertice>{
        private int custo = Integer.MAX_VALUE;
        private Vertice vertice = null;
        
        public CustoVertice(int custo, Vertice vertice){
            if(vertice == null)
                throw (new NullPointerException("O vértice não pode ser nulo."));
            
            this.custo = custo;
            this.vertice = vertice;
        }
        
        public int getCusto(){
            return custo;
        }
        
        public void setCusto(int custo){
            this.custo = custo;
        }
        
        public Vertice getVertice(){
            return vertice;
        }
        
        @Override
        public int compareTo(CustoVertice cv){
            if(cv == null)
                throw (new NullPointerException("O custo do vértice não pode ser nulo."));
            
            if(this.custo < cv.custo)
                return -1;
            if(this.custo > cv.custo)
                return 1;
            
            return 0;
        }
        
        @Override
        public String toString(){
            StringBuilder texto = new StringBuilder();
            texto.append("Vértice = ").append(vertice.getValor()).append(" Custo = ").append(custo).append("\n");
            
            return texto.toString();
        }
    }
    
    public static class CustoCaminho{
        private int custo = 0;
        private Set<Aresta> caminho = null;
        
        public CustoCaminho(int custo, Set<Aresta> caminho){
            if(caminho == null)
                throw (new NullPointerException("O caminho não pode ser nulo"));
            
            this.custo = custo;
            this.caminho = caminho;
        }
        
        public int getCusto(){
            return custo;
        }
        
        public void setCusto(int custo){
            this.custo = custo;
        }
        
        public Set<Aresta> getCaminho(){
            return caminho;
        }
        
        public String toString(){
            StringBuilder texto = new StringBuilder();
            texto.append("Custo = ").append(custo).append("\n");
            for (Aresta e : caminho){
                texto.append("\t").append(e);
            }
            
            return texto.toString();
        }
    }
}
