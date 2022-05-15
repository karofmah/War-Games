package wargames.model.battle;


import wargames.model.army.Army;
import wargames.model.units.Unit;

import java.io.IOException;
import java.util.ArrayList;


public class Battle {
    private final Army army1;
    private final Army army2;
    private String terrain;

    //TODO: Javadoc
    public enum Terrains{
        FOREST("Forest"),
        HILL("Hill"),
        PLAINS("Plains");

        private final String toString;

        Terrains(String toString) {
            this.toString = toString;
        }
        @Override
        public String toString(){
            return toString;
        }
    }

    /**
     *Constructor for the class Battle
     * @param army1 one of the armies in a battle
     * @param army2 the other armies in a battle
     */
    public Battle(Army army1, Army army2, String terrain) {
        this.army1 = army1;
        if(!army1.hasUnits()){
            throw new IllegalArgumentException(army1.getName() +" has no units. Please reset battle before starting battle");
            }
        this.army2 = army2;
        if(!army2.hasUnits()){
            throw new IllegalArgumentException(army2.getName() + " has no units. Please reset battle before starting battle");
        }

            if (terrain!=null && (terrain.equals(Terrains.FOREST.toString()) ||
                    terrain.equals(Terrains.HILL.toString()) || terrain.equals(Terrains.PLAINS.toString()))) {
                this.terrain = terrain;
            }
            else {throw new IllegalArgumentException("The terrain chosen for this battle does not exist. " +
                        "Please choose one of the following terrains: Forest, Hill, Plains");
            }
        }


    /**
     * Simulates the battle between army one and army two
     * Returns the winner
     * @return Army
     */
    public Army simulate() throws IOException, InterruptedException {

        while(army1.hasUnits() && army2.hasUnits()) {
            //Trying to update number of units on each side during battle (Does not work yet):

            /*URL fxmlLocation = getClass().getResource("/wargames/simulationView.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent FrontPageParent = loader.load();
            SimulationController controller = loader.getController();
            Stage stage = WarGamesApplication.stage;
            stage.getScene().setRoot(FrontPageParent);*/

            int numberOfAttacks=0;
            while(numberOfAttacks>=0) {

                Unit armyOneUnit = army1.getRandom();
                Unit armyTwoUnit = army2.getRandom();
                if (numberOfAttacks % 2 == 0) {
                    armyOneUnit.attack(armyTwoUnit,terrain);

                    numberOfAttacks++;
                }
                if (armyTwoUnit.getHealth() == 0) {
                    army2.remove(armyTwoUnit);
                    if (!army2.hasUnits()) {
                        break;
                    }
                }
                armyTwoUnit.attack(armyOneUnit,terrain);
                numberOfAttacks++;
                if (armyOneUnit.getHealth() == 0) {
                    army1.remove(armyOneUnit);


                    if (!army1.hasUnits()) {
                        break;
                    }
                }
            }
        }

        return army1.hasUnits() ? army1 : army2;
    }
    public ArrayList <Army> getArmies(){
        ArrayList<Army> armies=new ArrayList<>();

        armies.add(army1);
        armies.add(army2);

        return armies;
    }

    @Override
    public String toString() {
        return "Battle{" +
                "army1=" + army1 +
                ", army2=" + army2 +
                ", terrain='" + terrain +
                '}';
    }
}
