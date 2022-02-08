package model;

public class Node implements NodeInterface{
    int _x;
    int _y;
    NodeType _nodeType;

    public Node(int x, int y, NodeType nodeType){
        _x = x;
        _y = y;
        _nodeType = nodeType;
    }

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    public NodeType getNodeType() {
        return _nodeType;
    }

    @Override
    public boolean isObstacle() {
        return _nodeType == NodeType.WALL;
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Node node = (Node) o;

        if (_x != node._x) {
            return false;
        }
        return _y == node._y;
    }

    public void changeNodeType( NodeType nodeType){
        _nodeType = nodeType;
    }
}
