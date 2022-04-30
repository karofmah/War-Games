package wargames;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;


public class WarGamesApplication extends javafx.application.Application {
    public static Stage stage;

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        WarGamesApplication.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(WarGamesApplication.class.getResource("armiesView.fxml"));
        Scene scene = null;

        try {
            scene = new Scene(fxmlLoader.load(), 1280, 720);
        } catch (IOException e) {
            logger.severe("ERROR: IOException occurred. Cause: " + e.getCause());
        }
        stage.setTitle("War Games");
        stage.setScene(scene);
        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, windowEvent -> {
            //Since connection is kept as long as the app is running
            //Listen for closing event and close when at app exit

        });
        stage.show();
    }
    public static void changeScene(String fxml) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(WarGamesApplication.class.getResource(fxml)));
        Stage stage = WarGamesApplication.stage;
        stage.getScene().setRoot(parent);
    }
}
