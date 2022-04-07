package Battle;

import Army.Army;
import UnitFactory.UnitFactory;
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
    private InfantryUnit infantry1;
    private CavalryUnit cavalry1;
    private RangedUnit ranged1;
    private CommanderUnit commander1;

    private Army army1;
    private Army army2;

    private Battle battle;

    @BeforeEach
    @DisplayName("Sets up necessary data before each test")
    public void setup() {
        //UnitFactory factory=new UnitFactory();
        //this.infantry= (InfantryUnit) factory.create("InfantryUnit","Footman",100);
        this.infantry = new InfantryUnit("Footman", 100);
        this.cavalry = new CavalryUnit("Knight", 100);
        this.ranged = new RangedUnit("Archer", 100);
        this.commander = new CommanderUnit("Mountain King", 100);

        this.infantry1 = new InfantryUnit("Grunt", 100);
        this.cavalry1 = new CavalryUnit("Raider", 100);
        this.ranged1 = new RangedUnit("Spearman", 100);
        this.commander1 = new CommanderUnit("Gul´dan", 100);

        this.army1 = new Army("Blue Side");
        this.army2 =new Army("Red Side", new ArrayList<Unit>());

        for (int i =0 ; i < 10; i++) {
            this.army1.add(new InfantryUnit("Footman", 100));
            this.army1.add(new RangedUnit("Archer", 100));
            this.army2.add(new InfantryUnit("Grunt", 100));
            this.army2.add(new RangedUnit("Spearman", 100));


        }
        for (int i=0; i<5; i++) {
            this.army1.add(new CavalryUnit("Knight", 100));
            this.army1.add(new CommanderUnit("Mountain King", 100));
            this.army2.add(new CavalryUnit("Raider", 100));
            this.army2.add(new CommanderUnit("Gul´dan", 100));
        }
        this.battle=new Battle(army1, army2,"FOREST");
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
            for (int i = 0; i <10 ; i++) {
                setup();
                System.out.println(battle.simulate());
                System.out.println(army1.size());
                System.out.println(army2.size());
            }
        }

    @Nested
    @DisplayName("Perform negative tests")
    public class inputIsNotSupported{

        @Test
        @DisplayName("Tests if a battle can be created when armyOne is empty")
        public void armyOneIsEmpty(){
            army1.getAllUnits().clear();
            assertThrows(IllegalArgumentException.class,()->new Battle(army1, army2,"FOREST"));
        }
        @Test
        @DisplayName("Tests if a battle can be created when armyTwo is empty")
        public void armyTwoIsEmpty(){
            army2.getAllUnits().clear();
            assertThrows(IllegalArgumentException.class,()->new Battle(army1, army2,"HILL"));
        }
        @Test
        @DisplayName("Test if a battle can be created in a terrain that does not exist")
        public void terrainDoesNotExist(){
            assertThrows(IllegalArgumentException.class,()->new Battle(army1,army2,"RIVER"));
        }

    }

}
