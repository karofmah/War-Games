public abstract class Unit {
    private String name;
    private int health;
    private int attack;
    private int armor;

    public Unit(String name, int health, int attack, int armor) {
        this.name = name;
        this.health = health;
        if(health<0) throw new IllegalArgumentException("Unit can not have less than 0 health");
        this.attack = attack;
        this.armor = armor;
    }
    public void attack(Unit opponent){
        int opponentHealth=opponent.getHealth()-(this.getAttack() + this.getAttackBonus())+(opponent.getArmor() + opponent.getResistBonus());
        opponent.setHealth(opponentHealth);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getArmor() {
        return armor;
    }

    public void setHealth(int health) {
        this.health = Math.max(health,0);
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
    public abstract int getAttackBonus();

    public abstract int getResistBonus();
}
