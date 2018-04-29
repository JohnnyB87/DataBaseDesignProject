package controllers;

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
    private Label titleLabel;
    private Pane pane;
    private String menuName;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Label getTitleLabel() {
        return this.titleLabel;
    }

    public void accountButtonPressed() throws IOException {
        String s = this.menuName;
        if(!this.menuName.equalsIgnoreCase("add"))
            s = "viewDeleteUpdate";
        String str = String.format("../resources/%s/AccountWindow.fxml",s);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(str));
        this.pane = loader.load();
        AccountWindow cController = loader.getController();
        cController.getTitleLabel().setText(String.format("%s Account record",this.menuName));
        createNewStage(String.format("%s Account record",this.menuName));
    }

    public void customerButtonPressed() throws IOException {
        String s = this.menuName;
        if(!this.menuName.equalsIgnoreCase("add"))
            s = "viewDeleteUpdate";
        String str = String.format("../resources/%s/CustomerWindow.fxml",s);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(str));
        this.pane = loader.load();
        CustomerWindow cController = loader.getController();
        cController.getTitleLabel().setText(String.format("%s Customer record",this.menuName));
        createNewStage(String.format("%s Customer record",this.menuName));
    }

    public void staffButtonPressed() throws IOException {
        String s = this.menuName;
        if(!this.menuName.equalsIgnoreCase("add"))
            s = "viewDeleteUpdate";
        String str = String.format("../resources/%s/StaffWindow.fxml",s);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(str));
        this.pane = loader.load();
        StaffWindow sController = loader.getController();
        sController.getTitleLabel().setText(String.format("%s Staff record",this.menuName));
        createNewStage(String.format("%s Staff record",this.menuName));
    }

    public void branchButtonPressed() throws IOException {
        String s = this.menuName;
        if(!this.menuName.equalsIgnoreCase("add"))
            s = "viewDeleteUpdate";
        String str = String.format("../resources/%s/BranchWindow.fxml",s);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(str));
        this.pane = loader.load();
        BranchWindow bController = loader.getController();
        bController.getTitleLabel().setText(String.format("%s Branch record",this.menuName));
        createNewStage(String.format("%s Branch record",this.menuName));
    }

    private void createNewStage(String title){
        StackPane sp = new StackPane();
        sp.getChildren().add(this.pane);

        Scene scene = new Scene(sp,350,300);
        Stage stage = new Stage();

        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initOwner(MainWindow.getStage());
        stage.showAndWait();
    }
}
