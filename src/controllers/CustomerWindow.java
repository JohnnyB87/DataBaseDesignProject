package controllers;

import classes.PaneFrame;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class CustomerWindow {

    @FXML
    private PaneFrame paneFrame;
    @FXML
    private Label titleLabel;
    @FXML
    private TextField nameTxtFld;
    @FXML
    private TextField addressTxtFld;
    @FXML
    private TextField contactTxtFld;
    @FXML
    private ComboBox accountTypeComboBox;
    @FXML
    private ComboBox bNoComboBox;
    private Button confirmButton;

    @FXML
    private void initialize(){
        if(this.paneFrame != null) {
            this.confirmButton = paneFrame.getConfirmButton();
            this.confirmButton.setOnAction(e -> testButton());

//            Button testButton = new Button("Test");
//            testButton.setMinWidth(75);
//            HBox node = (HBox) this.paneFrame.getBottom();
//            node.getChildren().add(testButton);
//            node.setSpacing(20);
//            this.paneFrame.setBottom(node);
        }
    }

    private void testButton(){
        System.out.println("ACCOUNT CONFIRM BUTTON");
    }

    public Label getTitleLabel() {
        return titleLabel;
    }
}
