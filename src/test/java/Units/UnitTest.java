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
    class testData {

        @Test
        public void ifAttackOpponentLowersTheirHealthByRightAmount() {
            int infantryHealthBeforeAttack = infantry.getHealth();
            ranged.attack(infantry);
            int infantryHealthAfterAttack = infantry.getHealth();
            assertTrue(infantryHealthAfterAttack==93);
        }
        @Test
        public void ifHealthBelowZeroIsValid(){
            infantry.setHealth(-100);
            int infantryHealth = infantry.getHealth();
            assertTrue(infantryHealth==0);
        }
        @Test
        public void ifResistBonusIsChangedByRightAmount(){
            for (int i = 0; i < 7; i++) {
                System.out.println(ranged.getResistBonus());
            }
        }
        @Test
        public void ifAttackBonusIsChangedByRightAmount(){
            for (int i = 0; i < 7; i++) {
                System.out.println(cavalry.getAttackBonus());
            }
        }
        @Nested
        class validateData{
            @Test
            public void ifExceptionIsThrownWhenNameIsBlank(){
                assertThrows(IllegalArgumentException.class,()-> new InfantryUnit("",100));
            }
            @Test
            public void ifExceptionIsThrownWhenHealthIsBelowZero() {
                assertThrows(IllegalArgumentException.class,()-> new RangedUnit("Archer", -100));
            }
            @Test
            public void ifExceptionIsThrownWhenAttackIsBelowZero(){
                assertThrows(IllegalArgumentException.class,()-> new CavalryUnit("Knight",100,-20,12));
            }
            @Test
            public void ExceptionIsThrownWhenAttackIsBelowZero(){
                assertThrows(IllegalArgumentException.class,()-> new CommanderUnit("Knight",100, 25,-15));
            }
        }
    }   
}
