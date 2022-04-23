package wargames.model.unitfactory;

import wargames.model.units.*;

import java.util.ArrayList;


public class UnitFactory implements AbstractFactory<Unit>{

    @Override
    public Unit create(String type, String name, int health) {
        if(type.equalsIgnoreCase("InfantryUnit")){
            return new InfantryUnit(name,health);
        }
        else if (type.equalsIgnoreCase("RangedUnit")){
            return new RangedUnit(name,health);
        }
        else if (type.equalsIgnoreCase("CavalryUnit")){
            return new CavalryUnit(name,health);
        }
        else if (type.equalsIgnoreCase("CommanderUnit")){
            return new CommanderUnit(name, health);
        }else
        return null;
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
