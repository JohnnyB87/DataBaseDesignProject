package classes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PaneFrame extends BorderPane {

    private Button confirmButton;

    public PaneFrame(){
        this.confirmButton = new Button("Confirm");
        Button quitButton = new Button("Quit");

        this.confirmButton.setMinWidth(75);
        quitButton.setMinWidth(75);

        HBox hBox = new HBox();
        hBox.setMinHeight(75);
        hBox.setAlignment(Pos.CENTER);
        hBox.setBackground(new Background(new BackgroundFill(Color.web("#bcbcbc"),CornerRadii.EMPTY,Insets.EMPTY)));

        hBox.setSpacing(50);

        hBox.getChildren().addAll(this.confirmButton,quitButton);

        setAlignment(hBox,Pos.CENTER);
        this.setBottom(hBox);

        quitButton.setOnAction(e->quitButtonPressed());
    }

    public Button getConfirmButton() {
        return confirmButton;
    }

    public void setConfirmButtonText(String text) {
        this.confirmButton.setText(text);
    }

    private void quitButtonPressed() {
        Stage stage = (Stage)this.getScene().getWindow();
        stage.close();
    }

}
