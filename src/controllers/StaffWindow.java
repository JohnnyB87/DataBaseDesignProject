package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StaffWindow {

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

    public Label getTitleLabel() {
        return titleLabel;
    }

    public void setName(String name){
        this.name = name;
    }
}
