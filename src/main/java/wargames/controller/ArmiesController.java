package wargames.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Cursor;
import javafx.scene.Parent;

import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;
import wargames.WarGamesApplication;
import wargames.model.army.Army;

import wargames.model.unitfactory.UnitFactory;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ArmiesController implements Initializable {

    @FXML
    private TableView<Army> armiesTableView;

    @FXML
    private TableColumn<Army,String> armyNameCol;

    @FXML
    private TableColumn<Army,Integer> numberOfCavalryUnitsCol;

    @FXML
    private TableColumn<Army,Integer> numberOfCommanderUnitsCol;

    @FXML
    private TableColumn<Army,Integer> numberOfInfantryUnitsCol;

    @FXML
    private TableColumn<Army,Integer> numberOfRangedUnitsCol;

    @FXML
    private TableColumn<Army,Integer> totalNumberOfUnitsCol;

    @FXML
    private TextArea textFromFileArea;

    @FXML
    private TextField fileLocationTextField;

    UnitFactory factory;

    private Army army1;
    private Army army2;


    /**
     * Method to change view when the armies button is clicked to the same view
     * Linked to the armies button
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
     * Method to initialize data when this view is present
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.armyNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.totalNumberOfUnitsCol.setCellValueFactory(new PropertyValueFactory<>("totalNumberOfUnits"));
        this.numberOfInfantryUnitsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfInfantryUnits"));
        this.numberOfRangedUnitsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfRangedUnits"));
        this.numberOfCavalryUnitsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfCavalryUnits"));
        this.numberOfCommanderUnitsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfCommanderUnits"));
        createArmies();
        ObservableList<Army> armyObservableList = FXCollections.observableArrayList(
                new Army(army1.getName(), army1.size(), army1.getInfantryUnits().size(),
                        army1.getRangedUnits().size(), army1.getCavalryUnits().size(),
                        army1.getCommanderUnits().size(),army1.getAllUnits()),

                new Army(army2.getName(), army2.size(), army2.getInfantryUnits().size(),
                        army2.getRangedUnits().size(), army2.getCavalryUnits().size(),
                        army2.getCommanderUnits().size(),army2.getAllUnits()));
        this.armiesTableView.setItems(armyObservableList);
        handleArmySelection();


    }

    /**
     * Method to create armies to battle each other
     */
 public void createArmies(){
        try {
            this.factory = new UnitFactory();
            this.army1 = new Army("Blue Side");
            this.army2 = new Army("Red Side", new ArrayList<>());

            for (int i = 0; i < 10; i++) {
                this.army1.add(factory.create("InfantryUnit", "Footman", 100));
                this.army1.add(factory.create("RangedUnit", "Archer", 100));
                this.army2.add(factory.create("InfantryUnit", "Grunt", 100));
                this.army2.add(factory.create("RangedUnit", "Spearman", 100));

            }
            for (int i = 0; i < 5; i++) {
                this.army1.add(factory.create("CavalryUnit", "Knight", 100));
                this.army1.add(factory.create("CommanderUnit", "Mountain King", 100));
                this.army2.add(factory.create("CavalryUnit", "Raider", 100));
                this.army2.add(factory.create("CommanderUnit", "Gul´dan", 100));
            }

        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to handle the selection of an army in the tableview
     */
    public void handleArmySelection() {
        armiesTableView.setRowFactory(table -> {
            TableRow<Army> row = new TableRow<>();

            row.hoverProperty().addListener(observable -> {//Listen for hover on row
                Army army = row.getItem();
                if (row.isHover() && army != null) {
                    row.setOnMouseEntered(mouseEvent1 -> {//Listen when mouse is hovered over a row
                        armiesTableView.setCursor(Cursor.HAND);//Change cursor
                        army.writeArmyToFile(new File("src/main/resources/ArmyFile.csv"));//Write to file
                        textFromFileArea.setText(army.readArmyFromFile(new File("src/main/resources/ArmyFile.csv")));//Read from file
                        fileLocationTextField.setText("wargames/src/main/resources/ArmyFile.csv");
                        row.setOnMouseClicked(mouseEvent2 -> { //Listen for click event


                            changeToSelectedArmyView(army);//Change scene

                        });
                    });
                } else {
                    row.setOnMouseEntered(mouseEvent -> { //Default cursor when row is empty
                        armiesTableView.setCursor(Cursor.DEFAULT);
                    });
                }
            });

            return row;
        });
    }

    /**
     * Method to change scene to selectedArmyView with information of that specific army
     * @param army that is selected from the tableview
     */
    public void changeToSelectedArmyView(Army army){
        try {
            URL fxmlLocation = getClass().getResource("/wargames/selectedArmyView.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent FrontPageParent = loader.load();
            SelectedArmyController controller = loader.getController();
            controller.initData(army);
            Stage stage = WarGamesApplication.stage;
            stage.getScene().setRoot(FrontPageParent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to change scene to simulationView when the simulation button is clicked.
     */
    public void simulationBtnClicked() {
        try {
            URL fxmlLocation = getClass().getResource("/wargames/simulationView.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent FrontPageParent = loader.load();
            SimulationController controller = loader.getController();
            controller.initData(army1,army2);
            Stage stage = WarGamesApplication.stage;
            stage.getScene().setRoot(FrontPageParent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
