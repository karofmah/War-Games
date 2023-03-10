package wargames.model.units;

/**
 * InfantryUnit-class extends Unit
 * Represents a unit of the type infantry
 */
public class InfantryUnit extends Unit {
    /**
     * Constructor for InfantryUnit
     * @param name   of the unit as a String, can not be blank
     * @param health of the unit as an int, can not be lower than zero
     * @param attack of the unit as an int, can not be lower than zero
     * @param armor  of the unit as an int, can not be lower than zero
     */

    public InfantryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     *Constructor for InfantryUnit
     * @param name of the unit as a String, can not be blank
     * @param health of the unit as a String, can not be lower than zero
     */
    public InfantryUnit(String name,int health){
        super(name,health,15,10);
    }

    /**
     * Constructor for InfantryUnit
     * @param type of the unit as a String  must be a InfantryUnit and can not be blank
     * @param name of the unit as a String, can not be blank
     * @param health of the unit as an int, can not be lower than zero
     */
    public InfantryUnit(String type,String name, int health){super(type,name,health,15,10);}

    /**
     * Returns attack bonus that infantry units will receive
     * @return 2
     */
    @Override
    public int getAttackBonus() {
        return 2;
    }

    /**
     *Returns resist bonus that infantry units will receive
     * @return 1
     */
    @Override
    public int getResistBonus() {
        return 1;
    }

    /**
     * Returns the bonus attack that infantry units will receive when fighting in a forest
     * @param terrain the terrain where the battle is ongoing
     * @return attackBonus
     */
    @Override
    public int getTerrainAttackBonus(String terrain) {
        int attackBonus=0;
        if(terrain.equals("Forest")) {
            attackBonus+=2;
        }
        return attackBonus;
    }

    /**
     * Returns the bonus resist that infantry units will receive when fighting in a forest
     * @param terrain the terrain where the battle is ongoing
     * @return resistBonus
     */
    @Override
    public int getTerrainResistBonus(String terrain) {
        int resistBonus=0;
        if(terrain.equals("FOREST")) {
            resistBonus+=2;
        }
        return resistBonus;
    }

    /**
     * Returns the attack defects that infantry units will receive
     * @param terrain the terrain where the battle is ongoing
     * @return 0
     */
    @Override
    public int getTerrainAttackDefect(String terrain) {
        return 0;
    }
    /**
     * Returns the resist defects that infantry units will receive
     * @param terrain the terrain where the battle is ongoing
     * @return 0
     */
    @Override
    public int getTerrainResistDefect(String terrain) {
        return 0;
    }

}
