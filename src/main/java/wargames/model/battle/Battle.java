package wargames.model.battle;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.util.Duration;
import wargames.WarGamesApplication;
import wargames.controller.SelectedArmyController;
import wargames.controller.SimulationController;
import wargames.model.army.Army;
import wargames.model.observer.Publisher;
import wargames.model.observer.Subscriber;
import wargames.model.units.Unit;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Battle extends Publisher {
    private final Army army1;
    private final Army army2;
    private final String terrain;

    //TODO: Javadoc
    public enum Terrains{
        FOREST("Forest"),
        HILL("Hill"),
        PLAINS("Plains");

        private final String toString;

        Terrains(String toString) {
            this.toString = toString;
        }
        @Override
        public String toString(){
            return toString;
        }
    }

    /**
     *Constructor for the class Battle
     * @param army1 one of the armies in a battle
     * @param army2 the other armies in a battle
     */
    public Battle(Army army1, Army army2, String terrain) {
        this.army1 = army1;
        if(!army1.hasUnits()){
            throw new IllegalArgumentException(army1.getName() +" has no units. Please reset battle before starting battle");
            }
        this.army2 = army2;
        if(!army2.hasUnits()){
            throw new IllegalArgumentException(army2.getName() + " has no units. Please reset battle before starting battle");
        }

            if (terrain!=null && (terrain.equals(Terrains.FOREST.toString()) ||
                    terrain.equals(Terrains.HILL.toString()) || terrain.equals(Terrains.PLAINS.toString()))) {
                this.terrain = terrain;
            }
            else {throw new IllegalArgumentException("The terrain chosen for this battle does not exist. " +
                        "Please choose one of the following terrains: Forest, Hill, Plains");
            }

        }


    /**
     * Simulates the battle between army one and army two
     * Returns the winner
     * @return Army
     */
    public Army simulate() throws IOException, InterruptedException {

        while(army1.hasUnits() && army2.hasUnits()) {

            int numberOfAttacks=0;
            while(numberOfAttacks>=0) {

                Unit army1Unit = army1.getRandom();
                Unit army2Unit = army2.getRandom();
                if (numberOfAttacks % 2 == 0) {
                    army1Unit.attack(army2Unit,terrain);

                    numberOfAttacks++;
                }
                if (army2Unit.getHealth() == 0) {
                    army2.remove(army2Unit);
                    notify(army1,army2);
                    if (!army2.hasUnits()) {
                        break;
                    }
                }
                army2Unit.attack(army1Unit,terrain);
                numberOfAttacks++;
                if (army1Unit.getHealth() == 0) {
                    army1.remove(army1Unit);
                    notify(army1,army2);
                    if (!army1.hasUnits()) {
                        break;
                    }
                }
            }
        }

        return army1.hasUnits() ? army1 : army2;
    }


    //TODO: Javadoc
    public ArrayList <Army> getArmies(){
        ArrayList<Army> armies=new ArrayList<>();

        armies.add(army1);
        armies.add(army2);

        return armies;
    }

    @Override
    public String toString() {
        return "Battle{" +
                "army1=" + army1 +
                ", army2=" + army2 +
                ", terrain='" + terrain +
                '}';
    }
}
