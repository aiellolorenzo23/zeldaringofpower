package world.entities;

import enums.EntityType;

public class Boss extends Monster{
    public Boss(String name, String description, Room location) {
        super(name, description, location);
        this.type = EntityType.GANON;
    }
}
