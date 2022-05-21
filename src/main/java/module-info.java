module wargames {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.desktop;
    opens wargames.controller to javafx.fxml;
    opens wargames to javafx.fxml;
    opens wargames.model.battle to javafx.base;
    opens wargames.model.army to javafx.base;
    opens wargames.model.units to javafx.base;
    exports wargames.controller;
    exports wargames;
    exports wargames.model.units;
    exports wargames.model.army;
    exports wargames.model.battle;
    exports wargames.model.observer;


}