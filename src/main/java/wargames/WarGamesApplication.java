package wargames;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Objects;



public class WarGamesApplication extends javafx.application.Application {
    public static Stage stage;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        WarGamesApplication.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(WarGamesApplication.class.getResource("armiesView.fxml"));
        Scene scene = null;
        stage.setMaximized(true);
        try {
            scene = new Scene(fxmlLoader.load(), 1280, 720);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("War Games");
        stage.setScene(scene);
        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, windowEvent -> {


        });
        stage.show();
    }
    public static void changeScene(String fxml) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(WarGamesApplication.class.getResource(fxml)));
        Stage stage = WarGamesApplication.stage;
        stage.getScene().setRoot(parent);
    }
}
