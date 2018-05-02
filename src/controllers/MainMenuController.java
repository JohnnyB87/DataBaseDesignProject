package controllers;

import classes.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    private Pane pane;
    private static Stage stage;
    private int width;
    private int height;

    public void buttonPressed(ActionEvent e) throws IOException {
        Button b = (Button)e.getSource();
        String buttonPressed = b.getText();
        Main.setButtonPressed(buttonPressed);
        FXMLLoader loader;
        System.out.println(buttonPressed);
        if(!(buttonPressed.equalsIgnoreCase("joinTable"))){
            loader = new FXMLLoader(getClass().getResource("../resources/SecondMenuWindow.fxml"));
            this.pane = loader.load();
            SecondMenuController addMenu = loader.getController();
            addMenu.getTitleLabel().setText(buttonPressed + " Menu");
            addMenu.setMenuName(buttonPressed);
            width = 350;
            height = 300;
        }
        else{
            width = 500;
            height = 400;
            loader = new FXMLLoader(getClass().getResource("../resources/JoinTableWindow.fxml"));
            this.pane = loader.load();
        }
        createNewStage(buttonPressed);
    }

    private void createNewStage(String title){
        StackPane sp = new StackPane();
        sp.getChildren().add(this.pane);

        Scene scene = new Scene(sp,width,height);
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
