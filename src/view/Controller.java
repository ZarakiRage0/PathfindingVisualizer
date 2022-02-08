package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import presenter.Presenter;

public class Controller implements ViewContract{
    private static final int widthMap = 1240;
    private static final int heightMAp = 420;
    private static final int size = 20;

    private Presenter _presenter;
    private Rectangle[][] _grid;
    @FXML
    private Pane idMapGrid;
    @FXML
    private Button idStart;
    @FXML
    private Button idClear;

    @FXML
    public void initialize() {
        idClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                onClearClick();
            }
        });
        idStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                onStartClick();
            }
        });
        _grid = new Rectangle[heightMAp/size][widthMap/size];
        for(int i = 0; i < heightMAp/size; i++){
            for (int j = 0; j < widthMap/size; j++){
                _grid[i][j] = new Rectangle(size,size);
                _grid[i][j].setFill(Color.GRAY);
                _grid[i][j].setX(j*size);
                _grid[i][j].setY(i*size);
                _grid[i][j].setStroke(Color.BLACK);
                idMapGrid.getChildren().add(_grid[i][j]);
                _grid[i][j].setOnMouseClicked(new RectangleMouseEvent(i,j));
            }
        }
        idMapGrid.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                double posX = t.getX();
                double posY = t.getY();
                int i = (int)posY/size;
                int j = (int)posX/size;
                if( i >= 0 && i < heightMAp/size && j >= 0 && j < widthMap/size ){
                    _presenter.updateNode(i,j);
                }

            }
        });
        _presenter = new Presenter(this, widthMap/size, heightMAp/size);
    }

    @Override
    public void updateNode( int i, int j, Color color){
        _grid[i][j].setFill(color);
    }

    public void onStartClick(){
        System.out.println("Starrt Click");
        _presenter.startSearchAlgorithm();
    }

    public void onClearClick(){
        _presenter.clear();
    }

    private class RectangleMouseEvent implements EventHandler<MouseEvent> {
        private int _i;
        private int _j;
        public RectangleMouseEvent(int i, int j){
            _i = i;
            _j = j;
        }
        @Override
        public void handle( MouseEvent event ) {
            _presenter.updateNode(_i,_j);
        }
    }
}


