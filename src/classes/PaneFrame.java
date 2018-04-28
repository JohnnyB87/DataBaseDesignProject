package classes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PaneFrame extends BorderPane {

    public PaneFrame(){
        this.setBackground(new Background(new BackgroundFill(Color.web("#df45df"),CornerRadii.EMPTY,Insets.EMPTY)));

        Button confirmButton = new Button("Confirm");
        Button quitButton = new Button("Quit");

        confirmButton.setMinWidth(75);
        quitButton.setMinWidth(75);

        HBox hBox = new HBox();
        hBox.setMinHeight(75);
        hBox.setAlignment(Pos.CENTER);
        hBox.setBackground(new Background(new BackgroundFill(Color.web("#111287"),CornerRadii.EMPTY,Insets.EMPTY)));

        hBox.setSpacing(50);

        hBox.getChildren().addAll(confirmButton,quitButton);

        setAlignment(hBox,Pos.CENTER);
        this.setBottom(hBox);

        quitButton.setOnAction(e->quitButtonPressed());
    }
    public void quitButtonPressed() {
        Stage stage = (Stage)this.getScene().getWindow();
        stage.close();
    }

    public void confirmButtonPressed() {
    }

}
