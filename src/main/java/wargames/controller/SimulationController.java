package wargames.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import wargames.WarGamesApplication;
import wargames.model.army.Army;
import wargames.model.battle.Battle;
import wargames.model.observer.Subscriber;
import wargames.model.units.*;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static wargames.dialogs.Dialogs.showAlertDialog;
import static wargames.dialogs.Dialogs.showInformationDialog;

/**
 * Controller class that mainly manages and updates simulationView.fxml.
 */

public class SimulationController implements Subscriber {


    @FXML
    private Button startBattleBtn;

    @FXML
    private Button resetBattleBtn;

    @FXML
    private ComboBox<String> terrainComboBox;

    @FXML
    private TilePane tilePane1;

    @FXML
    private TilePane tilePane2;

    @FXML
    private ScrollPane scrollPane;

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
    private TableColumn<?,?> numberOfMageUnitsCol1;

    @FXML
    private TableColumn<?,?> numberOfMageUnitsCol2;

    @FXML
    private TableView<Army> army1TableView;

    @FXML
    private TableView<Army> army2TableView;


    private ArrayList<ImageView> listOfImageViewsArmy1;

    private ArrayList<ImageView> listOfImageViewsArmy2;

    Battle battle;

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
    private void addTerrainsToComboBox() {
        ObservableList<String> terrainList = FXCollections.observableArrayList("Forest", "Hill", "Plains");
        terrainComboBox.setItems(terrainList);
    }


    /**
     * Method to start battle between two armies in chosen terrain
     * Linked to the start battle button.
     * This controller-class is added as a subscriber
     * of the Subject/Publisher during the battle,
     * and the armies are updated when the battle has ended
     *
     * @param army1 one of the armies in the battle
     * @param army2 the other of the armies in the battle
     */
    public void startBattle(Army army1, Army army2) {
        try {

            battle = new Battle(army1, army2, terrainComboBox.getValue());
            battle.addSubscriber(this);
            Army winner = battle.simulate();
            battle.removeSubscriber(this);

            showInformationDialog(winner.getName() + " has won the battle!");


        } catch (IllegalArgumentException | IOException | InterruptedException|URISyntaxException e) {
            showAlertDialog(e);

        }


    }

