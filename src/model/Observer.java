package model;

import java.util.List;

public interface Observer {
    abstract void update(NodeInterface node, NodeType nodeType);
    void update( List<NodeInterface> nodeList1, List<NodeInterface> nodeList2, NodeType nodeType, NodeType nodeType2 );
}
