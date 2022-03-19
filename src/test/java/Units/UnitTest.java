package Units;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {


    InfantryUnit infantry = new InfantryUnit("Footman", 100);
    CavalryUnit cavalry = new CavalryUnit("Knight", 100);
    RangedUnit ranged = new RangedUnit("Archer", 100);
    CommanderUnit commander = new CommanderUnit("Mountain King", 100);

//Add display name for all tests
    //remove unnecessary asserts.

    @Nested
    class generalTest {
        public void getCorrectInfantryStats(){
            assertEquals(infantry.getArmor(),100);
            assertEquals(infantry.getAttack(),15);
            assertEquals(infantry.getArmor(),10);
            assertEquals(infantry.getAttackBonus(),2);
            assertEquals(infantry.getHealth(),1);

        }
        @Test
        public void correctRangedStats(){
            assertEquals(ranged.getHealth(),100);
            assertEquals(ranged.getAttack(),15);
            assertEquals(ranged.getArmor(),8);
            assertEquals(ranged.getAttackBonus(),3);
        }
        @Test
        public void correctCavalryStats(){
            assertEquals(cavalry.getHealth(),100);
            assertEquals(cavalry.getAttack(),20);
            assertEquals(cavalry.getArmor(),12);
            assertEquals(cavalry.getResistBonus(),1);
        }
        @Test
        public void correctCommanderStats(){
            assertEquals(commander.getHealth(),100);
            assertEquals(commander.getAttack(),20);
            assertEquals(commander.getArmor(),12);
            assertEquals(commander.getResistBonus(),1);
        }
        @Test
        public void attackOpponentLowersTheirHealthByRightAmount() {
            int infantryHealthBeforeAttack = infantry.getHealth();
            ranged.attack(infantry);
            int infantryHealthAfterAttack = infantry.getHealth();
            assertEquals(93,infantryHealthAfterAttack);
        }
        @Test
        public void ifSetHealthBelowZeroIsValidAfterAttack(){

            infantry.setHealth(-100);
            int infantryHealth = infantry.getHealth();
            assertTrue(infantryHealth==0);
        }
        @Test
        public void rangedResistBonusIsChangedByRightAmount(){
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
        public void cavalryAttackBonusIsChangedByRightAmount(){
            for (int i = 0; i < 7; i++) {
                if(i==0) {
                    assertEquals(6,cavalry.getAttackBonus());
                }
                if(i>=1){
                    assertEquals(2,cavalry.getAttackBonus());
                }
            }
        }
        @Nested
        class inputNotSupported{
            @Test
            public void nameIsBlank(){
                assertThrows(IllegalArgumentException.class,()-> new InfantryUnit("",100));
                assertThrows(IllegalArgumentException.class,()-> new RangedUnit("", 100));
                assertThrows(IllegalArgumentException.class,()-> new CavalryUnit("", 100));
                assertThrows(IllegalArgumentException.class,()-> new CommanderUnit("", 100));
            }
            @Test
            public void healthIsNegative() {
                assertThrows(IllegalArgumentException.class,()-> new InfantryUnit("Footman", -100));
                assertThrows(IllegalArgumentException.class,()-> new RangedUnit("Archer", -100));
                assertThrows(IllegalArgumentException.class,()-> new CavalryUnit("Knight", -100));
                assertThrows(IllegalArgumentException.class,()-> new CommanderUnit("Mountain King", -100));
            }
            @Test
            public void attackIsNegative(){
                assertThrows(IllegalArgumentException.class,()-> new InfantryUnit("Footman", 100,-15,10));
                assertThrows(IllegalArgumentException.class,()-> new RangedUnit("Archer",100,-15,8));
                assertThrows(IllegalArgumentException.class,()-> new CavalryUnit("Knight",100,-20,12));
                assertThrows(IllegalArgumentException.class,()-> new CommanderUnit("Mountain King",100,-20,12));
            }
            @Test
            public void ArmorIsNegative(){
                assertThrows(IllegalArgumentException.class,()-> new InfantryUnit("Footman",100,15,-10));
                assertThrows(IllegalArgumentException.class,()-> new RangedUnit("Archer",100,25,-8));
                assertThrows(IllegalArgumentException.class,()-> new CavalryUnit("Knight",100,20,-12));
                assertThrows(IllegalArgumentException.class,()-> new CommanderUnit("Mountain King",100,20,-12));
            }
        }
    }   
}
