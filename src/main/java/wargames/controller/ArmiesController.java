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
import java.util.Objects;
import java.util.ResourceBundle;

import static wargames.dialogs.Dialogs.showAlertDialog;
import static wargames.dialogs.Dialogs.showInformationDialog;

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
    private javafx.scene.image.ImageView blueCavalryImageView;

    @FXML
    private javafx.scene.image.ImageView blueCommanderImageView;

    @FXML
    private javafx.scene.image.ImageView blueInfantryImageView;

    @FXML
    private javafx.scene.image.ImageView blueMageImageView;

    @FXML
    private javafx.scene.image.ImageView blueRangedImageView;

    @FXML
    private TextField numberOfBlueInfantry;

    @FXML
    private TextField numberOfBlueRanged;

    @FXML
    private TextField numberOfBlueCavalry;

    @FXML
    private TextField numberOfBlueCommander;

    @FXML
    private TextField numberOfBlueMage;

    @FXML
    private javafx.scene.image.ImageView redCavalryImageView;

    @FXML
    private javafx.scene.image.ImageView redCommanderImageView;

    @FXML
    private javafx.scene.image.ImageView redInfantryImageView;

    @FXML
    private javafx.scene.image.ImageView redMageImageView;

    @FXML
    private javafx.scene.image.ImageView redRangedImageView;

    @FXML
    private TextField numberOfRedInfantry;

    @FXML
    private TextField numberOfRedRanged;

    @FXML
    private TextField numberOfRedCavalry;

    @FXML
    private TextField numberOfRedCommander;

    @FXML
    private TextField numberOfRedMage;
    ArrayList<Unit> unitsList=new ArrayList<>();

    UnitFactory factory=new UnitFactory();

    private final Army army1=new Army("Blue side");
    private final Army army2=new Army("Red side");


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
     * Method to highlight images (in imageViews) when hovered by increasing the size,
     * and return to original size when not hovered
     * @param imageView ImageView that the image exists in
     */
    public void unitsHighlighted(javafx.scene.image.ImageView imageView){
        if(imageView!=null) {
            imageView.setOnMouseMoved(e -> {
                imageView.setFitWidth(125);
                imageView.setFitHeight(125);

            });
            imageView.setOnMouseExited(e -> {
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
            });
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

        unitsHighlighted(blueInfantryImageView);
        unitsHighlighted(blueRangedImageView);
        unitsHighlighted(blueCavalryImageView);
        unitsHighlighted(blueCommanderImageView);
        unitsHighlighted(blueMageImageView);

        unitsHighlighted(redInfantryImageView);
        unitsHighlighted(redRangedImageView);
        unitsHighlighted(redCavalryImageView);
        unitsHighlighted(redCommanderImageView);
        unitsHighlighted(redMageImageView);

        fillArmyTableView(army1TableView,army1);
        fillArmyTableView(army2TableView,army2);

        fillUnitTableView(army1.getAllUnits(),army1UnitsTableView);
        fillUnitTableView(army2.getAllUnits(),army2UnitsTableView);


            handleUnitsAdded(blueInfantryImageView, army1, army1UnitsTableView, army1TableView, numberOfBlueInfantry);
            handleUnitsAdded(blueRangedImageView, army1, army1UnitsTableView, army1TableView, numberOfBlueRanged);
            handleUnitsAdded(blueCavalryImageView, army1, army1UnitsTableView, army1TableView, numberOfBlueCavalry);
            handleUnitsAdded(blueCommanderImageView, army1, army1UnitsTableView, army1TableView, numberOfBlueCommander);
            handleUnitsAdded(blueMageImageView, army1, army1UnitsTableView, army1TableView, numberOfBlueMage);


            handleUnitsAdded(redInfantryImageView, army2, army2UnitsTableView, army2TableView, numberOfRedInfantry);
            handleUnitsAdded(redRangedImageView, army2, army2UnitsTableView, army2TableView, numberOfRedRanged);
            handleUnitsAdded(redCavalryImageView, army2, army2UnitsTableView, army2TableView, numberOfRedCavalry);
            handleUnitsAdded(redCommanderImageView, army2, army2UnitsTableView, army2TableView, numberOfRedCommander);
            handleUnitsAdded(redMageImageView, army2, army2UnitsTableView, army2TableView, numberOfRedMage);


        handleUnitsRemoval(army1UnitsTableView,army1TableView,army1);
        handleUnitsRemoval(army2UnitsTableView,army2TableView,army2);

        this.unitTypeCol1.setCellValueFactory(new PropertyValueFactory<>("type"));
        this.unitNameCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.unitHealthCol1.setCellValueFactory(new PropertyValueFactory<>("health"));

        this.unitTypeCol2.setCellValueFactory(new PropertyValueFactory<>("type"));
        this.unitNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.unitHealthCol2.setCellValueFactory(new PropertyValueFactory<>("health"));

        army1.writeArmyToFile(new File("src/main/resources/Army1File.csv"));

        army2.writeArmyToFile(new File("src/main/resources/Army2File.csv"));

    }
    public void handleUnitsAdded(javafx.scene.image.ImageView imageView, Army army,TableView<Unit> unitTableView,TableView<Army> armyTableView,TextField text){

            if (Objects.equals(imageView.getId(), blueInfantryImageView.getId())) {
                imageView.setOnMouseClicked(e -> {
                    if(text.getText().isBlank())
                        showInformationDialog("Please specify how many units of this type you want to add");
                    else {
                        List<Unit> units = factory.unitsOfSpecificType("InfantryUnit", "Footman", 100, Integer.parseInt(text.getText()));
                        army.addAll(units);
                        fillUnitTableView(army.getAllUnits(), unitTableView);
                        fillArmyTableView(armyTableView, army);
                    }
                });
            } else if (Objects.equals(imageView.getId(), blueRangedImageView.getId())) {
                imageView.setOnMouseClicked(e -> {
                    if(text.getText().isBlank())
                        showInformationDialog("Please specify how many units of this type you want to add");
                    else {
                        List<Unit> units = factory.unitsOfSpecificType("RangedUnit", "Archer", 100, Integer.parseInt(text.getText()));
                        army.addAll(units);
                        fillUnitTableView(army.getAllUnits(), unitTableView);
                        fillArmyTableView(armyTableView, army);
                    }
                });
            } else if (Objects.equals(imageView.getId(), blueCavalryImageView.getId())) {
                imageView.setOnMouseClicked(e -> {
                    if(text.getText().isBlank())
                        showInformationDialog("Please specify how many units of this type you want to add");
                    else {
                        List<Unit> units = factory.unitsOfSpecificType("CavalryUnit", "Knight", 100, Integer.parseInt(text.getText()));
                        army.addAll(units);
                        fillUnitTableView(army.getAllUnits(), unitTableView);
                        fillArmyTableView(armyTableView, army);
                    }
                });
            } else if (Objects.equals(imageView.getId(), blueCommanderImageView.getId())) {
                imageView.setOnMouseClicked(e -> {
                    if(text.getText().isBlank())
                        showInformationDialog("Please specify how many units of this type you want to add");
                    else {
                        List<Unit> units = factory.unitsOfSpecificType("CommanderUnit", "Mountain King", 180, Integer.parseInt(text.getText()));
                        army.addAll(units);
                        fillUnitTableView(army.getAllUnits(), unitTableView);
                        fillArmyTableView(armyTableView, army);
                    }
                });
            } else if (Objects.equals(imageView.getId(), blueMageImageView.getId())) {
                imageView.setOnMouseClicked(e -> {
                    if(text.getText().isBlank())
                        showInformationDialog("Please specify how many units of this type you want to add");
                    else {
                        List<Unit> units = factory.unitsOfSpecificType("MageUnit", "Sorcerer", 100, Integer.parseInt(text.getText()));
                        army.addAll(units);
                        fillUnitTableView(army.getAllUnits(), unitTableView);
                        fillArmyTableView(armyTableView, army);

                    }
                });
            } else if (Objects.equals(imageView.getId(), redInfantryImageView.getId())) {
                imageView.setOnMouseClicked(e -> {
                    if(text.getText().isBlank())
                        showInformationDialog("Please specify how many units of this type you want to add");
                    else {
                        List<Unit> units = factory.unitsOfSpecificType("InfantryUnit", "Grunt", 100, Integer.parseInt(text.getText()));
                        army.addAll(units);
                        fillUnitTableView(army.getAllUnits(), unitTableView);
                        fillArmyTableView(armyTableView, army);
                    }
                });
            } else if (Objects.equals(imageView.getId(), redRangedImageView.getId())) {
                imageView.setOnMouseClicked(e -> {
                    if(text.getText().isBlank())
                        showInformationDialog("Please specify how many units of this type you want to add");
                    else {
                        List<Unit> units = factory.unitsOfSpecificType("RangedUnit", "Spearman", 100, Integer.parseInt(text.getText()));
                        army.addAll(units);
                        fillUnitTableView(army.getAllUnits(), unitTableView);
                        fillArmyTableView(armyTableView, army);
                    }
                });

            } else if (Objects.equals(imageView.getId(), redCavalryImageView.getId())) {
                imageView.setOnMouseClicked(e -> {
                    if(text.getText().isBlank())
                        showInformationDialog("Please specify how many units of this type you want to add");
                    else {
                        List<Unit> units = factory.unitsOfSpecificType("CavalryUnit", "Raider", 100, Integer.parseInt(text.getText()));
                        army.addAll(units);
                        fillUnitTableView(army.getAllUnits(), unitTableView);
                        fillArmyTableView(armyTableView, army);
                    }
                });
            } else if (Objects.equals(imageView.getId(), redCommanderImageView.getId())) {
                imageView.setOnMouseClicked(e -> {
                    if(text.getText().isBlank())
                        showInformationDialog("Please specify how many units of this type you want to add");
                    else {
                        List<Unit> units = factory.unitsOfSpecificType("CommanderUnit", "Gul'dan", 180, Integer.parseInt(text.getText()));
                        army.addAll(units);
                        fillUnitTableView(army.getAllUnits(), unitTableView);
                        fillArmyTableView(armyTableView, army);
                    }
                });
            } else if (Objects.equals(imageView.getId(), redMageImageView.getId())) {
                imageView.setOnMouseClicked(e -> {
                    if(text.getText().isBlank())
                        showInformationDialog("Please specify how many units of this type you want to add");
                    else {
                        List<Unit> units = factory.unitsOfSpecificType("MageUnit", "Necromancer", 100, Integer.parseInt(text.getText()));
                        army.addAll(units);
                        fillUnitTableView(army.getAllUnits(), unitTableView);
                        fillArmyTableView(armyTableView, army);
                    }
                });
            }
        }

    /**
     * Method to fill a table view that represents the number of units an army
     * @param armyTableView a table view that is either empty or contains the number of units in an army
     * @param army an army that is connected to a table view that is either empty or contains the number of units in an army
     */
    public void fillArmyTableView(TableView<Army> armyTableView, Army army){
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


    }

    /**
     * Method to fill the table view with units in an army,
     * by showing unit type, name and health for each unit
     * @param units the list of units in an army
     * @param unitsTableView the table view that will be filled with units in an army
     */
    public void fillUnitTableView(List<Unit> units, TableView<Unit> unitsTableView) {

        army1UnitsTableView.refresh();
        army2UnitsTableView.refresh();

        unitsList.clear();
        if (unitsTableView != null) {
            unitsTableView.getItems().clear();

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
            army1.writeArmyToFile(new File("src/main/resources/Army1File.csv"));

            army2.writeArmyToFile(new File("src/main/resources/Army2File.csv"));

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
                            unitTableView.getItems().remove(unit);
                            for (Unit unitInArmy:army.getAllUnits()) {
                                if(unit.getName().equals(unitInArmy.getName())){
                                    army.remove(unitInArmy);
                                    break;
                                }
                            }
                            System.out.println(army1.size());
                            fillArmyTableView(armyTableView,army);
                            fillUnitTableView(army.getAllUnits(),unitTableView);

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
        showInformationDialog("You will now be directed to a directory with the path C:\\Users\\Bruker\\IdeaProjects\\wargames\\src\\main\\resources");
        showInformationDialog("Please remember to right click on the file you want to " +
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
