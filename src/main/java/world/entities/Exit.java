package world.entities;

import enums.Direction;
import enums.EntityType;

public class Exit extends Entity{

    private Direction direction;
    private Room source;
    private Room destination;
    private boolean locked;

    public Exit(String name, String description, Direction direction, Room source, Room destination) {
        this.direction = direction;
        this.source = source;
        this.destination = destination;
        this.locked = false;
        this.name = name;
        this.description = description;
        this.type = EntityType.EXIT;
    }

    public Direction getDirection() {
        return direction;
    }

    public Room getSource() {
        return source;
    }

    public Room getDestination() {
        return destination;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isLocked() {
        return locked;
    }
}
