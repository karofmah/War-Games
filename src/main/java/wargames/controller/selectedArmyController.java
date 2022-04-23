package wargames.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import wargames.model.Army;
import wargames.model.units.Unit;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class selectedArmyController {
    @FXML
    private TableView<Unit> unitsTableView;

    @FXML
    private Button backBtn;

    @FXML
    private Label teamNameLbl;

    private Army selectedArmy;

    private ArrayList<Unit> units= new ArrayList<>();

    private ObservableList<Unit> unitObservableList;

    @FXML
    void backBtnClicked() {

    }
    /*public void initialize(URL url, ResourceBundle resourceBundle) {

        this.teamNameCol.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        this.teamRegister = new TeamRegister();


        this.teamRegister = tournamentDAO.getTeamsGivenTournamentId(Tournament.getSelectedTournamentID());
        this.teamObservableList = FXCollections.observableArrayList(this.teamRegister.getTeams());
        this.teamsTableView.setItems(this.teamObservableList);

        selectedText.setText(this.tournamentDAO.getTournamentById(Tournament.getSelectedTournamentID()).getName());

    public void initData(Army army) {
        selectedArmy = army;
        if (selectedArmy != null) {
            this.units.addAll(selectedArmy.getAllUnits());
        }
        this.unitObservableList = FXCollections.observableArrayList(this.units);
        this.unitsTableView.setItems(this.unitObservableList);
    }*/
}
