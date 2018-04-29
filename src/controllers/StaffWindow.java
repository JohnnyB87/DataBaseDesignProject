package controllers;

import classes.PaneFrame;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StaffWindow {

    @FXML
    private PaneFrame paneFrame;
    @FXML
    private Label titleLabel;
    @FXML
    private TextField nameTxtFld;
    @FXML
    private TextField contactTxtFld;
    @FXML
    private ComboBox bNoComboBox;
    @FXML
    private ComboBox posComboBox;
    private String name;
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

    public void setName(String name){
        this.name = name;
    }
}
