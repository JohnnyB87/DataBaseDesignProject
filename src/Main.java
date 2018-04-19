import controllers.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args){

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/MainWindow.fxml"));
        //this.ps = loader.load();
        MainWindow mw = loader.getController();

        StackPane layout = new StackPane();

        layout.getChildren().add(mw);
        Scene scene = new Scene(layout);

        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }
}
