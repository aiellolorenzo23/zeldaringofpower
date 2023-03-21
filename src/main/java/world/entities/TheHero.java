package world.entities;

import enums.Direction;
import enums.EntityType;
import enums.ItemType;
import world.globals.Globals;

import java.util.List;

public class TheHero extends Creature{
    Item holdingItem;

    public TheHero(EntityType type, String name, String description, Room location) {
        super(type, name, description, location);
        this.holdingItem = null;
    }

    public TheHero(String name, String description, Room location) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.holdingItem = null;
    }

    public void DescribeCurrentRoom() {
        System.out.println("You are now in the " + location.getName() + ".");
        getLocation().Look();
        System.out.println("In this room you will find: ");
        if (Show(getLocation().getContains(), EntityType.ITEM) == 0)
            System.out.println("No items.");
        Show(getLocation().getContains(), EntityType.NPC);
        Show(getLocation().getContains(), EntityType.PIRATE);
    }

    public void Inventory() {
        System.out.println("Inventory: ");
        System.out.println("You have " + Show(getContains(), EntityType.ITEM) + " items.");
    }

    Item getHoldingItem() {
        return holdingItem;
    }

    void setHoldingItem(Item item) {
        holdingItem = item;
    }

    public void Look_Player(String str) {

        boolean looked = false;

        // Look at me
        if ("me".equals(str)) {
            Look();
            looked = true;
        }

        // Look at room
        if ("room".equals(str)) {
            DescribeCurrentRoom();
            looked = true;
        }

        // Look at room using its name
        if (!looked) {
            String currentRoomName = location.getName().toLowerCase();
            if (currentRoomName.equals(str)) {
                location.Look();
                looked = true;
            }
        }

        // Look at entities in the room
        if (!looked) {
            String entityName;

            for (Entity e : location.getContains()) {

                // If it's in the room and has the same name
                switch (e.getType()) {
                    case NPC:
                    case EXIT:
                    case ITEM:
                        entityName = e.getName().toLowerCase();
                        if (entityName.equals(str)) {
                            e.Look();
                            looked = true;
                        }
                        break;
                    default:
                        break;
                }

            }
        }

        // Look at entities in your inventory
        if (!looked) {
            Item item = GetEntityFromName(str, getContains(), EntityType.ITEM);

            if (item != null) {
                item.Look();
                looked = true;
            }
        }

        // Look at direction
        if (!looked) {
            Exit exit = GetExitFromDirection(str);

            if (exit != null) {
                exit.Look();
                looked = true;
            }
        }

        if (!looked) {
            System.err.println("Nothing relevant to look at " + str + ".");
        }

    }

    public void Go(String str) {

        boolean moved = false;

        Exit exit = GetExitFromDirection(str);
        if (exit != null) {
            if (exit.isLocked()) {
                System.out.println(str+" is locked.");
            }
            else {
                System.out.println("Walking to " + str + "...");
                location = exit.getDestination();
                DescribeCurrentRoom();
                moved = true;
            }
        }

        if (!moved)
            System.out.println("You can't go to " + str + ".");

    }

    public void Take(String str) {

        // Search for items
        Item item = GetEntityFromName(str, location.getContains(), EntityType.ITEM);

        if (item == null) {
            System.out.println("You can't take " + str + ".");
        }
        else {
            if (item.getItemType().equals(ItemType.WEAPON)) {

                Item holder = GetItemFromType(ItemType.HOLDER);

                // If we don't have a holder, we need one (scabbard)
                if (holder == null) {
                    System.out.println("You need to have a holder to take this " + str + ".");
                    return;
                }
                else {
                    holder.Insert(item);
                    item.setParent(holder);
                }
            }

            getContains().add(item);
            location.getContains().remove(item);
            System.out.println("You now have a " + str + ".");
        }

    }

    public void Drop(String str) {

        // Search for items
        Item item = GetEntityFromName(str, getContains(), EntityType.ITEM);

        if (item == null) {
            System.out.println("You can't drop a " + str + ".");
        }
        else {
            // Check if equipped
            if (getHoldingItem() == item) {
                Unequip(str);
            }

            // Check for parent
            Entity parent = item.getParent();
            if (parent != null) {
                parent.Remove(item);
                item.setParent(null);
            }

            // Check if childs
            for (Entity child : item.getContains()) {
                getContains().remove(child);
                location.getContains().add(child);
                System.out.println("You dropped a " + child.getName() + ".");
            }

            item.getContains().clear();
            getContains().remove(item);
            location.getContains().add(item);
            System.out.println("You dropped a " + str + ".");
        }

    }

    public void Equip(String str) {

        // Search for items
        Item item = GetEntityFromName(str, getContains(), EntityType.ITEM);

        if (item == null) {
            System.out.println("You can't equip a " + str + ".");
        }
        else {
            if (getHoldingItem() != null)
                System.out.println("Switched " + getHoldingItem().getName() + " for " + item.getName() + ".");
            setHoldingItem(item);
            System.out.println("Equipped a " + item.getName() + ".");
        }

    }

    public void Unequip(String str) {

        // Search for items
        Item item = GetEntityFromName(str, getContains(), EntityType.ITEM);

        if (item == null) {
            System.out.println("You can't unequip a " + str + ".");
        }
        else {
            if (getHoldingItem() == item) {
                System.out.println("Unequipped a " + item.getName() + ".");
                setHoldingItem(null);
            }
            else {
                System.out.println("You are not holding a " + item.getName() + ".");
            }
        }

    }

    public boolean Attack(String str) {
        boolean gameOver = false;

        // Search monster
        Monster monster = GetMonsterFromCurrentRoom();

        if (monster == null) {
            System.out.println("Nothing to attack in this room.");
        }
        else {

            if (str.equals(monster.getName().toLowerCase())) {
                System.out.println("The room is dark, you don't see where the " + monster.getName() + " is. Specify a direction to attack it.");
            }
            else {

                // Check valid direction
                Direction dir = Globals.strToDir(str);

                if (dir == null) {
                    System.out.println("You can't attack " + str + ".");
                }
                else {

                    Item weapon = GetItemFromType(ItemType.WEAPON);

                    if (weapon == null) {
                        System.out.println("You don't have a weapon to attack.");
                    }
                    else {
                        if (weapon == getHoldingItem()) {
                            monster.Attack(dir);
                            if (monster.isDead()) {
                                System.out.println("CONGRATULATIONS! YOU BEAT THE GAME!");
                                gameOver = true;
                            }
                        }
                        else
                            System.out.println("You need to equip a weapon to attack.");
                    }
                }
            }
        }

        return gameOver;
    }

    public void Lock(String str) {
        // Check if it's direction
        if (Globals.isDir(str)) {

            Direction direction = Globals.strToDir(str);
            Exit exit = GetExitFromDirection(str);

            if (exit == null) {
                System.out.println("There is no exit in " + str + ".");
            }
            else {

                if (!exit.isLocked()) {

                    // Search for items
                    Item key = GetItemFromType(ItemType.KEY);

                    if (key == null) {
                        System.out.println("You don't have a key to lock the exit.");
                    }
                    else {
                        // Check if key is equipped
                        if (key == getHoldingItem()) {
                            System.out.println(str + " locked.");
                            exit.setLocked(true);
                        }
                        else System.out.println("You are not holding a key.");
                    }
                }
                else System.out.println(str + " is already locked!");
            }

        }
        else {
            System.out.println("Parameter must be a direction.");
        }
    }

    public void Unlock(String str) {
        // Check if it's direction
        if (Globals.isDir(str)) {

            Direction direction = Globals.strToDir(str);
            Exit exit = GetExitFromDirection(str);

            if (exit == null) {
                System.out.println("There is no exit in " + str + ".");
            }
            else {

                if (exit.isLocked()) {

                    // Search for items
                    Item key = GetItemFromType(ItemType.KEY);

                    if (key == null) {
                        System.out.println("You don't have a key to unlock the exit.");
                    }
                    else {
                        // Check if key is equipped
                        if (key == getHoldingItem()) {
                            System.out.println(str + " unlocked.");
                            exit.setLocked(false);
                        }
                        else System.out.println("You are not holding a key.");
                    }
                }
                else System.out.println(str + " is already unlocked!");
            }

        }
        else {
            System.out.println("Parameter must be a direction.");
        }
    }

    public void Loot(String str) {
        boolean foundNpc = false;

        for (Entity e : location.getContains()) {

            // If it's in the room and it has the same name
            if (e.getType().equals(EntityType.NPC)) {

                NPC npc = (NPC)e;

                String npcName = npc.getName().toLowerCase();
                if (npcName.equals(str)) {
                    foundNpc = true;

                    if (!npc.getContains().isEmpty()) {
                        getContains().addAll(npc.getContains());
                        System.out.println("You have looted from " + str + ":");
                        Show(npc.getContains(), EntityType.ITEM);
                        npc.getContains().clear();
                    }
                    else System.out.println("Nothing to loot from " + str + ".");
                    break;
                }
            }
        }

        if (!foundNpc)
            System.out.println("You can't loot from " + str + ".");
    }

    public void Talk(String str) {
        NPC npc = GetEntityFromName(str, location.getContains(), EntityType.NPC);

        if (npc == null) {
            System.out.println("You can't talk to " + str + ".");
        }
        else {
            System.out.println(npc.getDialogue());
        }
    }

    private Exit GetExitFromDirection(Direction dir) {
        Exit exit = null;

        // Search for exits
        for (Entity e : location.getContains()) {

            if (e.getType() == EntityType.EXIT) {

                // Look at each type of direction parameter (NORTH, WEST, EAST, SOUTH)
                if (dir == ((Exit)e).getDirection()) {
                    exit = (Exit)e;
                    break;
                }
            }
        }

        return exit;

    }

    private Exit GetExitFromDirection(String str) {
        return Globals.isDir(str) ? GetExitFromDirection(Globals.strToDir(str)) : null;
    }

    private static <T> T GetEntityFromName(String name, List<Entity> entities, EntityType type) {
        T entity = null;

        // Search for entities
        for (Entity e : entities) {

            if (e.getType() == type) {

                String entityName = e.getName().toLowerCase();
                if (entityName.equals(name)) {
                    entity = (T) e;
                    break;
                }
            }
        }

        return entity;
    }

    private Item GetItemFromType(ItemType type) {
        Item item = null;

        // Search for items
        for (Entity e : getContains()) {

            if (e.getType() == EntityType.ITEM && ((Item)e).getItemType() == type) {
                item = (Item)e;
                break;
            }
        }
        return item;
    }

    private Monster GetMonsterFromCurrentRoom() {
        Monster monster = null;

        // Search for entities
        for (Entity e : location.getContains()) {

            if (e.getType() == EntityType.PIRATE) {
                monster = (Monster)e;
                break;
            }
        }

        return monster;
    }

}
