package wargames.controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import wargames.WarGamesApplication;
import wargames.model.Army;
import wargames.model.Battle;
import wargames.model.unitfactory.UnitFactory;
import wargames.model.units.InfantryUnit;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class armiesController implements Initializable {
    @FXML
    private Button armiesBtn;

    @FXML
    private Button simulationBtn;
    @FXML
    private TableView<Army> armiesTableView;

    @FXML
    private TableColumn<?, ?> armyNameCol;

    @FXML
    private TableColumn<?, ?> numberOfCavalryUnitsCol;

    @FXML
    private TableColumn<?, ?> numberOfCommanderUnitsCol;

    @FXML
    private TableColumn<?, ?> numberOfInfantryUnitsCol;

    @FXML
    private TableColumn<?, ?> numberOfRangedUnitsCol;
    @FXML
    private TableColumn<?, ?> totalNumberOfUnitsCol;
    @FXML
    private ObservableList<Army> armyObservableList;

    private InfantryUnit infantry;

    UnitFactory factory;

    private Army army1;
    private Army army2;

    private Battle battle;

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
//TODO Change scene when this button is clicked

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.armyNameCol.setCellValueFactory(new PropertyValueFactory<>("armyName"));
        this.totalNumberOfUnitsCol.setCellValueFactory(new PropertyValueFactory<>("totalNumberOfUnits"));
        this.numberOfInfantryUnitsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfInfantryUnits"));
        this.numberOfRangedUnitsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfRangedUnits"));
        this.numberOfCavalryUnitsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfCavalryUnits"));
        this.numberOfCommanderUnitsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfCommanderUnits"));
        createArmies();
        createBattle();
        this.armyObservableList= FXCollections.observableArrayList((this.battle.getArmies()));
        this.armiesTableView.setItems(this.armyObservableList);

        //handleArmySelection();
    }
    public void createBattle(){
        try{this.battle=new Battle(this.army1,this.army2,"FOREST");}
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

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
    /*public void handleArmySelection() {
        armiesTableView.setRowFactory(table -> {
            TableRow<Army> row = new TableRow<>();

            row.hoverProperty().addListener(observable -> {//Listen for hover on row
                Army army = row.getItem();
                if (row.isHover() && army != null) {
                    row.setOnMouseEntered(mouseEvent1 -> {//Listen when mouse is hovered over a row
                        armiesTableView.setCursor(Cursor.HAND);//Change courser
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
    public void changeToSelectedArmyView(Army army){
        try {
            URL fxmlLocation = getClass().getResource("/wargames/selectedArmyView.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent FrontPageParent = loader.load();
            selectedArmyController controller = loader.getController();
            //controller.initData(army);
            Stage stage = WarGamesApplication.stage;
            stage.getScene().setRoot(FrontPageParent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
