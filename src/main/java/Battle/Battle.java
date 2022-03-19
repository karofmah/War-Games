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
        if(!armyOne.hasUnits()){
            throw new IllegalArgumentException("This army has no units. Please add units before starting battle");
            }
        this.armyTwo = armyTwo;
        if(!armyTwo.hasUnits()){
            throw new IllegalArgumentException("This army has no units. Please add units before starting battle");
        }
    }

    /**
     * Simulates the battle between army one and army two
     * Returns the winner
     * @return Army
     */
    public Army simulate(){
        int counter=0;
        while(armyOne.hasUnits() && armyTwo.hasUnits()) {
            Unit armyOneUnit = armyOne.getRandom();
            Unit armyTwoUnit = armyTwo.getRandom();
            if(counter%2==0){
                armyOneUnit.attack(armyTwoUnit);
                counter++;
            }
            if(armyTwoUnit.getHealth()==0){
                armyTwo.remove(armyTwoUnit);
                if(!armyTwo.hasUnits()){
                    break;
                }
            }
            if(counter%2!=0){
                armyTwoUnit.attack(armyOneUnit);
                counter++;
            }
            if(armyOneUnit.getHealth()==0){
                armyOne.remove(armyOneUnit);
                if(!armyOne.hasUnits()) {
                    break;
                }
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
