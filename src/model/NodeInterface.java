package model;

public interface NodeInterface {
    boolean equals( Object o );
    void changeNodeType( NodeType nodeType);
    int getX();
    int getY();
    NodeType getNodeType();
    boolean isObstacle();
}
