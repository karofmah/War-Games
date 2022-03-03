package Army;

import Units.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ArmyTest {

    InfantryUnit infantry = new InfantryUnit("Footman", 100);
    CavalryUnit cavalry = new CavalryUnit("Knight", 100);
    RangedUnit ranged = new RangedUnit("Archer", 100);
    CommanderUnit commander = new CommanderUnit("Mountain King", 100);

    Army armyOne=new Army("Blue Side",new ArrayList<Unit>());

    private void fillArmyWithTestData() {
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
    class generalTest{

        @Test
        public void unitsAreAddedToArmy() {
            fillArmyWithTestData();
            assertEquals(60,armyOne.size());
        }
        @Test
        public void armyCanRecieveSeveralOfSameUnit(){
            ArrayList<Unit> units=new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                units.add(infantry);
            }
            armyOne.addAll(units);
            assertEquals(1,armyOne.size());
        }
        @Test
        public void randomUnitIsRemovedFromArmy(){
            fillArmyWithTestData();
            armyOne.remove(armyOne.getRandom());
            assertEquals(59,armyOne.size());
        }
        @Test
        public void armyHasUnits(){
            fillArmyWithTestData();
            assertTrue( armyOne.hasUnits());
        }
        @Test
        public void armyDoesNotHaveUnits(){
            assertFalse(armyOne.hasUnits());
        }
        @Test
        public void representArmy(){
            fillArmyWithTestData();
            System.out.println(armyOne.toString());
        }
        @Test
        public void representNameofArmy(){
            System.out.println(armyOne.getName());
        }
        @Nested
        class nameIsNotSupported{
            @Test
            public void nameIsBlank(){
                assertThrows(IllegalArgumentException.class,()-> new Army(""));
                assertThrows(IllegalArgumentException.class,()-> new Army("",new ArrayList<>()));
            }
        }
    }
}
