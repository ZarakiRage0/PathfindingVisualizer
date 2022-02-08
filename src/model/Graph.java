package model;

import java.util.ArrayList;
import java.util.List;

public class Graph implements GraphInterface,Subject{
    private NodeInterface[][] _grid;
    private NodeInterface _start;
    private NodeInterface _goal;
    private List<Observer> _observers = new ArrayList<Observer>();
    private int _width;
    private int _height;

    public Graph( int width, int height, Observer observer ) {
        _grid = new Node[height][width];
        _width = width;
        _height = height;
        _observers.add(observer);
        initGraph();
    }


    @Override
    public NodeInterface getStart() {
        return _start;
    }

    @Override
    public NodeInterface getGoal() {
        return _goal;
    }

    @Override
    public void initGraph() {
        for (int i = 0; i < _height; i++){
            for (int j = 0; j < _width; j++){
                _grid[i][j] = new Node(j,i,NodeType.EMPTY);
                if ( i == _height/3 && j == _width/4){
                    changeNode(_height/3, _width/4,NodeType.START);
                    _start = _grid[_height/3][_width/4];
                }
                else if ( i == _height/3 && j == _width*3/4){
                    changeNode(_height/3, _width*3/4,NodeType.GOAL);
                    _goal = _grid[_height/3][_width*3/4];
                } else {
                    changeNode(i, j ,NodeType.EMPTY);
                }

            }
        }
    }

    public void changeNode(int i, int j, NodeType nodeType){
        _grid[i][j].changeNodeType(nodeType);
        notifyObservers(_grid[i][j],nodeType);
    }

    @Override
    public void updateNode( int i, int j, NodeType nodeType ) {
        switch(nodeType){
            case EMPTY:

                break;
            case START:
                if ( _start != null){
                    int startX = _start.getX();
                    int startY = _start.getY();
                    changeNode(startY,startX,NodeType.EMPTY);
                }
                changeNode(i,j,nodeType);
                _start = _grid[i][j];
                break;
            case WALL:
                if ( _grid[i][j].getNodeType() == NodeType.WALL){
                    changeNode(i,j,NodeType.EMPTY);
                } else if ( _grid[i][j].getNodeType() == NodeType.EMPTY){
                    changeNode(i,j,NodeType.WALL);
                }
                break;
            case GOAL:
                if ( _goal != null){
                    int startX = _goal.getX();
                    int startY = _goal.getY();
                    changeNode(startY,startX,NodeType.EMPTY);
                }
                changeNode(i,j,nodeType);
                _goal = _grid[i][j];
                break;
        }
    }

    @Override
    public List<NodeInterface> getNeighbors( NodeInterface current ) {
        List<NodeInterface> neighborsList = new ArrayList<>();
        int[] directionX = {1,0,0,-1};
        int[] directionY = {0,1,-1,0};
        int currentX = current.getX();
        int currentY = current.getY();
        for(int i = 0; i< directionY.length; i++){
            int x = directionX[i] + currentX;
            int y = directionY[i] + currentY;
            // check if out of bounds
            if ( x >= 0 && x < _width && y >= 0 && y < _height){
                NodeInterface neighbor = _grid[y][x];
                if (current.equals(neighbor) || neighbor.isObstacle()){
                    continue;
                }
                neighborsList.add(neighbor);
            }

        }
        return neighborsList;
    }

    @Override
    public int getCost( NodeInterface from, NodeInterface to ) {
        return 1;
    }

    @Override
    public void addObserver( Observer observer ) {
        _observers.add(observer);
    }

    @Override
    public void notifyObservers( NodeInterface node, NodeType nodeType ) {
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
