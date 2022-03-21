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

    private Army armyOne;
    private Army armyTwo;

    @BeforeEach
    @DisplayName("Sets up necessary data before each test")
    public void setup() {
        this.infantry = new InfantryUnit("Footman", 100);
        this.cavalry = new CavalryUnit("Knight", 100);
        this.ranged = new RangedUnit("Archer", 100);
        this.commander = new CommanderUnit("Mountain King", 100);

        this.armyOne = new Army("Blue Side");
        this.armyTwo=new Army("Red Side", new ArrayList<Unit>());

        for (int i = 0; i < 20; i++) {
            armyOne.add(new InfantryUnit("Footman", 100));
            armyOne.add(new RangedUnit("Archer", 100));
            if (i % 2 == 0) {
                armyOne.add(new CavalryUnit("Knight", 100));
                armyOne.add(new CommanderUnit("Mountain King", 100));
            }
        }
    }
    @Nested
    @DisplayName("Performs positive tests")
    class inputIsSupported{

        @Test
        @DisplayName("Tests if units are added to armyOne")
        public void unitsAreAddedToArmy() {
            assertEquals(60,armyOne.size());
        }
        @Test
        @DisplayName("Tests if armyOne can recieve several of the same unit")
        public void armyCanNotRecieveSeveralOfSameUnit(){
            ArrayList<Unit> units=new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                units.add(infantry);
            }
            armyOne.addAll(units);
            assertEquals(61,armyOne.size());
        }
        @Test
        @DisplayName("Tests if it is possible to remove a random unit from armyOne")
        public void randomUnitIsRemovedFromArmy(){
            armyOne.remove(armyOne.getRandom());
            assertEquals(59,armyOne.size());
        }
        @Test
        @DisplayName("Tests if armyOne with units, has units")
        public void armyHasUnits(){
            assertTrue( armyOne.hasUnits());
        }
        @Test
        @DisplayName("Tests if armyTwo without units, has units")
        public void armyDoesNotHaveUnits(){
            assertFalse(armyTwo.hasUnits());
        }
        @Test
        @DisplayName("Tests whether armyOne is represented properly or not")
        public void representArmy(){
            System.out.println(armyOne.toString());
        }
        @Test
        @DisplayName("Tests whether the name of ArmyOne is 'Blue Side' ")
        public void representNameofArmy(){
            assertEquals("Blue Side",armyOne.getName());
        }

        @Test
        @DisplayName("Tests if infantry units from the army are represented properly")
        public void getInfantryUnits(){
            for (int i = 0; i < 1; i++) {
                armyTwo.add(new InfantryUnit("Footman",100));
                armyTwo.add(new RangedUnit("Archer",100));
                armyTwo.add(new CavalryUnit("Knight",100));
                armyTwo.add(new CommanderUnit("Mountain King",100));
            }
            assertEquals("[Units.Unit{name='Footman', health=100, attack=15, armor=10}]",armyTwo.getInfantryUnits().toString());
        }
        @Test
        @DisplayName("Tests if ranged units from an army are represented properly")
        public void getRangedUnits(){
            for (int i = 0; i < 1; i++) {
                armyTwo.add(new InfantryUnit("Footman", 100));
                armyTwo.add(new RangedUnit("Archer", 100));
                armyTwo.add(new CavalryUnit("Knight", 100));
                armyTwo.add(new CommanderUnit("Mountain King", 100));
            }
            assertEquals("[Units.Unit{name='Archer', health=100, attack=15, armor=8}]",armyTwo.getRangedUnits().toString());
        }
        @Test
        @DisplayName("Tests if cavalry units from an army are represented properly")
        public void getCavalryUnits() {
            for (int i = 0; i < 1; i++) {
                armyTwo.add(new InfantryUnit("Footman", 100));
                armyTwo.add(new RangedUnit("Archer", 100));
                armyTwo.add(new CavalryUnit("Knight", 100));
                armyTwo.add(new CommanderUnit("Mountain King", 100));
            }
            assertEquals("[Units.Unit{name='Knight', health=100, attack=20, armor=12}, Units.Unit{name='Mountain King', health=100, attack=25, armor=15}]",armyTwo.getCavalryUnits().toString());
        }
        @Test
        @DisplayName("Tests if commander units from an army are represented properly")
        public void getCommanderUnits() {
            for (int i = 0; i < 1; i++) {
                armyTwo.add(new InfantryUnit("Footman", 100));
                armyTwo.add(new RangedUnit("Archer", 100));
                armyTwo.add(new CavalryUnit("Knight", 100));
                armyTwo.add(new CommanderUnit("Mountain King", 100));
            }
            assertEquals("[Units.Unit{name='Mountain King', health=100, attack=25, armor=15}]",armyTwo.getCommanderUnits().toString());
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