    /**
     * Method to create images/image icons that represents
     * the different type of units in the armies
     *
     * @param army1 One of the armies that will take part in a battle
     * @param army2 The other army in a battle that will take part in a battle
     * @throws URISyntaxException e
     * @throws FileNotFoundException e
     * @throws MalformedURLException e
     */
    public void createImagesForArmies(Army army1, Army army2) throws URISyntaxException, FileNotFoundException, MalformedURLException {

        if (listOfImageViewsArmy1 != null && listOfImageViewsArmy2 != null) {
            for (ImageView imageView : listOfImageViewsArmy1) {
                tilePane1.getChildren().remove(imageView);
            }
            for (ImageView imageView : listOfImageViewsArmy2) {
                tilePane2.getChildren().remove(imageView);
            }
            listOfImageViewsArmy1.clear();
            listOfImageViewsArmy2.clear();
        }
        listOfImageViewsArmy1 = new ArrayList<>();
        for (Unit unit:army1.getAllUnits()) {
            if(unit instanceof InfantryUnit) {
                ImageIcon imageIcon = new ImageIcon("file:src/main/resources/images/blueInfantry.png");
                ImageView imageView = new ImageView(String.valueOf(imageIcon));
                listOfImageViewsArmy1.add(imageView);
            }
            else if(unit instanceof RangedUnit) {
                ImageIcon imageIcon = new ImageIcon("file:src/main/resources/images/blueRanged.png");
                ImageView imageView = new ImageView(String.valueOf(imageIcon));
                listOfImageViewsArmy1.add(imageView);
            }
            else if(unit instanceof CavalryUnit && !(unit instanceof CommanderUnit)) {
                ImageIcon imageIcon = new ImageIcon("file:src/main/resources/images/blueCavalry.png");
                ImageView imageView = new ImageView(String.valueOf(imageIcon));
                listOfImageViewsArmy1.add(imageView);
        }
            else if(unit instanceof CommanderUnit){
                ImageIcon imageIcon = new ImageIcon("file:src/main/resources/images/blueCommander.png");
                ImageView imageView = new ImageView(String.valueOf(imageIcon));
                listOfImageViewsArmy1.add(imageView);
            }
            else if(unit instanceof MageUnit){
                ImageIcon imageIcon = new ImageIcon("file:src/main/resources/images/blueMage.png");
                ImageView imageView = new ImageView(String.valueOf(imageIcon));
                listOfImageViewsArmy1.add(imageView);
            }

        }

        for (ImageView imageView : listOfImageViewsArmy1) {
            tilePane1.getChildren().add(imageView);
        }

        listOfImageViewsArmy2 = new ArrayList<>();

        for (Unit unit:army2.getAllUnits()) {
            if(unit instanceof InfantryUnit) {
                ImageIcon imageIcon = new ImageIcon("file:src/main/resources/images/redInfantry.png");
                ImageView imageView = new ImageView(String.valueOf(imageIcon));
                listOfImageViewsArmy2.add(imageView);
            }
            else if(unit instanceof RangedUnit) {
                ImageIcon imageIcon = new ImageIcon("file:src/main/resources/images/redRanged.png");
                ImageView imageView = new ImageView(String.valueOf(imageIcon));
                listOfImageViewsArmy2.add(imageView);
            }
            else if(unit instanceof CavalryUnit && !(unit instanceof CommanderUnit)) {
                ImageIcon imageIcon = new ImageIcon("file:src/main/resources/images/redCavalry.png");
                ImageView imageView = new ImageView(String.valueOf(imageIcon));
                listOfImageViewsArmy2.add(imageView);
            }
            else if(unit instanceof CommanderUnit){
                ImageIcon imageIcon = new ImageIcon("file:src/main/resources/images/redCommander.png");
                ImageView imageView = new ImageView(String.valueOf(imageIcon));
                listOfImageViewsArmy2.add(imageView);
            }
            else if(unit instanceof MageUnit){
                ImageIcon imageIcon = new ImageIcon("file:src/main/resources/images/redMage.png");
                ImageView imageView = new ImageView(String.valueOf(imageIcon));
                listOfImageViewsArmy2.add(imageView);
            }
        }

        for (ImageView imageView : listOfImageViewsArmy2) {
            tilePane2.getChildren().add(imageView);
        }
    }

    /**
     * Method to reset a battle that has finished between two armies.
     * The state of the armies before the battle was started,
     * will return
     * @param army1 One of the armies after battle
     * @param army2 The other army after battle
     * @param army1Saved One of the armies before battle
     * @param army2Saved The other army before battle
     * @throws MalformedURLException e
     * @throws FileNotFoundException e
     * @throws URISyntaxException e
     */
    public void resetBattle(Army army1, Army army2,Army army1Saved,Army army2Saved) throws MalformedURLException, FileNotFoundException, URISyntaxException {

        army1.getAllUnits().clear();
        army2.getAllUnits().clear();
        army1.addAll(army1Saved.getAllUnits());
        army2.addAll(army2Saved.getAllUnits());
        updateArmies(army1,army2);

    }



