package wargames.model.units;

public class CavalryUnit extends Unit {
    private int attackCounter;
    /**
     * Constructor
     *
     * @param name   of the unit as a String, can not be blank
     * @param health of the unit as an int, can not be lower than zero
     * @param attack of the unit as an int, can not be lower than zero
     * @param armor  of the unit as an int, can not be lower than zero
     */
    public CavalryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }
    /**
     * Constructor
     *
     * @param name   of the unit as a String, can not be blank
     * @param health of the unit as an int, can not be lower than zero
     */
    public CavalryUnit(String name, int health) {
        super(name, health, 20, 12);
    }
    public CavalryUnit(String type, String name, int health,int attack,int armor){super(type,name,health,attack,armor);}
    public CavalryUnit(String type,String name, int health){super(type,name,health,20, 12);}
    /**
     * Returns the bonus attack that cavalry units will receive
     * @return attackBonus
     */

    @Override
    public int getAttackBonus() {
        int attackBonus=2;
        if(attackCounter==0){
            attackBonus+=4;
        }
        attackCounter++;

        return attackBonus;
    }

    /**
     * Returns the bonus resist that cavalry units will receive
     * @return 1
     */
    @Override
    public int getResistBonus() {
        return 1;
    }

    /**
     * Returns the bonus attack that cavalry units will receive when fighting in plains
     * @param terrain the terrain where the battle is ongoing
     * @return attackBonus
     */
    @Override
    public int getTerrainAttackBonus(String terrain) {
        int attackBonus=0;
        if(terrain.equals("Plains")){
            attackBonus=2;

        }
        return attackBonus;
    }

    /**
     * Returns the bonus attack that cavalry units will receive when fighting in plains
     * @param terrain the terrain where the battle is ongoing
     * @return 0
     */
    @Override
    public int getTerrainResistBonus(String terrain) {
        return 0;
    }

    /**
     * Returns the attack defects that cavalry units will receive
     * @param terrain the terrain where the battle is ongoing
     * @return 0
     */
    @Override
    public int getTerrainAttackDefect(String terrain) {
        return 0;
    }

    /**
     * Returns the resist defects that cavalry units will receive when fighting in a forest
     * @param terrain the terrain where the battle is ongoing
     * @return resistDefect
     */
    @Override
    public int getTerrainResistDefect(String terrain) {
        int resistDefect=0;
        if(terrain.equals("Forest")){
            resistDefect-=getResistBonus();
        }
        return resistDefect;
    }






}
