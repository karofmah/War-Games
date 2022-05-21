package wargames.model.units;
public abstract class Unit {
    private final String name; //name of the unit
    private int health; //health of the unit
    private final int attack; //attack damage of the unit
    private final int armor; //armor of the unit
    private String type; //type of the unit


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
        if(name.isBlank()) throw new IllegalArgumentException("Please enter a name for the unit");
        this.health = health;
        if(health<=0) throw new IllegalArgumentException("Unit can not have equal to, or less than 0 health");
        this.attack = attack;
        if(attack<0) throw new IllegalArgumentException("Unit can not have less than 0 attack");
        this.armor = armor;
        if(armor<0) throw new IllegalArgumentException("Unit can not have less than 0 armor");
    }
    /**
     * Constructor for the abstract superclass Unit
     * @param type of the unit as a String  must be a RangedUnit and can not be blank
     * @param name   of the unit as a String, can not be blank
     * @param health of the unit as an int, can not be lower than zero
     * @param attack of the unit as an int, can not be lower than zero
     * @param armor  of the unit as an int, can not be lower than zero
     */
    public Unit(String type,String name, int health, int attack,int armor){

        this.type = type;
        if(!type.equals(getClass().getSimpleName()) || type.isBlank()) throw new IllegalArgumentException("Type of the unit must be valid, please write a type that exists");

        this.name=name;
        if(name.isBlank()) throw new IllegalArgumentException("Please enter a name");

        this.health=health;
        if(health<=0) throw new IllegalArgumentException("Unit can not have equal to, or less than 0 health");

        this.attack=attack;
        if(attack<0) throw new IllegalArgumentException("Unit can not have less than 0 attack");

        this.armor=armor;
        if(armor<0) throw new IllegalArgumentException("Unit can not have less than 0 armor");

    }
    /**
     * Attack opponent to lower their health
     * @param opponent opponent that is attacked
     */
    public void attack(Unit opponent,String terrain){
        if(opponent.getArmor()<=this.getAttack()) {
            int opponentHealth = opponent.getHealth() - (this.getAttack() + this.getAttackBonus()
                    +this.getTerrainAttackBonus(terrain)+this.getTerrainAttackDefect(terrain)) +
                    (opponent.getArmor() + opponent.getResistBonus()+opponent.getTerrainResistBonus(terrain)+opponent.getTerrainResistDefect(terrain));
            opponent.setHealth(opponentHealth);
        }

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
     * @param health health of the unit
     */
    public void setHealth(int health) {
        this.health = Math.max(health,0);
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", attack=" + attack +
                ", armor=" + armor +
                '}';
    }

    /**
     * Abstract method for receiving the attack bonus of a unit
     * @return int
     */
    public abstract int getAttackBonus();

    /**
     * Abstract method for receiving the resist bonus of a unit
     * @return int
     */
    public abstract int getResistBonus();

    /**
     * Abstract method for receiving the attack bonus of a unit
     * in a specific terrain
     * @param terrain the terrain where the battle is ongoing
     * @return int
     */
    public abstract int getTerrainAttackBonus(String terrain);

    /**
     * Abstract method for receiving the resist bonus of a unit
     * in a specific terrain
     * @param terrain the terrain where the battle is ongoing
     * @return int
     */
    public abstract int getTerrainResistBonus(String terrain);

    /**
     * Abstract method for receiving the attack defect of a unit
     * in a specific terrain
     * @param terrain the terrain where the battle is ongoing
     * @return int
     */
    public abstract int getTerrainAttackDefect(String terrain);

    /**
     * Abstract method for receiving the resist defect of a unit
     * in a specific terrain
     * @param terrain the terrain where the battle is ongoing
     * @return int
     */

    public abstract int getTerrainResistDefect(String terrain);
}
