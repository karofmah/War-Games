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
     *
     * @param name
     */
    public Army(String name) {
        this.name = name;
        if(name.isBlank()) {
            throw new IllegalArgumentException("Please enter a name");
        }
        units=new ArrayList<>();
    }

    /**
     *
     * @param name
     * @param units
     */
    public Army(String name, List<Unit> units) {
        this.name = name;
        if(name.isBlank()) {
            throw new IllegalArgumentException("Please enter a name");
        }
        this.units = units;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param unit
     */
    public void add(Unit unit){
        if(!units.contains(unit)) {
            units.add(unit);
        }
    }

    /**
     *
     * @param units
     */
    public void addAll(List<Unit> units) {
        for (Unit unit : units) {
            this.add(unit);
        }
    }
    public void remove(Unit unit){
        if(units.contains(unit)){
            units.remove(unit);
        }
    }

    /**
     *
     * @return
     */
    public boolean hasUnits(){
        boolean hasUnits=true;
        if(units.isEmpty()){
            hasUnits=false;
        }
        return hasUnits;
    }

    /**
     *
     * @return
     */
    public Unit getRandom(){
        Random randomUnitIndex=new java.util.Random();
        return units.get(randomUnitIndex.nextInt(units.size()));
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return Objects.equals(name, army.name) && Objects.equals(units, army.units);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Army{" +
                "name='" + name + '\'' +
                ", units=" + units +
                '}';
    }

    public int size() {
        return units.size();
    }
}

