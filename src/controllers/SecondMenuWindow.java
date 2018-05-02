package controllers;

import classes.PaneFrame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class SecondMenuWindow {

    @FXML
    private Button newBranch;
    @FXML
    private Button newStaff;
    @FXML
    private Button newCustomer;
    @FXML
    private Button newAccount;
    @FXML
    private Label titleLabel;
    private PaneFrame pane;
    private String menuName;
    private String buttonPressed;
    private int width = 350;
    private int height = 300;

    @FXML
    private void initialize(){
        this.newAccount.setOnAction(e->{
            this.buttonPressed="Account";
                buttonPressed();
        });
        this.newBranch.setOnAction(e->{
            this.buttonPressed="Branch";
                buttonPressed();
        });
        this.newCustomer.setOnAction(e->{
            this.buttonPressed="Customer";
                buttonPressed();
        });
        this.newStaff.setOnAction(e->{
            this.buttonPressed="Staff";
                buttonPressed();
        });
    }

    void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Label getTitleLabel() {
        return this.titleLabel;
    }

    private void buttonPressed()  {
        try {
            String s = this.menuName;
            if (!this.menuName.equalsIgnoreCase("add")) {
                s = "viewDeleteUpdate";
                this.width = 500;
                this.height = 400;
            }
            String str = String.format("../resources/%s/%sWindow.fxml",s ,this.buttonPressed);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(str));
            this.pane = loader.load();
            if (this.buttonPressed.equalsIgnoreCase("account")) {
                AccountWindow aController = loader.getController();
                aController.getTitleLabel().setText(String.format("%s Account record", this.menuName));
            }
            else if (this.buttonPressed.equalsIgnoreCase("branch")) {
                BranchWindow bController = loader.getController();
                bController.getTitleLabel().setText(String.format("%s Branch record", this.menuName));
            }
            else if (this.buttonPressed.equalsIgnoreCase("customer")) {
                CustomerWindow cController = loader.getController();
                cController.getTitleLabel().setText(String.format("%s Customer record", this.menuName));
            }
            else{
                StaffWindow sController = loader.getController();
                sController.getTitleLabel().setText(String.format("%s Staff record", this.menuName));
            }
            this.pane.setConfirmButtonText(this.menuName);
            createNewStage(String.format("%s %s record", this.menuName, this.buttonPressed), width, height);
        }catch(IOException ioe){
            System.out.println("Window not loading");
            ioe.printStackTrace();
        }
    }

    private void createNewStage(String title, int width, int height){
        StackPane sp = new StackPane();
        sp.getChildren().add(this.pane);

        Scene scene = new Scene(sp,width,height);
        Stage stage = new Stage();

        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initOwner(MainWindow.getStage());
        stage.showAndWait();
    }
}
