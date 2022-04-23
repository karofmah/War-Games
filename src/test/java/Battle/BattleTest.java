package Battle;

import wargames.model.Army;
import wargames.model.unitfactory.UnitFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import wargames.model.Battle;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class BattleTest {


    private Army army1;
    private Army army2;

    private Battle battle;

    private UnitFactory factory;

    @BeforeEach
    @DisplayName("Sets up necessary data before each test")
    public void setup() {
        try {
            this.factory = new UnitFactory();
            this.army1 = new Army("Blue Side");
            this.army2 = new Army("Red Side", new ArrayList<>());

            for (int i = 0; i < 10; i++) {
                this.army1.add(factory.create("InfantryUnit", "Footman", 100));
                this.army1.add(factory.create("RangedUnit", "Archer", 100));
                this.army2.add(factory.create("InfantryUnit", "Grunt", 100));
                this.army2.add(factory.create("RangedUnit", "Spearman", 100));

            }
            for (int i = 0; i < 5; i++) {
                this.army1.add(factory.create("CavalryUnit", "Knight", 100));
                this.army1.add(factory.create("CommanderUnit", "Mountain King", 100));
                this.army2.add(factory.create("CavalryUnit", "Raider", 100));
                this.army2.add(factory.create("CommanderUnit", "Gul´dan", 100));
            }
            this.battle = new Battle(army1, army2, "FOREST");
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
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
        public void simulateBattle() {
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
