package model;

import java.util.List;

public interface GraphInterface {
    NodeInterface getStart();
    NodeInterface getGoal();
    void initGraph();
    void updateNode( int i, int j, NodeType Node);
    void changeNode(int i, int j, NodeType nodeType);
    List<NodeInterface> getNeighbors( NodeInterface current);
    int getCost(NodeInterface from, NodeInterface to);
}
