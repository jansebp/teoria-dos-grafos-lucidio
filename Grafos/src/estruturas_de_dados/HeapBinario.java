package estruturas_de_dados;

/**
 * Heap é uma estrutura de dados que pode ser visto como uma árvore binária
 * completa. A árvore está completamente preenchida em todos os níveis exceto,
 * talvez, no último nível. A complexidade, ao realizar operações sobre um Heap,
 * é da ordem de 'log n', ou seja, a altura da árvore que representa o Heap.
 * 
 * Bibliografia:
 * http://www.slideshare.net/rodrigovmoraes/heap-8003774
 * http://pt.wikipedia.org/wiki/Heap
 * Outros slides e tutoriais
 * 
 * @author Jansepetrus Brasileiro Pereira   -   11111976
 */
public class HeapBinario {
    private No raiz = null;
    private int tam = 0;
    
    /*
     * Existem dois tipos de Heaps Binários: Heap Máximo e Heap Mínimo.
     * Heap Máximo: é aquele onde para todo nó 'i' diferente da raiz,
     *              PAI(i) >= i. Ou seja, o pai é sempre maior que seus filhos.
     * Heap Mínimo: é aquele onde para todo nó 'i' diferente da raiz,
     *              PAI(i) <= i. Ou seja, o pai é sempre menor que seus filhos.
     * Em outras palavras, em um Heap Máximo, o maior elemento está armazenado
     * na raiz, enquanto que em um Heap Mínimo, o maior elemento sempre estará
     * armazenado na raiz.
     */
    public enum TIPO {MIN,MAX};
    /*
     * De acordo com o solicitado, será utilizado um Heap Mínimo
     */
    private TIPO tipo = TIPO.MIN;
    
    public HeapBinario(){
        raiz = null;
        tam  = 0;
    }
    
    public HeapBinario(TIPO tipo){
        this();
        this.tipo = tipo;
    }
    
    public HeapBinario(int[] nos){
        this();
        popula(nos);
    }
    
    public HeapBinario(int[] nos, TIPO tipo){
        this(tipo);
        popula(nos);
    }
    
    private void popula(int[] nos){
        for (int no : nos){
            add(new No(null, no));
        }
    }
    
    private int[] getCaminhamento(int indice){
        int tamanho = (int)(Math.log10(indice + 1) / Math.log10(2)) - 1;    // log10(0) = -inf
        int[] caminhamento = null;
        
        if (tamanho > 0){
            caminhamento = new int[tamanho];
            int i = tamanho - 1;
            while (i >= 0){
                indice = (indice - 1)/2;
                caminhamento[i--] = (indice > 0 && indice % 2 == 0) ? 1 : 0;    // 0 indica que o caminhamento
                                                                            //deverá ser para a esquerda.
                                                                            // 1 indica que o caminhamento
                                                                            //deverá ser para a direita.
            }
        }
        
        return caminhamento;
    }
    
    public void add(int valor){
        add(new No(null, valor));
    }
    
    public void add(No novoNo){
        if (raiz == null){
            raiz = novoNo;
            tam++;
            return;
        }
        
        No no = raiz;
        int[] caminhamento = getCaminhamento(tam);
        if (caminhamento != null && caminhamento.length > 0){
            for (int c : caminhamento){
                if (c == 0){
                    no = no.noEsquerdo;
                } else {
                    no = no.noDireito;
                }
            }
        }
        if (no.noEsquerdo == null){
            no.noEsquerdo = novoNo;
        } else {
            no.noDireito = novoNo;
        }
        
        novoNo.noPai = no;
        tam++;
        heapUp(novoNo);
    }
    
    public void remove(int valor){
        int[] caminhamento = getCaminhamento(tam - 1);
        No ultimoNo = raiz;
        if (caminhamento != null && caminhamento.length > 0){
            for (int c : caminhamento){
                if (c == 0){
                    ultimoNo = ultimoNo.noEsquerdo;
                } else {
                    ultimoNo = ultimoNo.noDireito;
                }
            }
        }
        if (ultimoNo.noDireito != null){
            ultimoNo = ultimoNo.noDireito;
        } else {
            ultimoNo = ultimoNo.noEsquerdo;
        }
        
        if (ultimoNo == null)
            return;
        
        No noRemovido = getNo(raiz, valor);
        if (noRemovido == null)
            return;
        
        No paiDoUltimoNo = ultimoNo.noPai;
        if (paiDoUltimoNo != null){
            if (paiDoUltimoNo.noEsquerdo != null && paiDoUltimoNo.noEsquerdo.equals(ultimoNo)){
                paiDoUltimoNo.noEsquerdo = null;
            } else {
                paiDoUltimoNo.noDireito = null;
            }
        }
        
        if (ultimoNo.equals(noRemovido)){
            tam--;
        } else {
            No paiDoNoRemovido = noRemovido.noPai;
            if (paiDoNoRemovido != null){
                if (paiDoNoRemovido.noEsquerdo != null && paiDoNoRemovido.noEsquerdo.equals(noRemovido)){
                    paiDoNoRemovido.noEsquerdo = ultimoNo;
                } else {
                    paiDoNoRemovido.noDireito = ultimoNo;
                }
            } else {
                raiz = ultimoNo;
            }
            ultimoNo.noPai = paiDoNoRemovido;
            ultimoNo.noEsquerdo = noRemovido.noEsquerdo;
            if (ultimoNo.noEsquerdo != null){
                ultimoNo.noEsquerdo.noPai = ultimoNo;
            }
            ultimoNo.noDireito = noRemovido.noDireito;
            if (ultimoNo.noDireito != null){
                ultimoNo.noDireito.noPai = ultimoNo;
            }
            tam--;
            heapDown(raiz);
        }
    }
    
