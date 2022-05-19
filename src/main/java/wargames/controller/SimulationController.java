package wargames.controller;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import wargames.WarGamesApplication;
import wargames.model.army.Army;
import wargames.model.battle.Battle;
import wargames.model.observer.Subscriber;
import wargames.model.unitfactory.UnitFactory;
import wargames.model.units.*;


import javax.swing.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static wargames.dialogs.Dialogs.showAlertDialog;

public class SimulationController implements Subscriber {


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
    private TableColumn<?,?> numberOfMageUnitsCol1;

    @FXML
    private TableColumn<?,?> numberOfMageUnitsCol2;

    @FXML
    private TableView<Army> army1TableView;

    @FXML
    private TableView<Army> army2TableView;



    private ObservableList<Army> army1ObservableList;

    private ObservableList<Army> army2ObservableList;

    private ArrayList<ImageView> listOfImageViewsArmy1;

    private ArrayList<ImageView> listOfImageViewsArmy2;

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
     * Linked to the start battle button
     *
     * @param army1 one of the armies in the battle
     * @param army2 the other of the armies in the battle
     */
    public void startBattle(Army army1, Army army2) {
        try {
            Battle battle = new Battle(army1, army2, terrainComboBox.getValue());
            battle.addSubscriber(this);
            Army winner = battle.simulate();
            battle.removeSubscriber(this);
            announcedWinnerLabel.setText(winner.getName() + " won the battle!");
            createShapesForArmies(army1, army2);



        } catch (IllegalArgumentException | IOException | InterruptedException|URISyntaxException e) {
            showAlertDialog(e);

        }


    }

    /**
     * Method to create circles that represent the units in the armies
     *
     * @param army1 one of the armies in a battle
     * @param army2 the other of the armies in a battle
     */
    public void createShapesForArmies(Army army1, Army army2) throws URISyntaxException, FileNotFoundException, MalformedURLException {

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
     * Method to reset a battle that has finished between two armies
     *
     * @param army1 one of the armies in the battle
     * @param army2 the other of the armies in a battle
     */
    public void resetBattle(Army army1, Army army2) {
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
            updateArmies(army1, army2);
            createShapesForArmies(army1, army2);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (MalformedURLException | FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method to initialize transferred data from armiesView when this view is present
     *
     * @param army1 one of the armies in a battle
     * @param army2 the other of the armies in a battle
     */
    public void initData(Army army1, Army army2) {
        try {

            updateArmies(army1, army2);

            startBattleBtn.setOnMouseClicked(mouseEvent ->
                    startBattle(army1, army2));

            resetBattleBtn.setOnMouseClicked(mouseEvent -> resetBattle(army1, army2));

            addTerrainsToComboBox();

            createShapesForArmies(army1, army2);



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

    public void fillTableView(TableView<Army> tableView, Army army) {
        ObservableList<Army> armyObservableList = FXCollections.observableArrayList(
                new Army(army.getName(), army.size(), army.getInfantryUnits().size(),
                        army.getRangedUnits().size(), army.getCavalryUnits().size(),
                        army.getCommanderUnits().size(), army.getMageUnits().size(),army.getAllUnits()));
        tableView.setItems(armyObservableList);


    }

    public void updateArmies(Army army1, Army army2) {

        army1TableView.refresh();
        army2TableView.refresh();

        fillTableView(army1TableView,army1);
        fillTableView(army2TableView,army2);

        System.out.println("Army updated");

    }

    public void delay(Army army1,Army army2) {

/*
        PartialResultsTask task1=new PartialResultsTask(army1, army1TableView,army1ObservableList);
        Thread thread1=new Thread(task1);
        thread1.start();
        PartialResultsTask task2=new PartialResultsTask(army2,army2TableView, army2ObservableList);
        Thread thread2=new Thread(task2);
        thread2.start();

    }
    public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
        System.out.println("updated");
    }*/
    }

    public void updateGui(Army army1, Army army2) {

        //delay(1000, () -> updateArmies(army1,army2));

        updateArmies(army1,army2);
        System.out.println("updated before");
        int army1Size=army1.size();
        int army2Size=army2.size();
        System.out.println(army1Size);
        System.out.println(army2Size);


        PauseTransition pause=new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e-> System.out.println("Pause finished"));

       Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(new KeyFrame(Duration.ZERO, e->updateArmies(army1,army2)),new KeyFrame(Duration.seconds(1),e->pause.play()));

        timeline.setCycleCount(1);
        timeline.setOnFinished(e-> System.out.println("Timeline finished"));

        timeline.play();
        System.out.println("updated after");




    }



}
