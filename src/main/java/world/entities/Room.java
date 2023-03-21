package world.entities;

import enums.EntityType;

public class Room extends Entity{
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.type = EntityType.ROOM;
    }


}
