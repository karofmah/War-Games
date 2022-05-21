package wargames.model.observer;

import javafx.animation.Timeline;
import wargames.model.army.Army;
import wargames.model.battle.Battle;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Publisher {

    List<Subscriber> subscribers;

    public Publisher(){
        subscribers=new ArrayList<>();
    }

    public void addSubscriber(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber){
        subscribers.remove(subscriber);
    }

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
