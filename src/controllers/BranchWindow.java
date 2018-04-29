package controllers;

import classes.PaneFrame;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BranchWindow {

    @FXML
    private PaneFrame paneFrame;
    @FXML
    private Label titleLabel;
    @FXML
    private TextField streetTxtFld;
    @FXML
    private TextField countyTxtFld;
    @FXML
    private TextField cityTxtFld;
    @FXML
    private TextField contactTxtFld;
    private String name;
    private String contactNo;
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
}
