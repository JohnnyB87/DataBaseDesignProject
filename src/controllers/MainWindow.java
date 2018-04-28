package controllers;

import classes.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow{

    private Pane pane;
    private static Stage stage;

    public void buttonPressed(ActionEvent e) throws IOException {
        Button b = (Button)e.getSource();
        String str = b.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/SecondMenuWindow.fxml"));
        this.pane = loader.load();
        SecondMenuWindow addMenu = loader.getController();
        addMenu.getTitleLabel().setText(str + " Menu");
        addMenu.setMenuName(str);
        createNewStage(str);
    }

    private void createNewStage(String title){
        StackPane sp = new StackPane();
        sp.getChildren().add(this.pane);

        Scene scene = new Scene(sp,350,300);
        stage = new Stage();

        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initOwner(Main.getPrimaryStage());
        stage.showAndWait();
    }
    public static Stage getStage(){
        return stage;
    }


}
