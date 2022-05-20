package wargames.controller;

import static wargames.dialogs.Dialogs.showInformationDialog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import wargames.WarGamesApplication;
import wargames.model.army.Army;
import wargames.model.unitfactory.UnitFactory;
import wargames.model.units.Unit;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ArmiesController implements Initializable {

    @FXML
    private TableView<Army> army1TableView;

    @FXML
    private TableView<Army> army2TableView;

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
    private TableColumn<?, ?> numberOfMageUnitsCol1;

    @FXML
    private TableColumn<?, ?> numberOfMageUnitsCol2;

    @FXML
    private TableView<Unit> army1UnitsTableView;

    @FXML
    private TableView<Unit> army2UnitsTableView;

    @FXML
    private TableColumn<?, ?> unitHealthCol1;

    @FXML
    private TableColumn<?, ?> unitHealthCol2;

    @FXML
    private TableColumn<?, ?> unitNameCol1;

    @FXML
    private TableColumn<?, ?> unitNameCol2;

    @FXML
    private TableColumn<?, ?> unitTypeCol1;

    @FXML
    private TableColumn<?, ?> unitTypeCol2;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button readChosenArmyFromFile;

    ArrayList<Unit> unitsList=new ArrayList<>();

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

        scrollPane.setHvalue(0.42);
        createArmies();

        fillArmyTableViews(army1TableView,army1);
        fillArmyTableViews(army2TableView,army2);

        fillUnitTableViews(army1.getAllUnits(),army1UnitsTableView);
        fillUnitTableViews(army2.getAllUnits(),army2UnitsTableView);

        handleUnitsRemoval(army1UnitsTableView,army1TableView,army1);
        handleUnitsRemoval(army2UnitsTableView,army2TableView,army2);

        army1.writeArmyToFile(new File("src/main/resources/Army1File.csv"));

        army2.writeArmyToFile(new File("src/main/resources/Army2File.csv"));

    }
    public void fillArmyTableViews(TableView<Army> armyTableView,Army army){
        army1TableView.refresh();
        army2TableView.refresh();

            armyTableView.getItems().clear();

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



        ObservableList<Army> armyObservableList = FXCollections.observableArrayList(
                new Army(army.getName(), army.size(), army.getInfantryUnits().size(),
                        army.getRangedUnits().size(), army.getCavalryUnits().size(),
                        army.getCommanderUnits().size(),army.getMageUnits().size(),army.getAllUnits()));
        armyTableView.setItems(armyObservableList);
        armyTableView.refresh();

    }
    public void fillUnitTableViews(List<Unit> units,TableView<Unit> unitsTableView) {

        army1UnitsTableView.refresh();
        army2UnitsTableView.refresh();

        unitsList.clear();
        if (unitsTableView != null) {
            unitsTableView.getItems().clear();



        this.unitTypeCol1.setCellValueFactory(new PropertyValueFactory<>("type"));
        this.unitNameCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.unitHealthCol1.setCellValueFactory(new PropertyValueFactory<>("health"));

        this.unitTypeCol2.setCellValueFactory(new PropertyValueFactory<>("type"));
        this.unitNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.unitHealthCol2.setCellValueFactory(new PropertyValueFactory<>("health"));


        ObservableList<Unit> unitsObservableList = FXCollections.observableArrayList();

        if (units != null) {
            for (Unit unit : units) {
                switch (unit.getClass().getSimpleName()) {
                    case "InfantryUnit" -> unitsList.addAll(factory.unitsOfSpecificType("InfantryUnit"
                            , unit.getName(), unit.getHealth(), 1));

                    case "RangedUnit" -> unitsList.addAll(factory.unitsOfSpecificType("RangedUnit"
                            , unit.getName(), unit.getHealth(), 1));

                    case "CavalryUnit" -> unitsList.addAll(factory.unitsOfSpecificType("CavalryUnit"
                            , unit.getName(), unit.getHealth(), 1));

                    case "CommanderUnit" -> unitsList.addAll(factory.unitsOfSpecificType("CommanderUnit"
                            , unit.getName(), unit.getHealth(), 1));

                    case "MageUnit" -> unitsList.addAll(factory.unitsOfSpecificType("MageUnit"
                            , unit.getName(), unit.getHealth(), 1));
                }
            }
            unitsObservableList = FXCollections.observableArrayList(unitsList);

        }

        unitsTableView.setItems(unitsObservableList);
        unitsTableView.refresh();

        }
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
                this.army1.add(factory.create("MageUnit","Sorcerer",100));
                this.army2.add(factory.create("InfantryUnit", "Grunt", 100));
                this.army2.add(factory.create("RangedUnit", "Spearman", 100));
                this.army2.add(factory.create("MageUnit","Sorcerer",100));
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
    public void handleUnitsRemoval(TableView<Unit> unitTableView,TableView<Army> armyTableView,Army army) {
        unitTableView.setRowFactory(table -> {
            TableRow<Unit> row = new TableRow<>();

            row.hoverProperty().addListener(observable -> {//Listen for hover on row
                Unit unit = row.getItem();
                if (row.isHover() && unit != null) {
                    row.setOnMouseEntered(mouseEvent1 -> {//Listen when mouse is hovered over a row
                        unitTableView.setCursor(Cursor.HAND);//Change cursor
                        row.setOnMouseClicked(mouseEvent2 -> { //Listen for click event

                            army.getAllUnits().remove(unit);
                            fillArmyTableViews(armyTableView,army);
                            fillUnitTableViews(army.getAllUnits(),unitTableView);

                        });
                    });
                } else {
                    row.setOnMouseEntered(mouseEvent -> { //Default cursor when row is empty
                        unitTableView.setCursor(Cursor.DEFAULT);
                    });
                }
            });

            return row;
        });
    }



    @FXML
    public void readArmyFromFile() {
        Stage stage = WarGamesApplication.stage;
        showInformationDialog("Remember to right click on the file you want to " +
                        "read from, and decide where you want to open this file");
        FileChooser fileChooser=new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\Bruker\\IdeaProjects\\wargames\\src\\main\\resources"));
        fileChooser.showOpenDialog(stage);

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
