module wargames {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.desktop;
    opens wargames.controller to javafx.fxml;
    opens wargames to javafx.fxml;
    exports wargames.controller;
    exports wargames;

}