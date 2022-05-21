package wargames.model.observer;

import wargames.model.army.Army;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Publisher-class that notifies subscribers when they are notified
 */
public class Publisher {

    List<Subscriber> subscribers;

    /**
     * Constructor for Publisher
     */
    public Publisher(){
        subscribers=new ArrayList<>();
    }

    /**
     * Method to add subscriber to the publisher,
     * so that the subscriber is aware of
     * the notifications
     * @param subscriber subscriber of the publisher
     */
    public void addSubscriber(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    /**
     * Method to remove subscriber from the publisher,
     * so that the subscriber is no longer
     * aware of the notifications
     * @param subscriber subscriber of the publisher
     */
    public void removeSubscriber(Subscriber subscriber){
        subscribers.remove(subscriber);
    }

    /**
     * Method to notify all subscribers
     * Each subscriber will call the
     * method to update armies
     * @param army1 One of the armies
     * @param army2 the other of the armies
     */
    public void notify(Army army1,Army army2){
        subscribers.forEach(subscriber -> {
            try {
                subscriber.updateArmies(army1,army2);
            } catch (InterruptedException | MalformedURLException | FileNotFoundException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
    }

    }
