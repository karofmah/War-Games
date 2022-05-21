package wargames;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


/**
 * The war games application
 */
public class WarGamesApplication extends javafx.application.Application {
    public static Stage stage;


    /**
     * Main methods for the application
     * @param args args
     */
    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Start method for the application
     * in a stage
     * @param stage of the application
     */
    @Override
    public void start(Stage stage) {

        WarGamesApplication.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(WarGamesApplication.class.getResource("armiesView.fxml"));
        Scene scene = null;
        stage.setMaximized(true);


        try {
            scene = new Scene(fxmlLoader.load(), 627, 500);

        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("War Games");
        stage.setScene(scene);
        stage.setMaxWidth(5000);
        stage.show();
    }

    /**
     * Method to change between scenes
     * @param fxml fxml file to change to
     * @throws IOException e
     */
    public static void changeScene(String fxml) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(WarGamesApplication.class.getResource(fxml)));
        Stage stage = WarGamesApplication.stage;
        stage.getScene().setRoot(parent);

    }


}
