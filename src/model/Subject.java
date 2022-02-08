package model;

import java.util.List;

public interface Subject {
    void addObserver(Observer observer);
    void notifyObservers(NodeInterface node, NodeType nodeType);
    void notifyObservers( List<NodeInterface> nodeList1, List<NodeInterface> nodeList2, NodeType nodeType, NodeType nodeType2 );
}
