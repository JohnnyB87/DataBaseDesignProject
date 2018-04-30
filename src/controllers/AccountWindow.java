package controllers;

import classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;

public class AccountWindow {

    @FXML
    private PaneFrame paneFrame;
    @FXML
    private Label titleLabel;
    @FXML
    private TableView<Account> tableView;
    @FXML
    private TableColumn<Account, String> accNoCol;
    @FXML
    private TableColumn<Account, String> accTypeCol;
    @FXML
    private TableColumn<Account, String> accDesCol;
    @FXML
    private TextField accountType;
    @FXML
    private TextField accountDescription;

    private Account account;
    private String tableName;
    private Validator validator;
    private Connection con;

    @FXML
    private void initialize(){
        if(this.paneFrame != null) {
            Button confirmButton = paneFrame.getConfirmButton();
            this.tableName = "account";
            con = Main.getCon();
            this.validator = new Validator();
            this.account = new Account();
            String action = this.titleLabel.getText().split(" ")[0];
            System.out.println("Action ::::   " + action + "   title::: " + this.titleLabel.getText());
            if(action.equalsIgnoreCase("Add")) {
                confirmButton.setOnAction(e -> testConfirmButton());
            }else{
                setColumns();
                fillTable();
                if(action.equalsIgnoreCase("Update")) {
                    this.tableView.setEditable(true);
                }
                else if(action.equalsIgnoreCase("Delete")){
                    this.tableView.setEditable(true);
                    confirmButton.setText("Delete");
                    confirmButton.setOnAction(e-> {
                        Object selectedItem = tableView.getSelectionModel().getSelectedItem();
                        tableView.getItems().remove(selectedItem);
                    });
                }
            }
        }
    }

    private void setColumns() {
        this.accNoCol.setCellValueFactory(new PropertyValueFactory<>("AccNo"));
        this.accTypeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        this.accDesCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    private void testConfirmButton(){
        validator = new Validator();
        validator.setCont(true);
        this.account.setType(validator.validateTextFieldInputString(this.accountType.getText()));
        this.account.setDescription(validator.validateTextFieldInputString(this.accountDescription.getText()));
        if(!validator.isCont()){
            System.out.println("Invalid data entered.");
        }
        else {
            this.account.setAccNo(validator.getNumber(con, this.tableName));
            System.out.println("Database connection established1");
            SQLQuery sqlQuery = new SQLQuery();
            sqlQuery.insertQuery(con, this.tableName,this.account.getAccNo(),
                    this.account.getType(),this.account.getDescription());
        }
        Stage s = (Stage)paneFrame.getScene().getWindow();
        s.close();
    }

    private void fillTable(){
        ObservableList<Account> ol = FXCollections.observableArrayList();
        Statement s;
        try {
            s = con.createStatement();
            //Simple Query
            ResultSet rs = s.executeQuery ("SELECT * FROM account");
            while (rs.next ())
            {
                int index = 1;
                Account a = new Account(rs.getString (index++),rs.getString(index++),rs.getString(index));
                ol.add(a);
            }
            this.tableView.setItems(ol);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
