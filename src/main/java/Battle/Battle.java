package Battle;

import Army.Army;
import Units.Unit;

public class Battle {
    private Army armyOne;
    private Army armyTwo;

    /**
     *Constructor for the class Battle
     * @param armyOne
     * @param armyTwo
     */
    public Battle(Army armyOne, Army armyTwo) {
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
    }

    /**
     * Simulates the battle between army one and army two
     * @return winner
     */
    public Army simulate(){
        Army winner = null;
        int counter=0;
        while(true) {
            Unit armyOneUnit = armyOne.getRandom();
            Unit armyTwoUnit = armyTwo.getRandom();
            if(counter%2==0){
                armyOneUnit.attack(armyTwoUnit);
                counter++;
            }
            if(armyTwoUnit.getHealth()==0){
                armyTwo.remove(armyTwoUnit);
            }
            if(counter%2!=0){
                armyTwoUnit.attack(armyOneUnit);
                counter++;
            }
            if(armyOneUnit.getHealth()==0){
                armyOne.remove(armyOneUnit);
            }
            if((!armyOne.hasUnits() || !armyTwo.hasUnits())){
                break;
            }

        }

        return armyOne.hasUnits() ? armyOne : armyTwo;
    }

    @Override
    public String toString() {
        return "Battle{" +
                "armyOne=" + armyOne +
                ", armyTwo=" + armyTwo +
                '}';
    }
}
