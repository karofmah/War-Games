package Army;

import Units.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArmyTest {

    InfantryUnit infantry = new InfantryUnit("Footman", 100);
    CavalryUnit cavalry = new CavalryUnit("Knight", 100);
    RangedUnit ranged = new RangedUnit("Archer", 100);
    CommanderUnit commander = new CommanderUnit("Mountain King", 100);

    Army armyOne=new Army("Blue Side",new ArrayList<Unit>());

    private void fillArmyWithData() {
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
    class testData{

        @Test
        public void ifUnitsAreAddedToArmy() {
            fillArmyWithData();
            assertEquals(60,armyOne.size());
        }
        @Test
        public void ifArmyCanHaveSeveralOfSameUnit(){
            ArrayList<Unit> units=new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                units.add(infantry);
            }
            armyOne.addAll(units);
            assertEquals(armyOne.size(),1);
        }
        @Test
        public void ifUnitCanBeRemovedFromArmy(){

        }
    }
}
