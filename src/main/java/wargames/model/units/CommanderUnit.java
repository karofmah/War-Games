package wargames.model.units;

/**
 * CommanderUnit-class extends CavalryUnit
 * Represents a unit of the type commander
 */
public class CommanderUnit extends CavalryUnit {
    /**
     * Constructor
     *
     * @param name   of the unit as a String, can not be blank
     * @param health of the unit as an int, can not be lower than zero
     * @param attack of the unit as an int, can not be lower than zero
     * @param armor  of the unit as an int, can not be lower than zero
     */
    public CommanderUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Constructor
     *
     * @param name   of the unit as a String, can not be blank
     * @param health of the unit as an int, can not be lower than zero
     */
    public CommanderUnit(String name, int health) {
        super(name, health,25,15);
    }

    /**
     * Constructor
     * @param type of the unit as a String  must be a CommanderUnit and can not be blank
     * @param name   of the unit as a String, can not be blank
     * @param health of the unit as an int, can not be lower than zero
     */
    public CommanderUnit(String type,String name, int health){super(type,name,health,25,15);}

}
