package Units;

public abstract class Unit {
    private String name; //name of the unit
    private int health; //health of the unit
    private int attack; //attack damage of the unit
    private int armor; //armor of the unit

    /**
     * Constructor for the abstract superclass Unit
     *
     * @param name   of the unit as a String, can not be blank
     * @param health of the unit as an int, can not be lower than zero
     * @param attack of the unit as an int, can not be lower than zero
     * @param armor  of the unit as an int, can not be lower than zero
     */
    public Unit(String name, int health, int attack, int armor) {
        this.name = name;
        if(name.isBlank()) throw new IllegalArgumentException("Please enter a name");
        this.health = health;
        if(health<0) throw new IllegalArgumentException("Unit can not have less than 0 health");
        this.attack = attack;
        if(attack<0) throw new IllegalArgumentException("Unit can not have less than 0 attack");
        this.armor = armor;
        if(armor<0) throw new IllegalArgumentException("Unit can not have less than 0 armor");
    }
    public void attack(Unit opponent){
        int opponentHealth=opponent.getHealth()-(this.getAttack() + this.getAttackBonus())+(opponent.getArmor() + opponent.getResistBonus());
        opponent.setHealth(opponentHealth);
    }

    /**
     * Returns name
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * Returns health
     * @return health
     */
    public int getHealth() {
        return health;
    }
    /**
     * Returns attack
     * @return attack
     */
    public int getAttack() {
        return attack;
    }
    /**
     * Returns armor
     * @return armor
     */
    public int getArmor() {
        return armor;
    }

    /**
     * Changes health
     * @param health
     */
    public void setHealth(int health) {
        this.health = Math.max(health,0);
    }

    @Override
    public String toString() {
        return "Units.Unit{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", attack=" + attack +
                ", armor=" + armor +
                '}';
    }

    public abstract int getAttackBonus();

    public abstract int getResistBonus();
}