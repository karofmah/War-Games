package Battle;

import Army.Army;
import Units.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


public class BattleTest {
    private InfantryUnit infantry;
    private CavalryUnit cavalry;
    private RangedUnit ranged;
    private CommanderUnit commander;
    private InfantryUnit infantry_1;
    private CavalryUnit cavalry_1;
    private RangedUnit ranged_1;
    private CommanderUnit commander_1;

    private Army armyOne;
    private Army armyTwo;

    private Battle battle;
    @BeforeEach
    @DisplayName("Sets up necessary data before each test")
            public void setup() {
        this.infantry = new InfantryUnit("Footman", 100);
        this.cavalry = new CavalryUnit("Knight", 100);
        this.ranged = new RangedUnit("Archer", 100);
        this.commander = new CommanderUnit("Mountain King", 100);

        this.infantry_1 = new InfantryUnit("Grunt", 100);
        this.cavalry_1= new CavalryUnit("Raider", 100);
        this.ranged_1 = new RangedUnit("Spearman", 100);
        this.commander_1 = new CommanderUnit("Gul´dan", 100);

        this.armyOne = new Army("Blue Side");
        this.armyTwo=new Army("Red Side", new ArrayList<Unit>());

        for (int i =0 ; i < 20; i++) {
            this.armyOne.add(new InfantryUnit("Footman", 100));
            this.armyOne.add(new RangedUnit("Archer", 100));
            this.armyTwo.add(new InfantryUnit("Grunt", 100));
            this.armyTwo.add(new RangedUnit("Spearman", 100));

            if (i % 2 == 0) {
                this.armyOne.add(new CavalryUnit("Knight", 100));
                this.armyOne.add(new CommanderUnit("Mountain King", 100));
                this.armyTwo.add(new CavalryUnit("Raider", 100));
                this.armyTwo.add(new CommanderUnit("Gul´dan", 100));
            }
        }
        this.battle=new Battle(armyOne,armyTwo);
    }

    @Nested
    @DisplayName("Perform positive tests")
    public class inputIsSupported {}
        @Test
        @DisplayName("Tests if the battle, and therefore the armies, are represented")
        public void representArmies() {
            System.out.println(battle.toString());
        }

        @Test
        @DisplayName("Tests if the battle is performed properly ")
        public void testSimulation() {
            for (int i = 0; i <100 ; i++) {
                setup();
                System.out.println(battle.simulate());
                System.out.println(armyOne.size());
                System.out.println(armyTwo.size());
            }
        }

    @Nested
    @DisplayName("Perform negative tests")
    public class inputIsNotSupported{

        @Test
        @DisplayName("Tests if a battle can be created when armyOne is empty")
        public void armyOneIsEmpty(){
            armyOne.getAllUnits().clear();
            assertThrows(IllegalArgumentException.class,()->new Battle(armyOne,armyTwo));
        }
        @Test
        @DisplayName("Test if a battle can be created when armyTwo is empty")
        public void armyTwoIsEmpty(){
            armyTwo.getAllUnits().clear();
            assertThrows(IllegalArgumentException.class,()->new Battle(armyOne,armyTwo));
        }

    }

}
