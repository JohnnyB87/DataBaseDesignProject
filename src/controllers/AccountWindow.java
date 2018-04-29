package controllers;

import classes.PaneFrame;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AccountWindow {

    @FXML
    private PaneFrame paneFrame;
    @FXML
    private TableView tableView;
    @FXML
    private Label titleLabel;
    @FXML
    private TextField accountType;
    @FXML
    private TextField accountDescription;
    private Button confirmButton;

    @FXML
    private void initialize(){
        if(this.paneFrame != null) {
            this.confirmButton = paneFrame.getConfirmButton();
            this.confirmButton.setOnAction(e -> testButton());
        }
    }

    private void testButton(){
        System.out.println("ACCOUNT CONFIRM BUTTON");
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public void testConfirmButton(){

    }
}
