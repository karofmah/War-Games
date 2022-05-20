package wargames.model.observer;

import wargames.model.army.Army;

public interface Subscriber {
    void updateArmies(Army army1, Army army2) throws InterruptedException;
}
