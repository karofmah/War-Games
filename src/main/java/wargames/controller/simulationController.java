package wargames.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import wargames.WarGamesApplication;
import wargames.model.battle.Battle;
import wargames.model.army.Army;
import wargames.model.unitfactory.UnitFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    void armiesBtnClicked() {
        try {
            WarGamesApplication.changeScene("armiesView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void simulationBtnClicked() {
        try {
            WarGamesApplication.changeScene("simulationView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void startBattle(Army army1,Army army2,String terrain){
        Battle battle=new Battle(army1,army2,terrain);
        battle.simulate();

        int totalArmy1Units=army1.getAllUnits().size();
        String totalArmy1UnitsString=Integer.toString(totalArmy1Units);
        int totalArmy2Units=army2.getAllUnits().size();
        String totalArmy2UnitsString=Integer.toString(totalArmy2Units);
       totalNumberOfUnitsRedSide.setText(totalArmy2UnitsString);
       totalNumberOfUnitsBlueSide.setText(totalArmy1UnitsString);

    }
    public void resetBattle(Army army1,Army army2){
        try {
            UnitFactory factory = new UnitFactory();
            army1.getAllUnits().clear();
            army2.getAllUnits().clear();

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
        System.out.println(army1);
        System.out.println(army2);
        int totalArmy1Units=army1.getAllUnits().size();
        String totalArmy1UnitsString=Integer.toString(totalArmy1Units);
        totalNumberOfUnitsBlueSide.setText(totalArmy1UnitsString);

        int totalArmy2Units=army2.getAllUnits().size();
        String totalArmy2UnitsString=Integer.toString(totalArmy2Units);
        totalNumberOfUnitsRedSide.setText(totalArmy2UnitsString);

        startBattleBtn.setOnMouseClicked(mouseEvent ->
                startBattle(army1,army2,"FOREST"));
        resetBattleBtn.setOnMouseClicked(mouseEvent ->resetBattle(army1,army2) );





    }


}
