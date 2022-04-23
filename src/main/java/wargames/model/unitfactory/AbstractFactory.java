package wargames.model.unitfactory;

import wargames.model.units.Unit;

import java.util.ArrayList;

public interface AbstractFactory<T> {
    T create(String type,String name,int health);
    ArrayList<Unit> unitsOfSpecificType(String type, String name, int health, int n);
}
