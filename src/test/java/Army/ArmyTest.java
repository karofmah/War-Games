package Army;

import Units.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ArmyTest {
    private InfantryUnit infantry;
    private CavalryUnit cavalry;
    private RangedUnit ranged;
    private CommanderUnit commander;

    private Army army_1;
    private Army army_2;

    @BeforeEach
    @DisplayName("Sets up necessary data before each test")
    public void setup() {
        this.infantry = new InfantryUnit("Footman", 100);
        this.cavalry = new CavalryUnit("Knight", 100);
        this.ranged = new RangedUnit("Archer", 100);
        this.commander = new CommanderUnit("Mountain King", 100);

        this.army_1 = new Army("Blue Side");
        this.army_2 =new Army("Red Side", new ArrayList<Unit>());

        for (int i = 0; i < 10; i++) {
            army_1.add(new InfantryUnit("Footman", 100));
            army_1.add(new RangedUnit("Archer", 100));

        }
        for (int i = 0; i < 5; i++) {
            army_1.add(new CavalryUnit("Knight", 100));
            army_1.add(new CommanderUnit("Mountain King", 100));
        }
    }
    @Nested
    @DisplayName("Performs positive tests")
    class inputIsSupported{

        @Test
        @DisplayName("Tests if units are added to armyOne")
        public void unitsAreAddedToArmy() {
            assertEquals(30, army_1.size());
        }
        @Test
        @DisplayName("Tests if armyOne can recieve several of the same unit")
        public void armyCanNotRecieveSeveralOfSameUnit(){
            ArrayList<Unit> units=new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                units.add(infantry);
            }
            army_1.addAll(units);
            assertEquals(31, army_1.size());
        }
        @Test
        @DisplayName("Tests if it is possible to remove a random unit from armyOne")
        public void randomUnitIsRemovedFromArmy(){
            army_1.remove(army_1.getRandom());
            assertEquals(29, army_1.size());
        }
        @Test
        @DisplayName("Tests if armyOne with units, has units")
        public void armyHasUnits(){
            assertTrue( army_1.hasUnits());
        }
        @Test
        @DisplayName("Tests if armyTwo without units, has units")
        public void armyDoesNotHaveUnits(){
            assertFalse(army_2.hasUnits());
        }
        @Test
        @DisplayName("Tests whether armyOne is represented properly or not")
        public void representArmy(){
            System.out.println(army_1.toString());
        }
        @Test
        @DisplayName("Tests whether the name of ArmyOne is 'Blue Side' ")
        public void representNameofArmy(){
            assertEquals("Blue Side", army_1.getName());
        }

        @Test
        @DisplayName("Tests if infantry units from the army are represented properly")
        public void getInfantryUnits(){
            for (int i = 0; i < 1; i++) {
                army_2.add(new InfantryUnit("Footman",100));
                army_2.add(new RangedUnit("Archer",100));
                army_2.add(new CavalryUnit("Knight",100));
                army_2.add(new CommanderUnit("Mountain King",100));
            }
            assertEquals("[Units.Unit{name='Footman', health=100, attack=15, armor=10}]", army_2.getInfantryUnits().toString());
        }
        @Test
        @DisplayName("Tests if ranged units from an army are represented properly")
        public void getRangedUnits(){
            for (int i = 0; i < 1; i++) {
                army_2.add(new InfantryUnit("Footman", 100));
                army_2.add(new RangedUnit("Archer", 100));
                army_2.add(new CavalryUnit("Knight", 100));
                army_2.add(new CommanderUnit("Mountain King", 100));
            }
            assertEquals("[Units.Unit{name='Archer', health=100, attack=15, armor=8}]", army_2.getRangedUnits().toString());
        }
        @Test
        @DisplayName("Tests if cavalry units from an army are represented properly")
        public void getCavalryUnits() {
            for (int i = 0; i < 1; i++) {
                army_2.add(new InfantryUnit("Footman", 100));
                army_2.add(new RangedUnit("Archer", 100));
                army_2.add(new CavalryUnit("Knight", 100));
                army_2.add(new CommanderUnit("Mountain King", 100));
            }
            assertEquals("[Units.Unit{name='Knight', health=100, attack=20, armor=12}, Units.Unit{name='Mountain King', health=100, attack=25, armor=15}]", army_2.getCavalryUnits().toString());
        }
        @Test
        @DisplayName("Tests if commander units from an army are represented properly")
        public void getCommanderUnits() {
            for (int i = 0; i < 1; i++) {
                army_2.add(new InfantryUnit("Footman", 100));
                army_2.add(new RangedUnit("Archer", 100));
                army_2.add(new CavalryUnit("Knight", 100));
                army_2.add(new CommanderUnit("Mountain King", 100));
            }
            assertEquals("[Units.Unit{name='Mountain King', health=100, attack=25, armor=15}]", army_2.getCommanderUnits().toString());
        }
        @Nested
        @DisplayName("Performs negative tests")
        class inputIsNotSupported{
            @Test
            @DisplayName("Tests if an exception is thrown when creating an army with a empty name")
            public void nameIsBlank(){
                assertThrows(IllegalArgumentException.class,()-> new Army(""));
            }
        }
    }
}
