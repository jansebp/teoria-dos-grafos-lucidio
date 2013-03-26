/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturas_de_dados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Janse
 */
public class Heap<E extends Comparable<E>> {

    List<E> heap = new ArrayList<E>();

    public Heap() {
    }

    public Heap(List<E> elementos) {
        for (E e : elementos) {
            heap.addAll(elementos);
        }
        for (int i = heap.size() / 2 - 1; i >= 0; i--) {
            percolateDown(i, heap.get(i));
        }
    }

    public Heap(E[] keys) {
        for (E key : keys) {
            heap.add(key);
        }
        for (int k = heap.size() / 2 - 1; k >= 0; k--) {
            percolateDown(k, heap.get(k));
        }
    }

    public void addElement(Aresta element) {
        
    }
    
    public void add(E node) {
        heap.add(null);
        int k = heap.size() - 1;
        while (k > 0) {
            int parent = (k - 1) / 2;
            E p = heap.get(parent);
            if (node.compareTo(p) >= 0) {
                break;
            }
            heap.set(k, p);
            k = parent;
        }
        heap.set(k, node);
    }

    public E deleteFirst() {
        return this.remove();
    }

    public E remove() {
        E removedNode = heap.get(0);
        E lastNode = heap.remove(heap.size() - 1);
        percolateDown(0, lastNode);
        return removedNode;
    }

    public E min() {
        return heap.get(0);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    void percolateDown(int k, E node) {
        if (heap.isEmpty()) {
            return;
        }
        while (k < heap.size() / 2) {
            int child = 2 * k + 1;
            if (child < heap.size() - 1 && heap.get(child).compareTo(heap.get(child + 1)) > 0) {
                child++;
            }
            if (node.compareTo(heap.get(child)) <= 0) {
                break;
            }
            heap.set(k, heap.get(child));
            k = child;
        }
        heap.set(k, node);
    }

    // Usage example
    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<Integer>(new Integer[]{2, 5, 1, 3});
        // print keys in sorted order
        while (!heap.isEmpty()) {
            System.out.println(heap.remove());
        }
    }
}
