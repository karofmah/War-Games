package wargames.model.units;

public class RangedUnit extends Unit {
    private int receivedAttack;
    /**
     * Constructor
     *
     * @param name   of the unit as a String, can not be blank
     * @param health of the unit as an int, can not be lower than zero
     * @param attack of the unit as an int, can not be lower than zero
     * @param armor  of the unit as an int, can not be lower than zero
     */
    public RangedUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }
    public RangedUnit(String name, int health) {
        super(name, health, 15, 8);
    }
    public RangedUnit(String type,String name, int health){super(type,name,health);}

    /**
     * Returns the bonus attack that ranged units will receive
     * @return 3
     */
    @Override
    public int getAttackBonus() {
        return 3;
    }

    /**
     * Returns the resist bonus that cavalry units will receive
     * @return resistBonus
     */
    @Override
    public int getResistBonus() {
        int resistBonus=2;
        for(int i = 2; i> receivedAttack; i--){
            resistBonus+=2;
        }
        receivedAttack++;
        return resistBonus;
    }

    /**
     * Returns the bonus attack that ranged units will receive when fighting on a hill
     * @param terrain the terrain where the battle is ongoing
     * @return attackBonus
     */
    @Override
    public int getTerrainAttackBonus(String terrain) {
        int attackBonus=0;
        if (terrain.equals("HILL")){
            attackBonus+=2;
        }
        return attackBonus;
    }

    /**
     * Returns the bonus resist that ranged units will receive when fighting on a hill
     * @param terrain the terrain where the battle is ongoing
     * @return 0
     */
    @Override
    public int getTerrainResistBonus(String terrain) {
        return 0;
    }

    /**
     * Returns the attack defects that ranged units will receive when fighting in a forest
     * @param terrain the terrain where the battle is ongoing
     * @ attackDefect
     */
    @Override
    public int getTerrainAttackDefect(String terrain) {
        int attackDefect=0;
        if (terrain.equals("FOREST")){
            attackDefect-=2;
        }
        return attackDefect;
    }

    /**
     * Returns the resist defects that ranged units will receive
     * @param terrain the terrain where the battle is ongoing
     * @return 0
     */
    @Override
    public int getTerrainResistDefect(String terrain) {
        return 0;
    }


}
