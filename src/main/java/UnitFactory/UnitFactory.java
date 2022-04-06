package UnitFactory;

import Units.*;

import java.util.ArrayList;


public class UnitFactory implements AbstractFactory<Unit>{

    @Override
    public Unit create(String type, String name, int health){
        if(type.getClass().getSimpleName().equalsIgnoreCase("InfantryUnit") && "Footman".equalsIgnoreCase(name) && health==100){
            return new InfantryUnit(name,health);
        }
        else if (type.getClass().getSimpleName().equalsIgnoreCase("RangedUnit") && "Archer".equalsIgnoreCase(name) && health==100){
            return new RangedUnit(name,health);
        }
        else if (type.getClass().getSimpleName().equalsIgnoreCase("CavalryUnit") && "Knight".equalsIgnoreCase(name) && health==100){
            return new CavalryUnit(name,health);
        }
        else if (type.getClass().getSimpleName().equalsIgnoreCase("CommanderUnit") && "Mountain king".equalsIgnoreCase(name) && health==100) {
            return new CommanderUnit(name, health);
        }else
            return null;
    }
    public ArrayList<Unit> unitsOfSpecificType(String type, String name, int health,int n){
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
