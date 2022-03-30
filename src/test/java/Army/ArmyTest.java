package Army;

import Units.*;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ArmyTest {
    private InfantryUnit infantry;
    private CavalryUnit cavalry;
    private RangedUnit ranged;
    private CommanderUnit commander;

    private Army army1;
    private Army army2;

    @BeforeEach
    @DisplayName("Sets up necessary data before each test")
    public void setup() {
        infantry = new InfantryUnit("Footman", 100);
        cavalry = new CavalryUnit("Knight", 100);
        ranged = new RangedUnit("Archer", 100);
        commander = new CommanderUnit("Mountain King", 100);

        army1 = new Army("Blue Side", new ArrayList<Unit>());
        army2 = new Army("Red Side", new ArrayList<Unit>());

        for (int i = 0; i < 5; i++) {
            army1.add(new CavalryUnit("Knight", 100));
            army1.add(new CommanderUnit("Mountain King", 100));
        }
        for (int i=0;i<10;i++){
            army1.add(new InfantryUnit("Footman",100));
            army1.add(new RangedUnit("Archer",100));
        }

    }

    @Nested
    @DisplayName("Performs positive tests")
    class inputIsSupported {

        @Test
        @DisplayName("Tests if units are added to armyOne")
        public void unitsAreAddedToArmy() {
            assertEquals(30, army1.size());
        }

        @Test
        @DisplayName("Tests if armyOne can recieve several of the same unit")
        public void armyCanNotRecieveSeveralOfSameUnit() {
            ArrayList<Unit> units = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                units.add(infantry);
            }
            army1.addAll(units);
            assertEquals(31, army1.size());

        }

        @Test
        @DisplayName("Tests if it is possible to remove a random unit from armyOne")
        public void randomUnitIsRemovedFromArmy() {
            army1.remove(army1.getRandom());
            assertEquals(29, army1.size());
        }

        @Test
        @DisplayName("Tests if army_1 with units, has units")
        public void armyHasUnits() {
            assertTrue(army1.hasUnits());
        }

        @Test
        @DisplayName("Tests if army_2 without units, has units")
        public void armyDoesNotHaveUnits() {
            System.out.println(army2.getAllUnits().toString());
            assertFalse(army2.hasUnits());

        }

        @Test
        @DisplayName("Tests whether army_1 is represented properly or not")
        public void representArmy() {
            System.out.println(army1.toString());
        }

        @Test
        @DisplayName("Tests whether the name of army_1 is 'Blue Side' ")
        public void representNameofArmy() {
            assertEquals("Blue Side", army1.getName());
        }

        @Test
        @DisplayName("Tests if infantry units from the army are represented properly")
        public void getInfantryUnits() {
            for (int i = 0; i < 1; i++) {
                army2.add(new InfantryUnit("Footman", 100));
                army2.add(new RangedUnit("Archer", 100));
                army2.add(new CavalryUnit("Knight", 100));
                army2.add(new CommanderUnit("Mountain King", 100));
            }
            assertEquals("[Units.Unit{name='Footman', health=100, attack=15, armor=10}]", army2.getInfantryUnits().toString());
        }

        @Test
        @DisplayName("Tests if ranged units from an army are represented properly")
        public void getRangedUnits() {
            for (int i = 0; i < 1; i++) {
                army2.add(new InfantryUnit("Footman", 100));
                army2.add(new RangedUnit("Archer", 100));
                army2.add(new CavalryUnit("Knight", 100));
                army2.add(new CommanderUnit("Mountain King", 100));
            }
            assertEquals("[Units.Unit{name='Archer', health=100, attack=15, armor=8}]", army2.getRangedUnits().toString());
        }

        @Test
        @DisplayName("Tests if cavalry units from an army are represented properly")
        public void getCavalryUnits() {
            System.out.println(army2.getCavalryUnits());
            for (int i = 0; i < 1; i++) {
                army2.add(new InfantryUnit("Footman", 100));
                army2.add(new RangedUnit("Archer", 100));
                army2.add(new CavalryUnit("Knight", 100));
                army2.add(new CommanderUnit("Mountain King", 100));
            }
            assertEquals("[Units.Unit{name='Knight', health=100, attack=20, armor=12}, Units.Unit{name='Mountain King', health=100, attack=25, armor=15}]", army2.getCavalryUnits().toString());
        }

        @Test
        @DisplayName("Tests if commander units from an army are represented properly")
        public void getCommanderUnits() {
            for (int i = 0; i < 1; i++) {
                army2.add(new InfantryUnit("Footman", 100));
                army2.add(new RangedUnit("Archer", 100));
                army2.add(new CavalryUnit("Knight", 100));
                army2.add(new CommanderUnit("Mountain King", 100));
            }
            assertEquals("[Units.Unit{name='Mountain King', health=100, attack=25, armor=15}]", army2.getCommanderUnits().toString());
        }
        @Test
        @DisplayName("Tests if army1 is written to the file ArmyFile.csv")
        public void writeArmyToFile(){
            army1.writeArmyToFile(new File("src/main/resources/ArmyFile.csv"));//Assert
        }
        @Test
        @DisplayName("Tests if the file armyFile.csv is read properly")
        public void readArmyFromFile(){
            army1.readArmyFromFile(new File("src/main/resources/ArmyFile.csv"));
        }

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
