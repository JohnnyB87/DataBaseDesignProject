package controllers;

import classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;

import static javafx.scene.input.KeyCode.ENTER;

public class AccountController {

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
            this.con = Main.getCon();
            this.validator = new Validator();
            this.account = new Account();
            String action = Main.getButtonPressed();
            if(action.equalsIgnoreCase("Add")) {
                confirmButton.setOnAction(e -> testConfirmButton());
            }else{
                setColumns();
                fillTable();
                if(action.equalsIgnoreCase("Update")) {
                    this.tableView.getSelectionModel().setCellSelectionEnabled(true);
                    this.tableView.setEditable(true);
                    setColumnsEditable();
                    editCell();
                }
                else if(action.equalsIgnoreCase("Delete")){
                    this.tableView.setEditable(true);
                    confirmButton.setText("Delete");
                    confirmButton.setOnAction(e-> {
                        Account selectedItem = tableView.getSelectionModel().getSelectedItem();
                        tableView.getItems().remove(selectedItem);
                        SQLQuery query = new SQLQuery();
                        query.deleteQuery(con, tableName, selectedItem.getAccNo());
                    });
                }
            }
        }
    }

    private void editCell() {
        tableView.setOnKeyPressed(event -> {
            TablePosition<Account, ?> pos = tableView.getFocusModel().getFocusedCell() ;
            if (pos != null && event.getCode() == ENTER) {
                int row = pos.getRow();
                TableColumn<Account, ?> col = pos.getTableColumn();
                tableView.edit(row, col);
                col.setOnEditCommit(t ->
                {(
                        t.getTableView().getItems().get(t.getTablePosition().getRow()))
                        .editDetails(t.getTablePosition().getColumn(),(String)t.getNewValue());
                    Account rowData = t.getRowValue();
                    String newValue = (String)t.getNewValue();
                    SQLQuery query = new SQLQuery();
                    query.updateQuery(con,tableName,col.getText(),newValue,rowData.getAccNo());
                });
            }
        });
    }

    private void setColumns() {
        this.accNoCol.setCellValueFactory(new PropertyValueFactory<>("AccNo"));
        this.accTypeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        this.accDesCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
    }

    private void setColumnsEditable(){
        accTypeCol.setCellFactory(column -> EditCell.createStringEditCell());
        accDesCol.setCellFactory(column -> EditCell.createStringEditCell());
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(Label titleLabel) {
        this.titleLabel = titleLabel;
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
            sqlQuery.insertQuery(con, this.tableName, this.account.getAccNo(),
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
