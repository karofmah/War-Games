package Units;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {


    InfantryUnit infantry = new InfantryUnit("Footman", 100);
    CavalryUnit cavalry = new CavalryUnit("Knight", 100);
    RangedUnit ranged = new RangedUnit("Archer", 100);
    CommanderUnit commander = new CommanderUnit("Mountain King", 100);


    @Nested
    class generalTest {

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
        public void resistBonusIsChangedByRightAmount(){
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
        public void attackBonusIsChangedByRightAmount(){
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
        class nameOrHealthIsNotSupported{
            @Test
            public void nameIsBlank(){
                assertThrows(IllegalArgumentException.class,()-> new InfantryUnit("",100));
            }
            @Test
            public void healthIsNegative() {
                assertThrows(IllegalArgumentException.class,()-> new RangedUnit("Archer", -100));
            }
            @Test
            public void attackIsNegative(){
                assertThrows(IllegalArgumentException.class,()-> new CavalryUnit("Knight",100,-20,12));
            }
            @Test
            public void ArmorIsNegative(){
                assertThrows(IllegalArgumentException.class,()-> new CommanderUnit("Knight",100, 25,-15));
            }
        }
    }   
}
