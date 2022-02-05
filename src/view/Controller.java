package view;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Controller {

    @FXML
    private Pane idMapGrid;

    @FXML
    public void initialize() {
        int widthMap = 1240;
        int heightMAp = 420;
        int size = 20;
        System.out.println(widthMap);
        System.out.println(heightMAp);
        for(int i = 0; i < heightMAp; i+=size){
            for (int j = 0; j < widthMap; j+=size){
                Rectangle rectangle = new Rectangle(size,size);
                rectangle.setFill(null);
                rectangle.setX(j);
                rectangle.setY(i);
                rectangle.setStroke(Color.BLACK);
                idMapGrid.getChildren().add(rectangle);
            }
        }
    }
}
