package model.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue extends Frontier<Pair>{
    private List<Pair> _binary_heap = new ArrayList<>();

    private int getParent(int i){
        return (i-1)/2;
    }
    private int getLeftChild(int i){
        return 2*i + 1;
    }
    private int getRightChild(int i){
        return 2*i + 2;
    }
    private void min_heapify(int index){
        int left;
        int right;
        int smallest;
        int heap_size;

        heap_size = _binary_heap.size();
        left = getLeftChild(index);
        right = getRightChild(index);
        smallest = index;


        if (left < heap_size && _binary_heap.get(left).getPriority() < _binary_heap.get(smallest).getPriority()) {
            smallest = left;
        }

        if (right < heap_size && _binary_heap.get(right).getPriority() < _binary_heap.get(smallest).getPriority()) {
            smallest = right;
        }

        if (smallest != index) {
            Collections.swap(_binary_heap,index,smallest);
            min_heapify(smallest);
        }
    }

    public boolean isEmpty(){
        return _binary_heap.isEmpty();
    }

    public void put(Pair pair){
        _binary_heap.add(pair);
        int i = _binary_heap.size() - 1;
        while(i > 0 && _binary_heap.get(i).getPriority() < _binary_heap.get(getParent(i)).getPriority()) {
            Collections.swap(_binary_heap,i,getParent(i));
            i = getParent(i);
        }
    }

    public Pair get() throws FrontierException {
        Pair pair;
        if ( isEmpty()){
            throw new FrontierException("PriorityQueue is empty.");
        }
        pair = _binary_heap.get(0);
        if (_binary_heap.size() == 1) {
            _binary_heap.remove(0);
        } else {
            _binary_heap.set(0, _binary_heap.get(_binary_heap.size() - 1) );
            _binary_heap.remove(_binary_heap.size() - 1);
            min_heapify(0);
        }
        return pair;
    }

    public void display(){
        for (Pair pair:
                _binary_heap) {
            System.out.print(pair+" ");
        }
        System.out.print("\n");
    }
}
