package controllers;

import classes.Main;
import classes.SQLQuery;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoinTableController {

    @FXML private TableView<List<String>> tableView;
    @FXML private ComboBox<String> branchNo;
    @FXML private ComboBox<String> staffCol;
    @FXML private ComboBox<String> branchNoCustomer;
    @FXML private ComboBox<String> customerCol;
    @FXML private ComboBox<String> customerNo;
    @FXML private ComboBox<String> accCol;

    private List<String> colListNames;
    private List<List<String>> data;
    private Connection con;
    private String bNoS;
    private String cNo;
    private String bNoC;
    private String cCol;
    private String aCol;
    private String sCol;

    @FXML
    private void initialize(){
        con = Main.getCon();
        fillComboBoxes();
    }

    private void fillComboBoxes() {
        this.branchNo.setItems(loopThroughTable("Branch","Bno"));
        this.branchNoCustomer.setItems(loopThroughTable("Branch","Bno"));
        this.customerNo.setItems(loopThroughTable("Customer","Cno"));
        this.accCol.setItems(loopThroughTableCol("CustomerAccount"));
        this.staffCol.setItems(loopThroughTableCol("Staff"));
        this.customerCol.setItems(loopThroughTableCol("Customer"));
    }

    private ObservableList<String> loopThroughTable( String tableName, String columnName){
        ObservableList<String> array = FXCollections.observableArrayList();
        try {
            Statement s = con.createStatement();
            String select = String.format("SELECT %s FROM %s",columnName,tableName);
            ResultSet rs = s.executeQuery(select);
            while(rs.next()) {
                String bNo = rs.getString(columnName);
                array.add(bNo);
            }
        }catch(SQLException sql){
            System.out.println("Failed to Loop");
        }
        return array;
    }

    private ObservableList<String> loopThroughTableCol( String tableName){
        ObservableList<String> array = FXCollections.observableArrayList();
        try {
            Statement s = con.createStatement ();
            String query = String.format("SELECT * FROM %s",tableName);
            ResultSet rs = s.executeQuery (query);
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            for (int i=1; i<=count; i++)
                array.add(metaData.getColumnLabel(i));
            array.add("ALL");
        }catch(SQLException sql){
            System.out.println("Failed to Loop");
        }
        return array;
    }

    private void getAllData(ResultSet rs){
        this.tableView.getColumns().clear();
        this.colListNames = new ArrayList<>();
        this.data = new ArrayList<>();
        try {
            ResultSetMetaData meta = rs.getMetaData();
            int count = meta.getColumnCount();

            for(int i=1;i<=count;i++){
                this.colListNames.add(meta.getColumnName(i));
            }

            while(rs.next()){
                List<String> row = new ArrayList<>();
                for(int i=1; i<=count;i++){
                    String s = rs.getString(i);
                    row.add(s);
                }
                data.add(row);
            }

            fillTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillTable(){
        for (int i = 0 ; i < colListNames.size() ; i++) {
            TableColumn<List<String>, String> column = new TableColumn<>(colListNames.get(i));
            int columnIndex = i ;
            column.setCellValueFactory(cellData ->
                    new SimpleObjectProperty<>(cellData.getValue().get(columnIndex)));
            tableView.getColumns().add(column);

        }
        tableView.getItems().setAll(this.data);
    }

    @FXML
    private void searchBranchStaffBtn(){
        try{
            SQLQuery sql = new SQLQuery();
            String table1 = "Branch";
            String table2 = "Staff";
            ResultSet rs = sql.joinQuery(con, table1, table2, this.bNoS, this.sCol);
            getAllData(rs);
            rs.close();
        }
        catch(SQLException | NullPointerException e){
            System.out.println("Invalid Selections");
        }
    }

    @FXML
    private void searchBranchCustomerBtn() {
        try {
            SQLQuery sql = new SQLQuery();
            String table1 = "Branch";
            String table2 = "Customer";
            ResultSet rs = sql.joinQuery(con, table1, table2, this.bNoC, this.cCol);
            getAllData(rs);
            rs.close();
        }
        catch(SQLException | NullPointerException e){
            System.out.println("Invalid Selections");
        }

    }

    @FXML
    private void searchCustomerAccBtn() {
        try{
            SQLQuery sql = new SQLQuery();
            String table1 = "Customer";
            String table2 = "CustomerAccount";
            ResultSet rs = sql.joinQuery(con, table1, table2, this.cNo, this.aCol);
            getAllData(rs);
            rs.close();
        }
        catch(SQLException | NullPointerException e){
            System.out.println("Invalid Selections");
        }
    }

    @FXML
    private void setBNoS() {
        if(!this.branchNo.getValue().isEmpty() && this.branchNo.getValue() != null)
            this.bNoS = this.branchNo.getValue();
    }

    @FXML
    private void setSCol() {
        if(!this.staffCol.getValue().isEmpty() && this.staffCol.getValue() != null)
            this.sCol =  this.staffCol.getValue();
    }

    @FXML
    private void setBNoC() {
        if(!this.branchNoCustomer.getValue().isEmpty() && this.branchNoCustomer.getValue() != null)
            this.bNoC =  this.branchNoCustomer.getValue();
    }

    @FXML
    private void setCCol() {
        if(!this.customerCol.getValue().isEmpty() && this.customerCol.getValue() != null)
            this.cCol =  this.customerCol.getValue();
    }

    @FXML
    private void setCNo() {
        if(!this.customerNo.getValue().isEmpty() && this.customerNo.getValue() != null)
            this.cNo =  this.customerNo.getValue();
    }

    @FXML
    private void setACol() {
        if(!this.accCol.getValue().isEmpty() && this.accCol.getValue() != null)
            this.aCol =  this.accCol.getValue();
    }
}
