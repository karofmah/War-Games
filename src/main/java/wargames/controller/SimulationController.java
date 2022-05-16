package wargames.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
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

public class SimulationController implements Initializable {


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
    private TilePane tilePane1;

    @FXML
    private TilePane tilePane2;

    @FXML
    private TableColumn<?, ?> armyNameCol1;

    @FXML
    private TableColumn<?, ?> armyNameCol2;

    @FXML
    private TableColumn<?, ?> numberOfCavalryUnitsCol1;

    @FXML
    private TableColumn<?, ?> numberOfCavalryUnitsCol2;

    @FXML
    private TableColumn<?, ?> numberOfCommanderUnitsCol1;

    @FXML
    private TableColumn<?, ?> numberOfCommanderUnitsCol2;

    @FXML
    private TableColumn<?, ?> numberOfInfantryUnitsCol1;

    @FXML
    private TableColumn<?, ?> numberOfInfantryUnitsCol2;

    @FXML
    private TableColumn<?, ?> numberOfRangedUnitsCol1;

    @FXML
    private TableColumn<?, ?> numberOfRangedUnitsCol2;

    @FXML
    private TableColumn<?, ?> totalNumberOfUnitsCol1;

    @FXML
    private TableColumn<?, ?> totalNumberOfUnitsCol2;

    @FXML
    private TableView<Army> army1TableView;

    @FXML
    private TableView<Army> army2TableView;

    private ArrayList <Circle> listOfCirclesArmy1;

    private ArrayList <Circle> listOfCirclesArmy2;


