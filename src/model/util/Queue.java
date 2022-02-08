package model.util;
import model.NodeInterface;

import java.util.ArrayList;
import java.util.List;

public class Queue extends Frontier<NodeInterface> {
    private List<NodeInterface> _array = new ArrayList<>();

    @Override
    public boolean isEmpty() {
        return _array.isEmpty();
    }

    @Override
    public void put( NodeInterface node ) {
        _array.add(node);
    }

    @Override
    public NodeInterface get() throws FrontierException {
        if ( isEmpty()){
            throw new FrontierException("PriorityQueue is empty.");
        }
        NodeInterface node = _array.get(0);
        _array.remove(0);
        return node;
    }

    @Override
    public void display() {
        for (NodeInterface node :
                _array) {
            System.out.print(node +" ");
        }
        System.out.print("\n");
    }
}
