package Battle;

import Army.Army;
import Units.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class BattleTest {
    InfantryUnit infantry = new InfantryUnit("Footman", 100);
    CavalryUnit cavalry = new CavalryUnit("Knight", 100);
    RangedUnit ranged = new RangedUnit("Archer", 100);
    CommanderUnit commander = new CommanderUnit("Mountain King", 100);

    InfantryUnit infantry_1=new InfantryUnit("Grunt",100);
    CavalryUnit cavalry_1=new CavalryUnit("Raider",100);
    RangedUnit ranged_1=new RangedUnit("Spearman",100);
    CommanderUnit commander_1=new CommanderUnit("Gul´dan",100);


    Army armyOne=new Army("Blue Side",new ArrayList<Unit>());
    Army armyTwo=new Army("Red Side", new ArrayList<Unit>());
    private void fillArmyWithTestData() {
        for (int i = 0; i <= 20; i++) {
            armyOne.add(new InfantryUnit("Footman", 100));
            armyOne.add(new RangedUnit("Archer", 100));
            armyTwo.add(new InfantryUnit("Grunt",100));
            armyTwo.add(new RangedUnit("Spearman",100));

            if (i % 2 == 0) {
                armyOne.add(new CavalryUnit("Knight", 100));
                armyOne.add(new CommanderUnit("Mountain King", 100));
                armyTwo.add(new CavalryUnit("Raider",100));
                armyTwo.add(new CommanderUnit("Gul´dan",100));
            }
        }
    }
    Battle game=new Battle(armyOne,armyTwo);
    @Test
    public void representArmies(){
        fillArmyWithTestData();
        System.out.println(game.toString());
    }

    @Test
    public void testSimulation(){
        fillArmyWithTestData();
        System.out.println(game.simulate());
        System.out.println(armyOne.size());
        System.out.println(armyTwo.size());

    }

}
