package Battle;

import Army.Army;
import Units.Unit;

import java.util.ArrayList;
import java.util.Locale;

public class Battle {
    private Army army1;
    private Army army2;
    private String terrain;
    private ArrayList<String> terrains=new ArrayList<>();
    /**
     *Constructor for the class Battle
     * @param army1
     * @param army2
     */
    public Battle(Army army1, Army army2, String terrain) {
        this.army1 = army1;
        if(!army1.hasUnits()){
            throw new IllegalArgumentException(army1.getName() +" has no units. Please add units before starting battle");
            }
        this.army2 = army2;
        if(!army2.hasUnits()){
            throw new IllegalArgumentException(army1.getName() + " This army has no units. Please add units before starting battle");
        }
        terrains.add("FOREST");
        terrains.add("HILL");
        terrains.add("PLAINS");
        this.terrain=terrain.toUpperCase();

        if(!terrains.contains(terrain)){
            throw new IllegalArgumentException("The terrain chosen for this battle does not exist. " +
                    "Please choose one of the following terrains: FOREST, HILL, PLAINS");
        }
    }

    /**
     * Simulates the battle between army one and army two
     * Returns the winner
     * @return Army
     */
    public Army simulate(){
        while(army1.hasUnits() && army2.hasUnits()) {
            for (int i = 0; i>=0;) {

                Unit armyOneUnit = army1.getRandom();
                Unit armyTwoUnit = army2.getRandom();
                if (i % 2 == 0) {
                    armyOneUnit.attack(armyTwoUnit);
                    i++;
                }
                if (armyTwoUnit.getHealth() == 0) {
                    army2.remove(armyTwoUnit);
                    if (!army2.hasUnits()) {
                        break;
                    }
                }
                if (i % 2 != 0) {
                    armyTwoUnit.attack(armyOneUnit);
                    i++;
                }
                if (armyOneUnit.getHealth() == 0) {
                    army1.remove(armyOneUnit);
                    if (!army1.hasUnits()) {
                        break;
                    }
                }
            }
        }

        return army1.hasUnits() ? army1 : army2;
    }

    @Override
    public String toString() {
        return "Battle{" +
                "armyOne=" + army1 +
                ", armyTwo=" + army2 +
                '}';
    }
}
