package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CustomerWindow {

    @FXML
    private TableView tableView;
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



    public Label getTitleLabel() {
        return titleLabel;
    }
}
