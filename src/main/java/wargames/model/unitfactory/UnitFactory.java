package wargames.model.unitfactory;

import wargames.model.units.*;


import java.util.ArrayList;
import java.util.Objects;

/**
 * UnitFactory-class that is mainly utilized to create units
 */
public class UnitFactory{
    /**
     * Enum-class that is used to create units
     * Source:
     *https://stackoverflow.com/questions/9712977/is-it-possible-to-have-an-enum-class-with-enums-of-two-or-more-words
     */

    public enum UnitType{
        INFANTRY("InfantryUnit"),
        RANGED("RangedUnit"),
        CAVALRY("CavalryUnit"),
        COMMANDER("CommanderUnit"),
        MAGE("MageUnit");


        private final String toString;

        UnitType(String toString) {
            this.toString = toString;
        }
        @Override
        public String toString(){
            return toString;
        }
    }

    /**
     * Method to create a unit
     * Is utilized in the method unitsOfSpecificType
     * @param type type of the unit
     * @param name name of the unit
     * @param health health of the unit
     * @return new Unit as a Unit
     */
    public Unit create(String type, String name, int health) {
        UnitType typeOfUnit=null;
        for (UnitType unitType:UnitType.values()){
            if(unitType.toString().equals(type)){
                typeOfUnit=unitType;
            }
        }
        switch (Objects.requireNonNull(typeOfUnit)){
            case INFANTRY -> {return new InfantryUnit(type,name,health);}
            case RANGED -> {return new RangedUnit(type,name,health);}
            case CAVALRY -> {return new CavalryUnit(type,name,health);}
            case COMMANDER-> {return new CommanderUnit(type,name,health);}
            case MAGE->{return new MageUnit(type,name,health);}
            default -> throw new IllegalArgumentException("This type of unit does not exist");
        }
    }

    /**
     * Method to create multiple units of same type
     * @param type type of the units
     * @param name name of the units
     * @param health health of the units
     * @param n number of units
     * @return unitsOfType as an ArrayList<Unit>
     */
    public ArrayList<Unit> unitsOfSpecificType(String type, String name, int health, int n){
        ArrayList<Unit> unitsOfType=new ArrayList<>();

    for (int i = 0; i < n; i++) {
        Unit unit=create(type,name,health);
        if(!unitsOfType.contains(unit)) {
            unitsOfType.add(unit);
        }
    }
    return unitsOfType;
    }

}
