package presenter;

import javafx.scene.paint.Color;
import model.*;
import view.Controller;
import view.ViewContract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Presenter implements PresenterContract,Observer {
    private ViewContract _view;
    private ModelContract _model;
    static Map<NodeType, Color> _colorMap;
    static {
        _colorMap = new HashMap<>();
        _colorMap.put(NodeType.START, Color.RED);
        _colorMap.put(NodeType.GOAL, Color.GREEN);
        _colorMap.put(NodeType.EMPTY, Color.WHITE);
        _colorMap.put(NodeType.WALL, Color.BLACK);
        _colorMap.put(NodeType.VISITED, Color.BLUE);
        _colorMap.put(NodeType.PATH, Color.YELLOW);
    }

    public Presenter(ViewContract view, int width, int height){
        _view = view;
        System.out.printf("Presenter : (%d,%d)\n",width,height);
        _model = new Manager(this, width, height);
    }

    public void updateNode( int i, int j){
        //_view.updateNode(i,j,_colorMap.get(NodeType.WALL));
        _model.updateGraph(i,j,NodeType.WALL);
    }

    @Override
    public void startSearchAlgorithm() {
        _model.search();
    }

    @Override
    public void clear() {
        _model.clear();
    }

    @Override
    public void update( NodeInterface node, NodeType nodeType ) {
        int i = node.getY();
        int j = node.getX();
        Color color = _colorMap.get(nodeType);
        _view.updateNode(i,j,color);
    }

    @Override
    public void update( List<NodeInterface> nodeList1, List<NodeInterface> nodeList2, NodeType nodeType, NodeType nodeType2 ) {
        System.out.println("Start");
        for (NodeInterface node : nodeList1){
            int i = node.getY();
            int j = node.getX();
            Color color = _colorMap.get(nodeType);
            _view.updateNode(i,j,color);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (NodeInterface node : nodeList2){
            int i = node.getY();
            int j = node.getX();
            Color color = _colorMap.get(nodeType2);
            _view.updateNode(i,j,color);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
