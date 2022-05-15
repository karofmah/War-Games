package wargames.model.unitfactory;

import wargames.model.units.*;


import java.util.ArrayList;
import java.util.Objects;


public class UnitFactory implements AbstractFactory<Unit>{

    //https://stackoverflow.com/questions/9712977/is-it-possible-to-have-an-enum-class-with-enums-of-two-or-more-words
    public enum UnitType{
        INFANTRY("InfantryUnit"),
        RANGED("RangedUnit"),
        CAVALRY("CavalryUnit"),
        COMMANDER("CommanderUnit");


        private final String toString;

        UnitType(String toString) {
            this.toString = toString;
        }
        @Override
        public String toString(){
            return toString;
        }
    }
    @Override
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
            default -> throw new IllegalArgumentException("This type of unit does not exist");
        }
    }
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
