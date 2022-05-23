module wargames {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.logging;
    requires java.desktop;
    opens wargames.controller to javafx.fxml;
    opens wargames to javafx.fxml;
    opens wargames.model.battle to javafx.fxml;
    opens wargames.model.army to javafx.fxml;
    opens wargames.model.units to javafx.fxml;
    opens wargames.dialogs to javafx.fxml;
    exports wargames;
    exports wargames.dialogs;
    exports wargames.controller;
    exports wargames.model.units;
    exports wargames.model.army;
    exports wargames.model.battle;
    exports wargames.model.observer;



}