package wargames.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import wargames.WarGamesApplication;
import wargames.model.army.Army;
import wargames.model.unitfactory.UnitFactory;
import wargames.model.units.Unit;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class selectedArmyController implements Initializable {

    @FXML
    public Button backBtn;
    @FXML
    private TableView<Unit> unitsTableView;

    @FXML
    private TableColumn<?, ?> unitHealthCol;

    @FXML
    private TableColumn<?, ?> unitNameCol;

    @FXML
    private TableColumn<?, ?> unitTypeCol;

    @FXML
    private Label teamNameLbl;

    private final ArrayList<Unit> units= new ArrayList<>();

    private ObservableList<Unit> unitObservableList;

    ArrayList<Unit> unitsList = new ArrayList<>();

    UnitFactory factory=new UnitFactory();

    /**
     * Method to change to the armiesView
     */
    @FXML
    void backBtnClicked() {
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
        public void initialize(URL url, ResourceBundle resourceBundle) {

            this.unitTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            this.unitNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            this.unitHealthCol.setCellValueFactory(new PropertyValueFactory<>("health"));
        }

    /**
     * Method to initialize transferred data from armiesView when this view is present
     * @param army that is selected in armiesView
     */
    public void initData(Army army) {
        teamNameLbl.setText(army.getName());
                if (army.getAllUnits() != null) {
                    units.addAll(army.getAllUnits());
                    for (Unit unit:units) {
                        switch (unit.getClass().getSimpleName()) {
                            case "InfantryUnit" -> unitsList.addAll(factory.unitsOfSpecificType("InfantryUnit"
                                    , unit.getName(), unit.getHealth(), 1));

                            case "RangedUnit" -> unitsList.addAll(factory.unitsOfSpecificType("RangedUnit"
                                    , unit.getName(), unit.getHealth(), 1));

                            case "CavalryUnit" -> unitsList.addAll(factory.unitsOfSpecificType("CavalryUnit"
                                    , unit.getName(), unit.getHealth(), 1));

                            case "CommanderUnit" -> unitsList.addAll(factory.unitsOfSpecificType("CommanderUnit"
                                    , unit.getName(), unit.getHealth(), 1));
                        }
                    }
                    unitObservableList = FXCollections.observableArrayList(unitsList);

                }
                this.unitsTableView.setItems(this.unitObservableList);

            }

}

