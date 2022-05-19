package wargames.model.units;

public class MageUnit extends Unit{

private int attackCounter;
private int receivedAttack;

    public MageUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }
    public MageUnit(String name, int health) {
        super(name, health, 13,6);
    }
    public MageUnit(String type, String name,int health) {
        super(type, name, health ,13,6);
    }

    public void stun(Unit opponent,int millis) throws InterruptedException {
        opponent.wait(millis);
    }

    @Override
    public int getAttackBonus() {
        int attackBonus=3;
        if(attackCounter==3){
            attackBonus+=4;
        }
        attackCounter++;

        return attackBonus;
    }

    @Override
    public int getResistBonus() {
        int resistBonus=3;
        for(int i = 1; i> receivedAttack; i--){
            resistBonus+=1;
        }
        receivedAttack++;
        return resistBonus;
    }

    @Override
    public int getTerrainAttackBonus(String terrain) {
        int attackBonus=0;
        if (terrain.equals("HILL")){
            attackBonus+=3;
        }
        return attackBonus;

    }

    @Override
    public int getTerrainResistBonus(String terrain) {
        return 0;
    }

    @Override
    public int getTerrainAttackDefect(String terrain) {
        int attackDefect=0;
        if (terrain.equals("FOREST")){
            attackDefect-=2;
        }
        return attackDefect;
    }

    @Override
    public int getTerrainResistDefect(String terrain) {
        return 0;
    }
}
