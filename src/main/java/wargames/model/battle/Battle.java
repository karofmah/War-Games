package wargames.model;

import wargames.model.units.Unit;

import java.util.ArrayList;


public class Battle {
    private final wargames.model.Army army1;
    private final wargames.model.Army army2;
    private final String terrain;
    private final ArrayList<String> terrains;
    /**
     *Constructor for the class Battle
     * @param army1 one of the armies in a battle
     * @param army2 the other armies in a battle
     */
    public Battle(wargames.model.Army army1, wargames.model.Army army2, String terrain) {
        this.army1 = army1;
        if(!army1.hasUnits()){
            throw new IllegalArgumentException(army1.getName() +" has no units. Please add units before starting battle");
            }
        this.army2 = army2;
        if(!army2.hasUnits()){
            throw new IllegalArgumentException(army1.getName() + " has no units. Please add units before starting battle");
        }
        this.terrains=new ArrayList<>();
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
    public wargames.model.Army simulate(){
        while(army1.hasUnits() && army2.hasUnits()) {
            int numberOfAttacks=0;
            while(numberOfAttacks>=0) {
                Unit armyOneUnit = army1.getRandom();
                Unit armyTwoUnit = army2.getRandom();
                if (numberOfAttacks % 2 == 0) {
                    armyOneUnit.attack(armyTwoUnit,terrain);
                    numberOfAttacks++;
                }
                if (armyTwoUnit.getHealth() == 0) {
                    army2.remove(armyTwoUnit);
                    if (!army2.hasUnits()) {
                        break;
                    }
                }
                if (numberOfAttacks % 2 != 0) {
                    armyTwoUnit.attack(armyOneUnit,terrain);
                    numberOfAttacks++;
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
    public ArrayList <wargames.model.Army> getArmies(){
        ArrayList<wargames.model.Army> armies=new ArrayList<>();

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