    private No getNo(No noInicial, int valor){
        No aux = null;
        
        if (noInicial != null && noInicial.valor == valor){
            aux = noInicial;
        } else if (noInicial != null && noInicial.valor != valor){
            No esquerda = noInicial.noEsquerdo;
            if (esquerda != null){
                aux = getNo(esquerda, valor);
                if (aux != null)
                    return aux;
            }
            No direita = noInicial.noDireito;
            if (direita != null){
                aux = getNo(direita, valor);
                if (aux != null)
                    return aux;
            }
        }
        
        return aux;
    }
    
    private void heapUp(No no){
        while (no != null){
            No pai = no.noPai;
            
            int aux = (tipo == TIPO.MIN) ? -1 : 1;
            if (pai != null && no.valor.compareTo(pai.valor) == aux){
                No avo = pai.noPai;
                No noEsquerdaDoPai = pai.noEsquerdo;
                No noDireitaDoPai = pai.noDireito;
                
                pai.noEsquerdo = no.noEsquerdo;
                if (pai.noEsquerdo != null)
                    pai.noEsquerdo.noPai = pai;
                
                pai.noDireito = no.noDireito;
                if (pai.noDireito != null)
                    pai.noDireito.noPai = pai;
                
                if (noEsquerdaDoPai != null && noEsquerdaDoPai.equals(no)){
                    no.noEsquerdo = pai;
                    no.noDireito = noDireitaDoPai;
                    if (noDireitaDoPai != null)
                        noDireitaDoPai.noPai = no;
                } else {
                    no.noDireito = pai;
                    no.noEsquerdo = noEsquerdaDoPai;
                    if (noEsquerdaDoPai != null)
                        noEsquerdaDoPai.noPai = no;
                }
                pai.noPai = no;
                
                if (avo == null){
                    no.noPai = null;
                    raiz = no;
                } else {
                    No noEsquerdaDoAvo = avo.noEsquerdo;
                    if(noEsquerdaDoAvo != null && noEsquerdaDoAvo.equals(pai)){
                        avo.noEsquerdo = no;
                    } else {
                        avo.noDireito = no;
                    }
                    no.noPai = avo;
                }
            } else {
                no = no.noPai;
            }
        }
    }
    
    private void heapDown(No no){
        heapUp(no);
        No esquerda = no.noEsquerdo;
        if (esquerda != null)
            heapDown(esquerda);
        No direita = no.noDireito;
        if (direita != null)
            heapDown(direita);
    }
    
    private void getValorNo(No no, int indice, int[] vetor){
        vetor[indice] = no.valor;
        indice = (indice * 2) + 1;
        
        No esquerda = no.noEsquerdo;
        if (esquerda != null)
            getValorNo(esquerda, indice, vetor);
        No direita = no.noDireito;
        if (direita != null)
            getValorNo(direita, indice + 1, vetor);
    }
    
    public int[] getHeap(){
        int[] nos = new int[tam];
        getValorNo(raiz, 0, nos);
        
        return nos;
    }
    
    public int getValorRaiz(){
        int aux = Integer.MIN_VALUE;
        if (raiz != null)
            aux = raiz.valor;
        
        return aux;
    }
    
    @Override
    public String toString(){
        StringBuilder texto = new StringBuilder();
        int[] heap = getHeap();
        for (int no : heap){
            texto.append(no).append(", ");
        }
        
        return texto.toString();
    }
    
    /**
     * Classe estática que manipula os nós que serão adicionados ao Heap.
     */
    private static class No {
        private Integer valor = null;
        private No noPai      = null;
        private No noEsquerdo = null;
        private No noDireito  = null;
        
        private No(No pai, int valor){
            this.noPai = pai;
            this.valor = valor;
        }
        
        public String toString(){
            return "\n Valor = "+valor+
                   "\n Nó Pai = "+((noPai != null)?noPai.valor:"NULL")+
                   "\n Nó da Esquerda = "+((noEsquerdo != null)?noEsquerdo.valor:"NULL")+
                   "\n Nó da Direita = "+((noDireito != null)?noDireito.valor:"NULL");
        }
    }
}
