package wargames.model.battle;


import wargames.model.army.Army;
import wargames.model.observer.Publisher;
import wargames.model.units.Unit;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Battle-class to manage the battle between armies
 */
public class Battle extends Publisher {
    private final Army army1;
    private final Army army2;
    private final String terrain;

    /**
     * Enum-class to manage terrains
     * Used in the Battle-constructor
     */
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
     * @param army2 the other of  the armies in a battle
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
     * Simulates the battle between two armies. One random unit from each army is set up
     * to attack each other. When the blows are done, another two random units
     * have to fight.
     * Returns the winner
     * @return Army
     */
    public Army simulate() throws IOException, InterruptedException, URISyntaxException {

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


                }
                if (!army2.hasUnits()) {
                    notify(army1,army2);
                    break;
                }
                army2Unit.attack(army1Unit,terrain);
                numberOfAttacks++;

                if (army1Unit.getHealth() == 0) {
                    army1.remove(army1Unit);

                }
                if (!army1.hasUnits()) {
                    notify(army1,army2);
                    break;
                }
            }
        }

        return army1.hasUnits() ? army1 : army2;
    }

    /**
     * toString-method
     */
    @Override
    public String toString() {
        return "Battle{" +
                "army1=" + army1 +
                ", army2=" + army2 +
                ", terrain='" + terrain +
                '}';
    }
}