    /**
     * Method to initialize transferred data from armiesView when this view is present
     * Also initiates methods to set up the simulation of the battle between armies
     *
     * @param army1 one of the armies in a battle
     * @param army2 the other of the armies in a battle
     */
    public void initData(Army army1, Army army2) {
        try {
            scrollPane.setHvalue(0.54);

            Army army1Saved=new Army(army1.getName());
            Army army2Saved=new Army(army2.getName());
            updateArmies(army1, army2);

            System.out.println(army1.size());
            System.out.println(army2.size());
            startBattleBtn.setOnMouseClicked(mouseEvent ->{
                    army1Saved.addAll(army1.getAllUnits());
                    army2Saved.addAll(army2.getAllUnits());
                    startBattle(army1, army2);
                    });

            resetBattleBtn.setOnMouseClicked(mouseEvent -> {
                try {
                    resetBattle(army1,army2,army1Saved, army2Saved);
                } catch (MalformedURLException | FileNotFoundException | URISyntaxException e) {
                    e.printStackTrace();
                    showAlertDialog(e);
                }
            });


            addTerrainsToComboBox();

            createImagesForArmies(army1, army2);

            if(army1.size()>144 || army2.size()>144){
                showInformationDialog("Max 144 units are displayed per army");
        }

            this.armyNameCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
            this.totalNumberOfUnitsCol1.setCellValueFactory(new PropertyValueFactory<>("totalNumberOfUnits"));
            this.numberOfInfantryUnitsCol1.setCellValueFactory(new PropertyValueFactory<>("numberOfInfantryUnits"));
            this.numberOfRangedUnitsCol1.setCellValueFactory(new PropertyValueFactory<>("numberOfRangedUnits"));
            this.numberOfCavalryUnitsCol1.setCellValueFactory(new PropertyValueFactory<>("numberOfCavalryUnits"));
            this.numberOfCommanderUnitsCol1.setCellValueFactory(new PropertyValueFactory<>("numberOfCommanderUnits"));
            this.numberOfMageUnitsCol1.setCellValueFactory(new PropertyValueFactory<>("numberOfMageUnits"));


            this.armyNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
            this.totalNumberOfUnitsCol2.setCellValueFactory(new PropertyValueFactory<>("totalNumberOfUnits"));
            this.numberOfInfantryUnitsCol2.setCellValueFactory(new PropertyValueFactory<>("numberOfInfantryUnits"));
            this.numberOfRangedUnitsCol2.setCellValueFactory(new PropertyValueFactory<>("numberOfRangedUnits"));
            this.numberOfCavalryUnitsCol2.setCellValueFactory(new PropertyValueFactory<>("numberOfCavalryUnits"));
            this.numberOfCommanderUnitsCol2.setCellValueFactory(new PropertyValueFactory<>("numberOfCommanderUnits"));
            this.numberOfMageUnitsCol2.setCellValueFactory(new PropertyValueFactory<>("numberOfMageUnits"));



        } catch (IllegalArgumentException e) {
            showAlertDialog(e);
        } catch (URISyntaxException | FileNotFoundException | MalformedURLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method to a fill table view with number of units, both in general
     *  * and for each unit type, as well as the name of an army
     *  * connected to these units
     * @param tableView tableview that is filled
     * @param army army that is connected to the content of the table view
     */
    public void fillTableView(TableView<Army> tableView, Army army) {

        army1TableView.refresh();
        army2TableView.refresh();

        ObservableList<Army> armyObservableList = FXCollections.observableArrayList(
                new Army(army.getName(), army.size(), army.getInfantryUnits().size(),
                        army.getRangedUnits().size(), army.getCavalryUnits().size(),
                        army.getCommanderUnits().size(), army.getMageUnits().size(),army.getAllUnits()));
        tableView.setItems(armyObservableList);



    }

    /**
     *Method to update both the tableview of the armies
     * and the images that represents the units in
     * the armies
     * @param army1 One of the armies that are updated
     * @param army2 The other of the armies that are updated
     * @throws MalformedURLException e
     * @throws FileNotFoundException e
     * @throws URISyntaxException e
     */
    public void updateArmies(Army army1, Army army2) throws MalformedURLException, FileNotFoundException, URISyntaxException {

        army1TableView.refresh();
        army2TableView.refresh();

        fillTableView(army1TableView,army1);
        fillTableView(army2TableView,army2);

        createImagesForArmies(army1, army2);



    }

}
