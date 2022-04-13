package Units;

public class CavalryUnit extends Unit{
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
    public CavalryUnit(String name, int health) {
        super(name, health, 20, 12);
    }
    @Override
    public int getAttackBonus() {
        int attackBonus=2;
        if(attackCounter==0){
            attackBonus+=4;
        }
        attackCounter++;

        return attackBonus;
    }

    @Override
    public int getResistBonus() {
        return 1;
    }

    @Override
    public int getTerrainAttackBonus(String terrain) {
        int attackBonus=0;
        if(terrain.equals("PLAINS")){
            attackBonus=2;

        }
        return attackBonus;
    }

    @Override
    public int getTerrainResistBonus(String terrain) {
        return 0;
    }

    @Override
    public int getTerrainAttackDefect(String terrain) {
        return 0;
    }

    @Override
    public int getTerrainResistDefect(String terrain) {
        int resistDefect=0;
        if(terrain.equals("FOREST")){
            resistDefect-=getResistBonus();
        }
        return resistDefect;
    }






}
