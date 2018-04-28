import controllers.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{

    private static Stage primaryStage = new Stage();

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args){
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = getPrimaryStage();
        primaryStage.setTitle("Main Menu");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/MainWindow.fxml"));
        Pane mainWindow = loader.load();
        MainWindow mw = loader.getController();

        StackPane layout = new StackPane();

        layout.getChildren().add(mainWindow);
        Scene scene = new Scene(layout,400,350);


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
