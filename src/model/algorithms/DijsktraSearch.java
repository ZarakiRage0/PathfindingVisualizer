package model.algorithms;

import model.GraphInterface;
import model.NodeInterface;
import model.util.FrontierException;
import model.util.Pair;
import model.util.PriorityQueue;

import java.util.HashMap;

public class DijsktraSearch implements PathFinderAlgo{
    private GraphInterface _graph;
    private boolean _hasEnded;
    private boolean _foundGoal;
    protected HashMap<NodeInterface,NodeInterface> _cameFrom;
    private PriorityQueue _frontier;
    private HashMap<NodeInterface, Integer> _costSoFar;

    @Override
    public void setUpAlgo( GraphInterface graph ) {
        _graph = graph;
        _hasEnded = false;
        _foundGoal = false;
        _cameFrom = new HashMap<>();
        _frontier = new PriorityQueue();
        _costSoFar = new HashMap<>();
        NodeInterface start = _graph.getStart();
        _frontier.put(new Pair(start,0));
        _cameFrom.put(start, start);
        _costSoFar.put(start, 0);
    }

    @Override
    public NodeInterface updateAlgo() {
        NodeInterface current = null;
        if (!(_frontier.isEmpty())) {
            try {
                current = _frontier.get().getNode();
            } catch (FrontierException e) {
                e.printStackTrace();
                System.exit(-1);
            }
            if (current.equals(_graph.getGoal())) {
                _foundGoal = true;
                _hasEnded = true;
            }
            for (NodeInterface neighbor:
                    _graph.getNeighbors(current)) {
                int newCost = _costSoFar.get(current) + _graph.getCost(current,neighbor);
                if ( !(_costSoFar.containsKey(neighbor)) || newCost < _costSoFar.get(neighbor)){
                    _costSoFar.put(neighbor, newCost);
                    _cameFrom.put(neighbor, current);
                    _frontier.put(new Pair(neighbor,newCost));
                }
            }
        } else {
            _hasEnded = true;
        }
        return current;
    }

    @Override
    public boolean hasSearchEnded() {
        return _hasEnded;
    }

    @Override
    public boolean hasFoundGoal() {
        return _foundGoal;
    }

    @Override
    public NodeInterface cameFrom( NodeInterface node ) {
        return _cameFrom.get(node);
    }
}
