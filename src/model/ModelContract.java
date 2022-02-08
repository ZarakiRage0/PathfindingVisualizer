package model;

import model.algorithms.PathFinderAlgo;

public interface ModelContract {
    void updateGraph(int x, int y, NodeType nodeType);
    void clear();
    void search();
    void changeAlgo( PathFinderAlgo newAlgo);
}
