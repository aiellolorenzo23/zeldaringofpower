package world.entities;

import enums.Direction;
import enums.EntityType;
import world.globals.Globals;

public class Monster extends Creature{
    public Direction position;
    public int life;

    public Monster(String name, String description, Room location) {
        UpdatePiratePosition();
        this.life = 3;
        this.name = name;
        this.description = description;
        this.location = location;
        this.type = EntityType.MOBLIN;
    }

    public Direction getDirection() {
        return position;
    }

    public boolean isDead() {
        return life == 0;
    }

    public void UpdatePiratePosition() {
        int pos = Globals.random(4);
        switch (pos) {
            case 0:
                position = Direction.NORTH;
                break;
            case 1:
                position = Direction.EAST;
                break;
            case 2:
                position = Direction.SOUTH;
                break;
            case 3:
                position = Direction.WEST;
                break;
        }
    }

    public void Attack(Direction dir) {

        if (dir != null) {
            if (position.equals(dir)) {
                life--;
                System.out.println("You hit the " + getName() + "! " + "It has " + life + " lives left.");
                UpdatePiratePosition();
            }
            else
                System.out.println("You missed to attack the " + getName() + "...");
        }
    }

}
