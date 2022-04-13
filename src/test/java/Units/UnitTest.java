package Units;

import UnitFactory.UnitFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {

    private InfantryUnit infantry;
    private CavalryUnit cavalry;
    private RangedUnit ranged;
    private CommanderUnit commander;

    UnitFactory factory;
    @BeforeEach
    @DisplayName("Sets up the necessary data before each test")
    public void setup() {
        this.factory=new UnitFactory();
        this.infantry= (InfantryUnit) factory.create("InfantryUnit","Footman", 100);
        this.cavalry = (CavalryUnit) factory.create("CavalryUnit","Knight", 100);
        this.ranged = (RangedUnit) factory.create("RangedUnit","Archer", 100);
        this.commander = (CommanderUnit) factory.create("CommanderUnit","Mountain King", 100);
    }

    @Nested
    @DisplayName("Performs positive tests")
    class inputIsSupported {

        @Test
        @DisplayName("Tests if the resist bonus of a CommanderUnit is equal to 1")
        public void commanderResistBonus(){
            assertEquals(1,commander.getResistBonus());

        }
        @Test
        @DisplayName("Tests if an attack from a ranged unit to an infantry unit on a hill, lowers their health by right amount")
        public void rangedAttacksInfantryInForest() {
            ranged.attack(infantry,"FOREST");
            int infantryHealthAfterAttack = infantry.getHealth();
            assertEquals(97,infantryHealthAfterAttack);
        }
        @Test
        @DisplayName("Tests if an attack from an infantry unit to an cavalry unit in a forest, lowers their health by right amount")
        public void infantryAttacksCavalryInForest() {
            infantry.attack(cavalry,"FOREST");
            int cavalryHealthAfterAttack = cavalry.getHealth();
            assertEquals(93,cavalryHealthAfterAttack);
        }
        @Test
        @DisplayName("Tests if an attack from a cavalry unit to an ranged unit in plains, lowers their health by right amount")
        public void cavalryAttacksRangedInPlains(){
            cavalry.attack(ranged,"PLAINS");
            int rangedHealthAfterAttack = ranged.getHealth();
            assertEquals(86,rangedHealthAfterAttack);
        }

        public void getTerrainResistDefect(){
            System.out.println(cavalry.getTerrainResistDefect("FOREST"));
        }
        @Test
        @DisplayName("Tests if it is possible to set health below zero")
        public void setHealthBelowZero(){
            infantry.setHealth(-100);
            int infantryHealth = infantry.getHealth();
            assertEquals(0,infantryHealth);
        }
        @Test
        @DisplayName("Tests if the resist bonus of a RangedUnit is changed by right amount")
        public void rangedResistBonus(){
            for (int i = 0; i < 5; i++) {
                if(i==0) {
                    assertEquals(6,ranged.getResistBonus());
                }
                if(i==1){
                    assertEquals(4,ranged.getResistBonus());
                }
                if (i>=2){
                    assertEquals(2,ranged.getResistBonus());
                }
            }

        }
        @Test
        @DisplayName("Tests if the resist bonus of a CavalryUnit is changed by right amount ")
        public void cavalryAttackBonus(){
            for (int i = 0; i < 7; i++) {
                if(i==0) {
                    assertEquals(6,cavalry.getAttackBonus());
                }
                if(i>=1){
                    assertEquals(2,cavalry.getAttackBonus());
                }
            }
        }
        @Test
        @DisplayName("Tests if a Unit can be created through UnitFactory")
        public void createUnitWithFactory(){
            assertEquals("Units.Unit{name='Footman', health=100, attack=15, armor=10}",infantry.toString());
        }
        @Nested
        @DisplayName("Performs negative tests")
        class inputIsNotSupported{
            @Test
            @DisplayName("Tests if an IllegalArgumentException is thrown when creating an InfantryUnit with blank name")
            public void nameIsBlank(){
                assertThrows(IllegalArgumentException.class,()-> new InfantryUnit("",100));

            }
            @Test
            @DisplayName("Tests if an IllegalArgumentException is thrown when creating an InfantryUnit with negative health")
            public void healthIsNegative() {
                assertThrows(IllegalArgumentException.class,()-> new InfantryUnit("Footman", -100));

            }
            @Test
            @DisplayName("Tests if an IllegalArgumentException is thrown when creating an InfantryUnit with negative attack")
            public void attackIsNegative(){
                assertThrows(IllegalArgumentException.class,()-> new InfantryUnit("Footman", 100,-15,10));

            }
            @Test
            @DisplayName("Tests if an IllegalArgumentException is thrown when creating a CommanderUnit with negative armor")
            public void armorIsNegative(){
                assertThrows(IllegalArgumentException.class,()-> new CommanderUnit("Mountain King",100,20,-12));
            }
        }
    }   
}