    /**
     * Method to change scene to armiesView when armies button is clicked.
     */
    @FXML
    void armiesBtnClicked() {
        try {
            WarGamesApplication.changeScene("armiesView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method to add existing terrains to a ComboBox
     */
    private void addTerrainsToComboBox(){
            ObservableList<String> terrainList= FXCollections.observableArrayList("Forest","Hill","Plains");
            terrainComboBox.setItems(terrainList);
    }


    /**
     * Method to initialize data when this view is present
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addTerrainsToComboBox();
    }
    //Method to update number of units of each side (This method does not work yet)
     /*public void updateNumberOfUnits(Army army1,Army army2) throws InterruptedException {

        int totalArmy1Units = army1.size();
        String totalArmy1UnitsString = Integer.toString(totalArmy1Units);
        totalNumberOfUnitsBlueSide.setText(totalArmy1UnitsString);

        int totalArmy2Units = army2.size();
        String totalArmy2UnitsString = Integer.toString(totalArmy2Units);
        totalNumberOfUnitsRedSide.setText(totalArmy2UnitsString);
         Thread.sleep(2000);

        System.out.println("updated");


    }*/

    /**
     * Method to start battle between two armies in chosen terrain
     * Linked to the start battle button
     * @param army1 one of the armies in the battle
     * @param army2 the other of the armies in the battle
     */
    public void startBattle(Army army1,Army army2){
        try {
            Battle battle = new Battle(army1, army2, terrainComboBox.getValue());
            Army winner=battle.simulate();

            announcedWinnerLabel.setText(winner.getName() + " won the battle!");

            updateTableViews(army1,army2);

            for (int i=0;i<listOfCirclesArmy1.size()-army1.size();i++) {
                tilePane1.getChildren().remove(listOfCirclesArmy1.get(i));
            }
            for (int i=0;i<listOfCirclesArmy2.size()-army2.size();i++) {
                tilePane2.getChildren().remove(listOfCirclesArmy2.get(i));
            }




        }catch (IllegalArgumentException | IOException | InterruptedException e){
            showAlertDialog(e);

        }


    }

    /**
     * Method to create circles that represent the units in the armies
     * @param army1 one of the armies in a battle
     * @param army2 the other of the armies in a battle
     */
    public void createShapesForArmies(Army army1,Army army2){

        if(listOfCirclesArmy1!=null  && listOfCirclesArmy2!=null) {
            for (Circle circle : listOfCirclesArmy1) {
                tilePane1.getChildren().remove(circle);
            }
            for (Circle circle : listOfCirclesArmy2) {
                tilePane2.getChildren().remove(circle);
            }
            listOfCirclesArmy1.clear();
            listOfCirclesArmy2.clear();

        }
        listOfCirclesArmy1=new ArrayList<>();

        for (int i = 0; i <army1.size(); i++) {
            listOfCirclesArmy1.add(new Circle(10,Color.BLUE));
        }

        for (Circle circle:listOfCirclesArmy1) {
            tilePane1.getChildren().add(circle);
        }

        listOfCirclesArmy2=new ArrayList<>();

        for (int i = 0; i <army2.size(); i++) {
                    listOfCirclesArmy2.add(new Circle(10,Color.RED));
        }

        for (Circle circle:listOfCirclesArmy2) {
                tilePane2.getChildren().add(circle);
        }
    }

    /**
     * Method to reset a battle that has finished between two armies
     * @param army1 one of the armies in the battle
     * @param army2 the other of the armies in a battle
     */
    public void resetBattle(Army army1,Army army2){
        try {
            UnitFactory factory = new UnitFactory();
            army1.getAllUnits().clear();
            army2.getAllUnits().clear();
            announcedWinnerLabel.setText("");

            updateTableViews(army1,army2);
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




            createShapesForArmies(army1,army2);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Method to initialize transferred data from armiesView when this view is present
     * @param army1 one of the armies in a battle
     * @param army2 the other of the armies in a battle
     */
    public void initData(Army army1, Army army2) {
        try {
            System.out.println(army1);
            System.out.println(army2);

            createShapesForArmies(army1,army2);

            updateTableViews(army1,army2);

            startBattleBtn.setOnMouseClicked(mouseEvent ->
                        startBattle(army1, army2));

            resetBattleBtn.setOnMouseClicked(mouseEvent -> resetBattle(army1, army2));

        }
        catch(IllegalArgumentException e){
            showAlertDialog(e);
            }

    }
    public void updateTableViews(Army army1, Army army2){
        army1TableView.refresh();
        army2TableView.refresh();
        this.armyNameCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.totalNumberOfUnitsCol1.setCellValueFactory(new PropertyValueFactory<>("totalNumberOfUnits"));
        this.numberOfInfantryUnitsCol1.setCellValueFactory(new PropertyValueFactory<>("numberOfInfantryUnits"));
        this.numberOfRangedUnitsCol1.setCellValueFactory(new PropertyValueFactory<>("numberOfRangedUnits"));
        this.numberOfCavalryUnitsCol1.setCellValueFactory(new PropertyValueFactory<>("numberOfCavalryUnits"));
        this.numberOfCommanderUnitsCol1.setCellValueFactory(new PropertyValueFactory<>("numberOfCommanderUnits"));

        this.armyNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.totalNumberOfUnitsCol2.setCellValueFactory(new PropertyValueFactory<>("totalNumberOfUnits"));
        this.numberOfInfantryUnitsCol2.setCellValueFactory(new PropertyValueFactory<>("numberOfInfantryUnits"));
        this.numberOfRangedUnitsCol2.setCellValueFactory(new PropertyValueFactory<>("numberOfRangedUnits"));
        this.numberOfCavalryUnitsCol2.setCellValueFactory(new PropertyValueFactory<>("numberOfCavalryUnits"));
        this.numberOfCommanderUnitsCol2.setCellValueFactory(new PropertyValueFactory<>("numberOfCommanderUnits"));

        ObservableList<Army> army1ObservableList = FXCollections.observableArrayList(
                new Army(army1.getName(), army1.size(), army1.getInfantryUnits().size(),
                        army1.getRangedUnits().size(), army1.getCavalryUnits().size(),
                        army1.getCommanderUnits().size(),army1.getAllUnits()));

        ObservableList<Army> army2ObservableList=FXCollections.observableArrayList(new Army(army2.getName(), army2.size(), army2.getInfantryUnits().size(),
                army2.getRangedUnits().size(), army2.getCavalryUnits().size(),
                army2.getCommanderUnits().size(),army2.getAllUnits()));
        this.army1TableView.setItems(army1ObservableList);
        this.army2TableView.setItems(army2ObservableList);
    }


}
