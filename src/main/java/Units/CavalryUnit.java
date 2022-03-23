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
        int attack=2;
        if(attackCounter==0){
            attack+=4;
        }
        attackCounter++;

        return attack;
    }

    @Override
    public int getResistBonus() {
        return 1;
    }
}
