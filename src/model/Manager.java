package model;

import model.algorithms.DijsktraSearch;
import model.algorithms.PathFinderAlgo;
import presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

public class Manager implements Subject,ModelContract{
    private GraphInterface _graph;
    private PathFinderAlgo _algorithm;
    private List<Observer> _observers = new ArrayList<Observer>();

    public Manager( Presenter observer, int width, int height ) {
        System.out.printf("Manager : (%d,%d)\n",width,height);
        _graph = new Graph(width, height, observer);
        _algorithm = new DijsktraSearch();
        _observers.add(observer);
    }

    @Override
    public void search() {
        Thread t = new Thread(){
            @Override
            public void run() {
                System.out.println("start search");
                NodeInterface visitedNode;
                List<NodeInterface> visitedList = new ArrayList<>();
                // init maybe
                _algorithm.setUpAlgo(_graph);
                //
                while( !(_algorithm.hasSearchEnded()) ){
                    visitedNode = _algorithm.updateAlgo();
                    if ( visitedNode == null){break;}
                    if (!(visitedNode.equals(_graph.getGoal())) && !(visitedNode.equals(_graph.getStart()))){
                        visitedList.add(visitedNode);
                    }
                }
                System.out.println("end search");
                List<NodeInterface> pathList = new ArrayList<>();
                NodeInterface start = _graph.getStart();
                if(_algorithm.hasFoundGoal()){
                    NodeInterface curent = _graph.getGoal();
                    do {
                        curent = _algorithm.cameFrom(curent);
                        if (!(curent.equals(_graph.getGoal())) && !(curent.equals(_graph.getStart()))){
                            pathList.add(curent);
                        }
                    } while (!curent.equals(start));
                }
                notifyObservers(visitedList,pathList,NodeType.VISITED,NodeType.PATH);
            }
        };
        t.start();



    }


    @Override
    public void updateGraph(int x, int y, NodeType nodeType) {
        _graph.updateNode(x, y, nodeType);
    }

    @Override
    public void clear() {
        _graph.initGraph();
    }

    @Override
    public void changeAlgo( PathFinderAlgo newAlgo) {

    }

    @Override
    public void addObserver( Observer observer ) {
        _observers.add(observer);
    }

    @Override
    public void notifyObservers( NodeInterface node, NodeType nodeType) {
        for (Observer observer : _observers) {
            observer.update(node,nodeType);
        }
    }

    @Override
    public void notifyObservers( List<NodeInterface> nodeList1, List<NodeInterface> nodeList2, NodeType nodeType, NodeType nodeType2  ) {
        for (Observer observer : _observers) {
            observer.update(nodeList1,nodeList2,nodeType,nodeType2);
        }
    }
}
