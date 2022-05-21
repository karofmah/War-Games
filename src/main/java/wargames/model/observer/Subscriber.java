package wargames.model.observer;

import wargames.model.army.Army;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public interface Subscriber {
    void updateArmies(Army army1, Army army2) throws InterruptedException, MalformedURLException, FileNotFoundException, URISyntaxException;
}
