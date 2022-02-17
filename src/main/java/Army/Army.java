package Army;

import Units.Unit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Army {
    private String name;
    private List<Unit> units;

    /**
     *Constructor for the class Army
     * @param name of the Army, can not be blank
     */
    public Army(String name) {
        this.name = name;
        if(name.isBlank()) {
            throw new IllegalArgumentException("Please enter a name");
        }
        units=new ArrayList<>();
    }

    /**
     *Constructor for the class Army
     * @param name of the Army, can not be blank
     * @param units in the Army
     */
    public Army(String name, ArrayList<Unit> units) {
        this.name = name;
        if(name.isBlank()) {
            throw new IllegalArgumentException("Please enter a name");
        }
        this.units = units;
        if(units.isEmpty()){
              Army army=new Army(name);
        }
    }

    /**
     *Returns name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *Adds an unit to army
     * @param unit
     */
    public void add(Unit unit){
        if(!units.contains(unit)) {
            units.add(unit);
        }
    }

    /**
     *Adds all units from a list (units) to army
     * @param units
     */
    public void addAll(List<Unit> units) {
        for (Unit unit : units) {
            this.add(unit);
        }
    }

    /**
     * Removes unit from army
     * @param unit
     */
    public void remove(Unit unit){
        if(units.contains(unit)){
            units.remove(unit);
        }
    }

    /**
     *Checks if the army has units
     * @return boolean
     * true or false depending on whether the army has units
     */
    public boolean hasUnits(){
        boolean hasUnits=true;
        if(units.isEmpty()){
            hasUnits=false;
        }
        return hasUnits;
    }


    public List<Unit> getAllUnits(){
        return units;
    }
    /**
     *Returns a random Unit in the army
     * @return Unit
     */
    public Unit getRandom(){
        Random randomUnitIndex=new java.util.Random();
        return units.get(randomUnitIndex.nextInt(units.size()));
    }

    /**
     *Compares to objects
     * @param o
     * @return boolean
     * true or false depending on whether the objects are equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return Objects.equals(name, army.name) && Objects.equals(units, army.units);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }


    @Override
    public String toString() {
        return "Army{" +
                "name='" + name + '\'' +
                ", units=" + units +
                '}';
    }

    /**
     * Returns size of the army
     * @return units.size() as an int
     */
    public int size() {
        return units.size();
    }

}

