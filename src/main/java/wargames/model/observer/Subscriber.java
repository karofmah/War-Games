package wargames.model.observer;

import wargames.model.army.Army;

public interface Subscriber {
    void updateGui(Army army1, Army army2) throws InterruptedException;
}
