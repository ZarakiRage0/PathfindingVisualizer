package model.util;

import model.NodeInterface;

public class Pair {
    private double _priority;
    private NodeInterface _node;

    public Pair(double priority){
        _priority = priority;
    }

    public Pair( NodeInterface node, double priority){
        _priority = priority;
        _node = node;
    }

    public NodeInterface getNode(){
        return _node;
    }

    public double getPriority(){
        return _priority;
    }
}
