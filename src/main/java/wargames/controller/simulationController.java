package wargames.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import wargames.WarGamesApplication;
import wargames.model.battle.Battle;
import wargames.model.army.Army;
import wargames.model.unitfactory.UnitFactory;
import static wargames.dialogs.Dialogs.showAlertDialog;

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
    private Button resetBattleBtn;

    @FXML
    private ComboBox<String> terrainComboBox;

    @FXML
    private Label announcedWinnerLabel;


    @FXML
    private AnchorPane anchorPane;

    private ArrayList<Circle> listOfCirclesArmy1;

    private ArrayList<Circle> listOfCirclesArmy2;

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
    //Method to update number of units of each side (This method does not work yet)
    /*public void updateNumberOfUnits(Army army1,Army army2) throws InterruptedException {

        int totalArmy1Units = army1.size();
        String totalArmy1UnitsString = Integer.toString(totalArmy1Units);
        Platform.runLater(()->totalNumberOfUnitsBlueSide.setText(totalArmy1UnitsString));



        int totalArmy2Units = army2.size();
        String totalArmy2UnitsString = Integer.toString(totalArmy2Units);
        Platform.runLater(()->totalNumberOfUnitsRedSide.setText(totalArmy2UnitsString));


        System.out.println("updated");


    }*/
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

            for (int i=0;i<(listOfCirclesArmy1.size()-army1.size());i++) {
                anchorPane.getChildren().remove(listOfCirclesArmy1.get(i));
            }
            for (int i=0;i<(listOfCirclesArmy2.size()-army2.size());i++) {
                anchorPane.getChildren().remove(listOfCirclesArmy2.get(i));
            }



        }catch (IllegalArgumentException | IOException | InterruptedException e){
            showAlertDialog(e);

        }


    }

    public void createShapesForArmies(Army army1,Army army2){

        if(listOfCirclesArmy1!=null  && listOfCirclesArmy2!=null) {
            for (Circle circle : listOfCirclesArmy1) {
                anchorPane.getChildren().remove(circle);
            }
            for (Circle circle : listOfCirclesArmy2) {
                anchorPane.getChildren().remove(circle);
            }
            listOfCirclesArmy1.clear();
            listOfCirclesArmy2.clear();

        }

        listOfCirclesArmy1=new ArrayList<>();
        for (int i = 1; i < army1.size(); i++) {
            for (int j=150;j<=500;j+=25) {
                for (int k = 200; k<=450; k+=25) {
                    listOfCirclesArmy1.add(new Circle(j, k, 5, Color.BLUE));

                }
            }
        }

        for (Circle circle:listOfCirclesArmy1) {
            anchorPane.getChildren().add(circle);
        }
        listOfCirclesArmy2=new ArrayList<>();
        for (int i = 1; i < army2.size(); i++) {
            for (int j=600;j<=900;j+=25) {
                for (int k = 200; k<=450; k+=25) {
                    listOfCirclesArmy2.add(new Circle(j, k, 5, Color.RED));

                }
            }
        }

            for (Circle circle:listOfCirclesArmy2) {
                anchorPane.getChildren().add(circle);
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

            int totalArmy1Units=army1.getAllUnits().size();
            String totalArmy1UnitsString=Integer.toString(totalArmy1Units);
            totalNumberOfUnitsBlueSide.setText(totalArmy1UnitsString);

            int totalArmy2Units2=army2.getAllUnits().size();
            String totalArmy2UnitsString=Integer.toString(totalArmy2Units2);
            totalNumberOfUnitsRedSide.setText(totalArmy2UnitsString);


            createShapesForArmies(army1,army2);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }


    public void initData(Army army1, Army army2) {
        try {
            System.out.println(army1);
            System.out.println(army2);

            createShapesForArmies(army1,army2);

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
