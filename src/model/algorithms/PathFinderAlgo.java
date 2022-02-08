package model.algorithms;

import model.GraphInterface;
import model.NodeInterface;

public interface PathFinderAlgo {
    void setUpAlgo( GraphInterface graph );
    NodeInterface updateAlgo();
    boolean hasSearchEnded();
    boolean hasFoundGoal();
    NodeInterface cameFrom(NodeInterface node);
}
