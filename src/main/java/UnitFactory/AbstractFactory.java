package UnitFactory;

import Units.Unit;

import java.util.ArrayList;

public interface AbstractFactory<T> {
    T create(T type,String name,int health);
    ArrayList<Unit> unitsOfSpecificType(Unit type, String name, int health, int n);
}
