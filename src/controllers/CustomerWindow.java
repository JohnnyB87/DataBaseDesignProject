package controllers;

import classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static javafx.scene.control.cell.TextFieldTableCell.forTableColumn;
import static javafx.scene.input.KeyCode.C;
import static javafx.scene.input.KeyCode.ENTER;

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
    private ComboBox<String> bNoComboBox;
    @FXML
    private TableView<Customer> tableView;
    @FXML
    private TableColumn<Customer, String> cNoCol;
    @FXML
    private TableColumn<Customer, String> bNoCol;
    @FXML
    private TableColumn<Customer, String> nameCol;
    @FXML
    private TableColumn<Customer, String> addressCol;
    @FXML
    private TableColumn<Customer, String> contactNoCol;

    private Connection con;
    private Validator validator;
    private String tableName;
    private Customer customer;
    private ArrayList<Customer> customerList;

    @FXML
    private void initialize(){
        if(this.paneFrame != null) {
            Button confirmButton = paneFrame.getConfirmButton();
            this.tableName = "customer";
            this.con = Main.getCon();
            validator = new Validator();
            customer = new Customer();
            String action = Main.getButtonPressed();
            if(this.titleLabel.getText().contains("Add")) {
                ObservableList<String> bNoList = validator.createObservableList(this.con, "branch");
                this.bNoComboBox.getItems().addAll(bNoList);
                confirmButton.setOnAction(e -> confirmButtonPressed());
                this.bNoComboBox.setOnAction(e -> checkComboBox());
            }
            else{
                setColumns();
                fillTable();
                if(action.equalsIgnoreCase("Update")) {
                    this.tableView.getSelectionModel().setCellSelectionEnabled(true);
                    this.tableView.setEditable(true);
                    setColumnsEditable();
                    tableView.setOnKeyPressed(event -> {
                        TablePosition<Customer, ?> pos = tableView.getFocusModel().getFocusedCell() ;
                        if (pos != null && event.getCode() == ENTER) {
                            int row = pos.getRow();
                            TableColumn<Customer, ?> col = pos.getTableColumn();
                            tableView.edit(row, col);
                            col.setOnEditCommit(t ->
                            {(
                                    t.getTableView().getItems().get(t.getTablePosition().getRow()))
                                        .editDetails(t.getTablePosition().getColumn(),(String)t.getNewValue());
                                    Customer rowData = t.getRowValue();
                                    String newValue = (String)t.getNewValue();
                                    SQLQuery query = new SQLQuery();
                                    query.updateQuery(con,tableName,col.getText(),newValue,rowData.getCNo());
                            });
                        }
                    });
                }
                else if(action.equalsIgnoreCase("Delete")){
                    this.tableView.setEditable(true);
                    confirmButton.setOnAction(e-> {
                        Customer selectedItem = tableView.getSelectionModel().getSelectedItem();
                        tableView.getItems().remove(selectedItem);
                        SQLQuery query = new SQLQuery();
                        query.deleteQuery(con, tableName, selectedItem.getCNo());
                    });
                }
            }
        }
    }

    private void confirmButtonPressed(){
        validator.setCont(true);
        customer.setName(validator.validateTextFieldInputString(this.nameTxtFld.getText()));
        customer.setAddress(validator.validateTextFieldInputString(this.addressTxtFld.getText()));
        customer.setContactNo(validator.validateTextFieldInputInt(this.contactTxtFld.getText()));

        if(!validator.isCont())
            System.out.println("Enter correct input");
        else{
            customer.setCNo(validator.getNumber(con, this.tableName));
            System.out.println("Database connection established1");
            SQLQuery sqlQuery = new SQLQuery();
            sqlQuery.insertQuery(con, this.tableName,
                    customer.getCNo(), customer.getBNo(), customer.getName(),
                    customer.getAddress(), customer.getContactNo());
        }
        Stage s = (Stage)paneFrame.getScene().getWindow();
        s.close();
    }


    private void setColumns() {
        this.cNoCol.setCellValueFactory(new PropertyValueFactory<>("CNo"));
        this.bNoCol.setCellValueFactory(new PropertyValueFactory<>("BNo"));
        this.nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        this.addressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
        this.contactNoCol.setCellValueFactory(new PropertyValueFactory<>("ContactNo"));
    }

    private void setColumnsEditable(){
        bNoCol.setCellFactory(column -> EditCell.createStringEditCell());
        nameCol.setCellFactory(column -> EditCell.createStringEditCell());
        addressCol.setCellFactory(column -> EditCell.createStringEditCell());
        contactNoCol.setCellFactory(column -> EditCell.createStringEditCell());
    }

    private void fillTable(){
        ObservableList<Customer> ol = FXCollections.observableArrayList();
        customerList = new ArrayList<>();
        Statement s;
        try {
            s = con.createStatement();
            //Simple Query
            ResultSet rs = s.executeQuery ("SELECT * FROM customer");
            while (rs.next ())
            {
                int index = 1;
                Customer c = new Customer(
                        rs.getString (index++),rs.getString(index++),
                        rs.getString(index++),rs.getString (index++),
                        rs.getString (index));
                ol.add(c);
                customerList.add(c);
            }
            this.tableView.setItems(ol);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkComboBox(){
        if(this.bNoComboBox.getValue() != null
                && !this.bNoComboBox.getValue().isEmpty() )
            customer.setBNo(this.bNoComboBox.getValue());
    }

    public Label getTitleLabel() {
        return titleLabel;
    }
}
