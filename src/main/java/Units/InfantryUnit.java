package Units;

import Units.Unit;

public class InfantryUnit extends Unit {
    /**
     * Constructor
     *
     * @param name   of the unit as a String, can not be blank
     * @param health of the unit as an int, can not be lower than zero
     * @param attack of the unit as an int, can not be lower than zero
     * @param armor  of the unit as an int, can not be lower than zero
     */

    public InfantryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     *
     * @param name of the unit as a String, can not be blank
     * @param health of the unit as a String, can not be lower than zero
     */
    public InfantryUnit(String name,int health){
        super(name,health,15,10);
    }

    /**
     * Returns attack bonus
     * @return attackBonus=2
     */
    @Override
    public int getAttackBonus() {
        return 2;
    }

    /**
     *Returns attack bonus
     * @return resistBonus=1
     */
    @Override
    public int getResistBonus() {
        return 1;
    }
}
