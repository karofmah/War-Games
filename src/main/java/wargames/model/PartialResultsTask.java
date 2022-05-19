package wargames.model;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableView;
import wargames.model.army.Army;

public class PartialResultsTask extends Task<ObservableList<Army>> {
    private final Army army;
    private final TableView<Army> armyTableView;

    private final ObservableList<Army> partialResults;

    public PartialResultsTask(Army army, TableView<Army> armyTableView, ObservableList<Army> partialResults) {
        this.army = army;
        this.armyTableView=armyTableView;
        this.partialResults = partialResults;
    }


    public final ObservableList<Army> getPartialResults() {
        return partialResults;
    }

    @Override
    protected ObservableList<Army> call() {
        updateMessage("Updating tableview");
        for (int i = 0; i < 383; i++) {
            if (isCancelled()) break;
            Platform.runLater(() -> partialResults.add(new Army(army.getName(), army.size(), army.getInfantryUnits().size(),
                    army.getRangedUnits().size(), army.getCavalryUnits().size(),
                    army.getCommanderUnits().size(),army.getMageUnits().size(), army.getAllUnits())));
            armyTableView.setItems(getPartialResults());
            updateProgress(i, 383);
            System.out.println("Task done");
        }
        return partialResults;
    }
}
