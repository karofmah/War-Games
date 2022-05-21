package wargames.model.observer;

import wargames.model.army.Army;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * Subscriber-interface that receives the notification
 * from the publisher, and calls methods
 */
public interface Subscriber {
    /**
     * Method to update armies
     * @param army1 One of the armies
     * @param army2 The other of the armies
     * @throws InterruptedException e
     * @throws MalformedURLException e
     * @throws FileNotFoundException e
     * @throws URISyntaxException e
     */
    void updateArmies(Army army1, Army army2) throws InterruptedException, MalformedURLException, FileNotFoundException, URISyntaxException;
}
