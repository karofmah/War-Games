package wargames.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import wargames.WarGamesApplication;
import wargames.model.Battle;
import wargames.model.army.Army;

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

        int totalArmy1Units2=army1.getAllUnits().size();
        String totalArmy1UnitsString2=Integer.toString(totalArmy1Units2);
        int totalArmy2Units2=army2.getAllUnits().size();
        String totalArmy2UnitsString2=Integer.toString(totalArmy2Units2);
       totalNumberOfUnitsRedSide.setText(totalArmy2UnitsString2);
       totalNumberOfUnitsBlueSide.setText(totalArmy1UnitsString2);

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

        startBattleBtn.setOnMouseClicked(mouseEvent1 ->
                startBattle(army1,army2,"FOREST"));






    }


}
