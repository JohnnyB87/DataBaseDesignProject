package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AccountWindow {

    @FXML
    private TableView tableView;
    @FXML
    private Label titleLabel;
    @FXML
    private TextField accountType;
    @FXML
    private TextField accountDescription;

    public Label getTitleLabel() {
        return titleLabel;
    }
}
