package Units;

public class RangedUnit extends Unit {
    private int recievedAttack;
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

    @Override
    public int getAttackBonus() {
        return 3;
    }

    @Override
    public int getResistBonus() {
        int resistBonus=2;
        for(int i=2;i>recievedAttack;i--){
            resistBonus+=2;
        }
        recievedAttack++;
        return resistBonus;
    }

    @Override
    public int getTerrainAttackBonus(String terrain) {
        int attackBonus=0;
        if (terrain.equals("HILL")){
            attackBonus+=2;
        }
        return attackBonus;
    }

    @Override
    public int getTerrainResistBonus(String terrain) {
        return 0;
    }

    @Override
    public int getTerrainAttackDefect(String terrain) {
        int attackDefect=0;
        if (terrain.equals("HILL")){
            attackDefect-=2;
        }
        return attackDefect;
    }

    @Override
    public int getTerrainResistDefect(String terrain) {
        return 0;
    }


}
