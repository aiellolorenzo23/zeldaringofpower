package world.entities;

import enums.EntityType;

import java.util.ArrayList;

public class NPC extends Creature{
    public String dialogue;

    public NPC(String name, String description, String dialogue, Room location) {
        super();
        this.dialogue = dialogue;
        this.name = name;
        this.description = description;
        this.type = EntityType.NPC;
        this.location = location;
    }

    public String getDialogue() {
        return dialogue;
    }

    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }

    public void Look_NPC() {
        Look();
        System.out.println(name+" has the following items:");
        if (Show(new ArrayList<>(), EntityType.ITEM) == 0) {
            System.out.println("No items.");
        }
    }

}
