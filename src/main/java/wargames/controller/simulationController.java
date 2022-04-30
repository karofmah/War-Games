package wargames.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import wargames.WarGamesApplication;
import wargames.model.battle.Battle;
import wargames.model.army.Army;
import wargames.model.unitfactory.UnitFactory;
import static wargames.dialogs.Dialogs.showAlertDialog;
import static wargames.dialogs.Dialogs.showInformationDialog;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class simulationController implements Initializable {


    @FXML
    private Label totalNumberOfUnitsBlueSide;

    @FXML
    private Label totalNumberOfUnitsRedSide;

    @FXML
    private Button startBattleBtn;

    @FXML
    private Button showResultsBtn;

    @FXML
    private Button resetBattleBtn;

    @FXML
    private ComboBox<String> terrainComboBox;

    @FXML
    private Label announcedWinnerLabel;

    @FXML
    void armiesBtnClicked() {
        try {
            WarGamesApplication.changeScene("armiesView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void addTerrainsToComboBox(){
            ObservableList<String> terrainList= FXCollections.observableArrayList("Forest","Hill","Plains");
            terrainComboBox.setItems(terrainList);
    }


    @FXML

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addTerrainsToComboBox();

    }

    public void startBattle(Army army1,Army army2){
        try {
            Battle battle = new Battle(army1, army2, terrainComboBox.getValue());
            Army winner=battle.simulate();

            announcedWinnerLabel.setText(winner.getName() + " won the battle!");

            int totalArmy1Units = army1.getAllUnits().size();
            String totalArmy1UnitsString = Integer.toString(totalArmy1Units);
            totalNumberOfUnitsBlueSide.setText(totalArmy1UnitsString);

            int totalArmy2Units = army2.getAllUnits().size();
            String totalArmy2UnitsString = Integer.toString(totalArmy2Units);
            totalNumberOfUnitsRedSide.setText(totalArmy2UnitsString);


        }catch (IllegalArgumentException e){
            showAlertDialog(e);
        }


    }

    public void resetBattle(Army army1,Army army2){
        try {
            UnitFactory factory = new UnitFactory();
            army1.getAllUnits().clear();
            army2.getAllUnits().clear();
            announcedWinnerLabel.setText("");

            for (int i = 0; i < 10; i++) {
                army1.add(factory.create("InfantryUnit", "Footman", 100));
                army1.add(factory.create("RangedUnit", "Archer", 100));
                army2.add(factory.create("InfantryUnit", "Grunt", 100));
                army2.add(factory.create("RangedUnit", "Spearman", 100));

            }
            for (int i = 0; i < 5; i++) {
                army1.add(factory.create("CavalryUnit", "Knight", 100));
                army1.add(factory.create("CommanderUnit", "Mountain King", 100));
                army2.add(factory.create("CavalryUnit", "Raider", 100));
                army2.add(factory.create("CommanderUnit", "Gul´dan", 100));
            }

        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        int totalArmy1Units=army1.getAllUnits().size();
        String totalArmy1UnitsString=Integer.toString(totalArmy1Units);
        int totalArmy2Units2=army2.getAllUnits().size();
        String totalArmy2UnitsString=Integer.toString(totalArmy2Units2);
        totalNumberOfUnitsRedSide.setText(totalArmy2UnitsString);
        totalNumberOfUnitsBlueSide.setText(totalArmy1UnitsString);
    }


    public void initData(Army army1, Army army2) {
        try {
            System.out.println(army1);
            System.out.println(army2);
            int totalArmy1Units = army1.getAllUnits().size();
            String totalArmy1UnitsString = Integer.toString(totalArmy1Units);
            totalNumberOfUnitsBlueSide.setText(totalArmy1UnitsString);

            int totalArmy2Units = army2.getAllUnits().size();
            String totalArmy2UnitsString = Integer.toString(totalArmy2Units);
            totalNumberOfUnitsRedSide.setText(totalArmy2UnitsString);


            startBattleBtn.setOnMouseClicked(mouseEvent ->
                        startBattle(army1, army2));

            resetBattleBtn.setOnMouseClicked(mouseEvent -> resetBattle(army1, army2));
        }
        catch(IllegalArgumentException e){
            showAlertDialog(e);
            }

    }


}
