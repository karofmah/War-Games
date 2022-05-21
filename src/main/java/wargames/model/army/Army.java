package wargames.model.army;


import wargames.model.units.*;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Army {
    private final String name;
    private final List<Unit> units;
    private int totalNumberOfUnits;
    private int numberOfInfantryUnits;
    private int numberOfRangedUnits;
    private int numberOfCavalryUnits;
    private int numberOfCommanderUnits;
    private int numberOfMageUnits;
    /**
     *Constructor for the class Army
     * @param name name of the Army, can not be blank
     */
    public Army(String name) {
        this.name = name;
        if(name.isBlank()) {
            throw new IllegalArgumentException("Please enter a name");
        }
        units= new ArrayList<>();
    }

    /**
     *Constructor for the class Army
     * @param name name of the Army, can not be blank
     * @param units units in the Army
     */
    public Army(String name, List<Unit> units) {
        this.name = name;
        if(name.isBlank()) {
            throw new IllegalArgumentException("Please enter a name");
        }
        this.units = units;
        if(units.isEmpty()){
              Army army=new Army(name);
        }
    }
    public Army(String name,int totalNumberOfUnits,int numberOfInfantryUnits,int numberOfRangedUnits,int numberOfCavalryUnits,
                int numberOfCommanderUnits,int numberOfMageUnits,List<Unit> units){
        this.name=name;
        this.totalNumberOfUnits=totalNumberOfUnits;
        this.numberOfInfantryUnits=numberOfInfantryUnits;
        this.numberOfRangedUnits=numberOfRangedUnits;
        this.numberOfCavalryUnits=numberOfCavalryUnits;
        this.numberOfCommanderUnits=numberOfCommanderUnits;
        this.numberOfMageUnits=numberOfMageUnits;
        this.units=units;
    }


    /**
     *Returns name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns list of infantry units in army
     * @return infantryUnitList
     */
    public List<Unit> getInfantryUnits(){
        return units.stream().filter(p->p instanceof InfantryUnit).collect(Collectors.toList());
    }

    /**
     * Returns list of ranged units in army
     * @return RangedUnitList
     */
    public List<Unit> getRangedUnits(){
        return units.stream().filter(p->p instanceof RangedUnit).collect(Collectors.toList());
    }

    /**
     * Returns list of cavalry units in army
     * @return CavalryUnitsList
     */
    public List<Unit> getCavalryUnits(){

        return units.stream().filter(p->p instanceof CavalryUnit).collect(Collectors.toList());
    }

    /**
     * Returns list of commander units in army
     * @return CommanderUnitList
     */
    public List<Unit> getCommanderUnits(){

        return units.stream().filter(p->p instanceof CommanderUnit).collect(Collectors.toList());
    }
    public List<Unit> getMageUnits(){
        return units.stream().filter(p->p instanceof MageUnit).collect(Collectors.toList());
    }

    public int getTotalNumberOfUnits() {
        return totalNumberOfUnits;
    }

    public int getNumberOfInfantryUnits() {
        return numberOfInfantryUnits;
    }

    public int getNumberOfRangedUnits() {
        return numberOfRangedUnits;
    }

    public int getNumberOfCavalryUnits() {
        return numberOfCavalryUnits;
    }

    public int getNumberOfCommanderUnits() {
        return numberOfCommanderUnits;
    }

    public int getNumberOfMageUnits(){
        return numberOfMageUnits;
    }
    /**
     *Adds an unit to army
     * @param unit a unit outside the army
     */
    public void add(Unit unit){
        if(!units.contains(unit)) {
            units.add(unit);
        }
    }

    /**
     *Adds all units from a list (units) to army
     * @param units a list of units
     */
    public void addAll(List<Unit> units) {
        for (Unit unit : units) {
            this.add(unit);
        }
    }

    /**
     * Removes unit from army
     * @param unit unit in an army
     */
    public void remove(Unit unit){
        units.remove(unit);
    }

    /**
     *Checks if the army has units
     * @return boolean
     * true or false depending on whether the army has units
     */
    public boolean hasUnits(){
        return !units.isEmpty();
    }


    public List<Unit> getAllUnits(){
        return units;
    }
    /**
     *Returns a random Unit in the army
     * @return Unit
     */
    public Unit getRandom(){
        Random randomIndexUnit= new Random();
        return units.get(randomIndexUnit.nextInt(units.size()));
    }

    /**
     *Compares to objects
     * @param o an object
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
//TODO:Javadoc
    public void writeArmyToFile( File file){
        try(PrintWriter writer=new PrintWriter(file)){
            writer.println(name);
            for (Unit unit : units){
                writer.printf("%s,%s,%s\n", unit.getClass().getSimpleName(), unit.getName(), unit.getHealth());
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    //TODO: Javadoc
    public String readArmyFromFile(File file){
        StringBuilder textFromFile= new StringBuilder();
        try(Scanner scanner=new Scanner(file)){
            while(scanner.hasNext()){
                String line=scanner.nextLine();
                textFromFile.append(line).append("\n");
            }
            return textFromFile.toString();

        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}

