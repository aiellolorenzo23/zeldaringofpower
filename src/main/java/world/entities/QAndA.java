package world.entities;

import enums.EntityType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QAndA extends NPC{

    Map<Integer,String> riddles = new HashMap<>();
    Map<Integer,String> answers = new HashMap<>();

    public QAndA(String name, String description, String dialogue, Room location) {
        super(name, description, dialogue, location);
    }

    public void setRiddles(Map<Integer, String> riddles) {
        this.riddles = riddles;
    }

    public void setAnswers(Map<Integer, String> answers) {
        this.answers = answers;
    }

    public void Look_QAndA() {
        Look();
        System.out.println(name+" has the following items:");
        if (Show(new ArrayList<>(), EntityType.ITEM) == 0) {
            System.out.println("No items.");
        }
        System.out.println(name+" has "+8+" riddles left");
    }
}
