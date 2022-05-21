package wargames.model.units;

/**
 * MageUnit-class extends Unit
 * Represents a unit of the type mage
 */
public class MageUnit extends Unit{

private int attackCounter;
private int receivedAttack;

    /**
     * Constructor for MageUnit
     * @param name name   of the unit as a String, can not be blank
     * @param health of the unit as an int, can not be lower than zero
     * @param attack of the unit as an int, can not be lower than zero
     * @param armor of the unit as an int, can not be lower than zero
     */
    public MageUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }
    /**
     * Constructor for MageUnit
     * @param name name   of the unit as a String, can not be blank
     * @param health of the unit as an int, can not be lower than zero
     */
    public MageUnit(String name, int health) {
        super(name, health, 13,6);
    }
    /**
     * Constructor for MageUnit
     * @param type of the unit as a String  must be a MageUnit and can not be blank
     * @param name  of the unit as a String, can not be blank
     * @param health of the unit as an int, can not be lower than zero
     */
    public MageUnit(String type, String name,int health) {
        super(type, name, health ,13,6);
    }

    /**
     * Returns bonus attack that the mage unit will receive
     * @return attackBonus as an int
     */
    @Override
    public int getAttackBonus() {
        int attackBonus=3;
        if(attackCounter==3){
            attackBonus+=4;
        }
        attackCounter++;

        return attackBonus;
    }

    /**
     * Returns bonus resist that the mage unit will receive
     * @return resistBonus as an Int
     */
    @Override
    public int getResistBonus() {
        int resistBonus=3;
        for(int i = 1; i> receivedAttack; i--){
            resistBonus+=1;
        }
        receivedAttack++;
        return resistBonus;
    }

    /**
     * Returns the bonus attack that mage units will receive when fighting on a hill
     * @param terrain the terrain where the battle is ongoing
     * @return attackBonus
     */
    @Override
    public int getTerrainAttackBonus(String terrain) {
        int attackBonus=0;
        if (terrain.equals("Hill")){
            attackBonus+=3;
        }
        return attackBonus;

    }
    /**
     * Returns the bonus resist that mage units will receive
     * @param terrain the terrain where the battle is ongoing
     * @return 0
     */
    @Override
    public int getTerrainResistBonus(String terrain) {
        return 0;
    }
    /**
     * Returns the attack defects that mage units will receive when fighting in a forest
     * @param terrain the terrain where the battle is ongoing
     * @return attackDefect
     */
    @Override
    public int getTerrainAttackDefect(String terrain) {
        int attackDefect=0;
        if (terrain.equals("Forest")){
            attackDefect-=2;
        }
        return attackDefect;
    }
    /**
     * Returns the resist defects that mage units will receive
     * @param terrain the terrain where the battle is ongoing
     * @return 0
     */
    @Override
    public int getTerrainResistDefect(String terrain) {
        return 0;
    }
}
