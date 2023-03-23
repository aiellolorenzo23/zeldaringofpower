package world;

import enums.Direction;
import enums.ItemType;
import world.entities.*;
import world.globals.Globals;

import java.util.ArrayList;
import java.util.List;

public class World {
    //#DEFINE variables
    private static final String ACTION_INVENTORY = "inventory";

    private static final String ACTION_LOOK = "look";
    private static final String ACTION_SEE = "see";

    private static final String ACTION_GO = "go";
    private static final String ACTION_WALK = "walk";

    private static final String ACTION_TAKE = "take";
    private static final String ACTION_GRAB = "grab";

    private static final String ACTION_DROP = "drop";
    private static final String ACTION_LEAVE = "leave";

    private static final String ACTION_EQUIP = "equip";
    private static final String ACTION_ARM = "arm";

    private static final String ACTION_UNEQUIP = "unequip";
    private static final String ACTION_UNARM = "unarm";

    private static final String ACTION_ATTACK = "attack";
    private static final String ACTION_FIGHT = "fight";

    private static final String ACTION_LOCK = "lock";
    private static final String ACTION_CLOSE = "close";

    private static final String ACTION_UNLOCK = "unlock";
    private static final String ACTION_OPEN = "open";

    private static final String ACTION_LOOT = "loot";
    private static final String ACTION_STEAL = "steal";

    private static final String ACTION_TALK = "talk";
    private static final String ACTION_SPEAK = "speak";

    public static final String ACTION_EXIT = "exit";
    public static final String ACTION_QUIT = "quit";

    private static final String ACTION_HELP = "help";

    public TheHero player;
    public List<Entity> entities = new ArrayList<>();
    public boolean gameOver;

    public World() {

        // Create rooms
        Room room1 = new Room("Entrance of the Tower of Evil", "Evil's atmosphere. ");
        Room room2 = new Room("Trial room: Wind", "You are surrounded by strange pillars, a creature staring at you. Behind him a stone full of light lies.");
        Room room3 = new Room("Trial room: Earth", "There's a strange atmosphere in this room. You can see an artificial sky above, many grey clouds up there. In front of you, a creature staring at you. Behind him, a pearl full of light lies.");
        Room room4 = new Room("Dark Room", "It's very dark you can't see anything. Smells like death corpse in here. Must be careful.");
        Room room5 = new Room("Stairs", "You are surrounded by ancient stuff.");
        Room room6 = new Room("Top of the Tower of Evil", "It's very dark you can't see anything.");

        // Create exits
        // Room 1
        Exit exitR1toR2 = new Exit("door", ExitDescription(room2), Direction.EAST, room1, room2);
        Exit exitR1toR3 = new Exit("stairs", ExitDescription(room3), Direction.WEST, room1, room3);
        exitR1toR3.setLocked(true);
        Exit exitR1toR4 = new Exit("door", ExitDescription(room6), Direction.NORTH, room1, room4);

        // Room 2
        Exit exitR2toR1 = new Exit("window", ExitDescription(room1), Direction.WEST, room2, room1);

        // Room 3
        Exit exitR3toR1 = new Exit("door", ExitDescription(room1), Direction.EAST, room3, room1);

        // Room 4
        Exit exitR4toR1 = new Exit("door", ExitDescription(room1), Direction.SOUTH, room4, room1);
        Exit exitR4toR5 = new Exit("stairs", ExitDescription(room5), Direction.NORTH, room4, room5);

        // Room 5
        Exit exitR5toR4 = new Exit("stairs", ExitDescription(room4), Direction.SOUTH, room5, room4);
        Exit exitR5toR6 = new Exit("door", ExitDescription(room6), Direction.NORTH, room5, room6);
        exitR5toR6.setLocked(true);

        // Room 6
        Exit exitR6toR5 = new Exit("door", ExitDescription(room5), Direction.SOUTH, room6, room5);

        // Create items
        Item key = new Item("key", "It's a rusty old key, maybe it can open something.", ItemType.KEY);
        Item rock = new Item("rock", "It's just a simple stupid rock.");
        Item sword = new Item("mastersword", "The Blade of Evil's Bane.", ItemType.WEAPON);
        Item scabbard = new Item("scabbard", "An expensive scabbard used to hold weapons.", ItemType.HOLDER);
        Item shield = new Item("HylianShield", "A shield wielded by the proud warrios of Hyrule", ItemType.WEAPON);
        Item spiritualStone = new Item("WindSpiritualStone", "The spiritual stone of Wind. Use it to overpower your sword.", ItemType.POWER);
        Item spiritualPearl = new Item("EarthSpiritualPearl", "The spiritual pearl of Earth. Use it to overpower your sword", ItemType.POWER);

        // Create player
        player = new TheHero("Hero", "You are the mighty hero of the legends, Link, the one who evil's bane.", room1);

        // Create npcs
        NPC owl = new NPC("OwlStatue", "A solid rock statue represents a owl. Seems alive.", "Stuck in this room? Check out the "+room2.getName()+". It is to the east.", room1);
        NPC wizard = new NPC("ChainedWizard", "Bone skin, more bone than skin. He is dressed in a wizard's tunic.", "It's a dangerous place to go alone, be careful! A powerful enemy is waiting for you!", room3);
        NPC Zelda = new NPC("ZeldaSpirit", "She's the spirit of the Princess Zelda within the Master Sword!", "Be careful Link! To defeat Ganon you must have the powers of Wind and Earth to overpower the Master Sword! Be careful... save Hyrule.", room5);

        Item watch = new Item("watch", "The times is "+ Globals.getCurrentTime());
        Item book = new Item("book", "\"Hylia: Myths and History\": and encyclopedia about the legends of Hyrule, pretty interesting.");
        wizard.Insert(watch);
        wizard.Insert(book);

        // Create monsters
        Monster moblin = new Monster("Moblin", "It's staring at you from the shadows, but you can't see it.", room4);
        Boss Ganondorf = new Boss("Ganondorf", "The King of evil, it's playing piano.", room6);

        //Create Q&As
        QAndA gollum = new QAndA();
        QAndA smeagol = new QAndA();

        // Add entities to Room 1
        room1.Insert(exitR1toR2);
        room1.Insert(exitR1toR3);
        room1.Insert(exitR1toR4);
        room1.Insert(rock);

        // Add entities to Room 2
        room2.Insert(exitR2toR1);
        room2.Insert(owl);
        room2.Insert(smeagol);
        room2.Insert(owl);
        room2.Insert(spiritualStone);

        // Add entities to Room 3
        room3.Insert(exitR3toR1);
        room3.Insert(gollum);
        room3.Insert(spiritualPearl);

        // Add entities to Room 4
        room4.Insert(exitR4toR1);
        room4.Insert(exitR4toR5);
        room4.Insert(wizard);
        room4.Insert(key);
        room4.Insert(moblin);

        // Add entities to Room 5
        room5.Insert(exitR5toR4);
        room5.Insert(exitR5toR6);
        room5.Insert(Zelda);

        // Add entities to Room 6
        room6.Insert(exitR6toR5);
        room6.Insert(Ganondorf);

        // Add all entities to world
        entities.add(room1);
        entities.add(room2);
        entities.add(room3);
        entities.add(room4);
        entities.add(room5);
        entities.add(room6);

        entities.add(exitR1toR2);
        entities.add(exitR1toR3);
        entities.add(exitR1toR4);
        entities.add(exitR2toR1);
        entities.add(exitR3toR1);
        entities.add(exitR4toR1);
        entities.add(exitR4toR5);
        entities.add(exitR5toR4);
        entities.add(exitR5toR6);
        entities.add(exitR6toR5);

        entities.add(owl);
        entities.add(wizard);
        entities.add(moblin);
        entities.add(gollum);
        entities.add(smeagol);

        entities.add(key);
        entities.add(rock);
        entities.add(sword);
        entities.add(scabbard);
        entities.add(spiritualStone);
        entities.add(spiritualPearl);

        player.getContains().add(scabbard);
        player.getContains().add(sword);
        player.getContains().add(shield);

        // Show init room
        //player.DescribeCurrentRoom();
        gameOver = false;
    }

    public String ExitDescription(Room room) {
        return "Exit to the " + room.getName() + ".";
    }

    public void HandleInput(List<String> words) {

        switch (words.size()) {
            case 0:
                System.err.println("ERROR: Type something!");
                break;
            case 1:
            case 2:
                HandleAction(words);
                break;
            default:
                System.out.println("ERROR: Please type 2 words max!");
                break;
        }
    }

    public void setPlayer(TheHero player) {
        this.player = player;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void HandleAction(List<String> words) {

        String actionName = words.get(0).toLowerCase();
        String actionParameter = "";
        if (words.size() > 1) {
            actionParameter = words.get(1).toLowerCase();
        }

        if (ACTION_INVENTORY.equals(actionName)) {
            player.Inventory();
        }
        else if (ACTION_LOOK.equals(actionName) || ACTION_SEE.equals(actionName)) {
            player.Look_Player(actionParameter);
        }
        else if (ACTION_GO.equals(actionName) || ACTION_WALK.equals(actionName)) {
            player.Go(actionParameter);
        }
        else if (ACTION_TAKE.equals(actionName) || ACTION_GRAB.equals(actionName)) {
            player.Take(actionParameter);
        }
        else if (ACTION_DROP.equals(actionName) || ACTION_LEAVE.equals(actionName)) {
            player.Drop(actionParameter);
        }
        else if (ACTION_EQUIP.equals(actionName) || ACTION_ARM.equals(actionName)) {
            player.Equip(actionParameter);
        }
        else if (ACTION_UNEQUIP.equals(actionName) || ACTION_UNARM.equals(actionName)) {
            player.Unequip(actionParameter);
        }
        else if (ACTION_ATTACK.equals(actionName) || ACTION_FIGHT.equals(actionName)) {
            gameOver = player.Attack(actionParameter);
        }
        else if (ACTION_LOCK.equals(actionName) || ACTION_CLOSE.equals(actionName)) {
            player.Lock(actionParameter);
        }
        else if (ACTION_UNLOCK.equals(actionName) || ACTION_OPEN.equals(actionName)) {
            player.Unlock(actionParameter);
        }
        else if (ACTION_LOOT.equals(actionName) || ACTION_STEAL.equals(actionName)) {
            player.Loot(actionParameter);
        }
        else if (ACTION_TALK.equals(actionName) || ACTION_SPEAK.equals(actionName)) {
            player.Talk(actionParameter);
        }
        else if (ACTION_HELP.equals(actionName)) {
            ShowHelp();
        }
        else
            System.out.println("Invalid action, please try again.");
    }

    public String ShowCommand(String str, String str2) {
        String result = "- '" + str.toUpperCase() + "'";
        if (str2.isEmpty()) {
            result += " command";
        }
        else {
            result += " or '" + str2.toUpperCase() + "'";
            result += " commands";
        }
        return result;
    }

    public void ShowHelp() {
        System.out.println("Action Commands:");
        System.out.println(ShowCommand(ACTION_INVENTORY, "") + " shows all the items you have.");
        System.out.println(ShowCommand(ACTION_LOOK, ACTION_SEE) + " describe the object you specify.");
        System.out.println(ShowCommand(ACTION_GO, ACTION_WALK) + " move the player to a different room. The parameter must be a direction.");
        System.out.println(ShowCommand(ACTION_TAKE, ACTION_GRAB) + " put an item inside your inventory.");
        System.out.println(ShowCommand(ACTION_DROP, ACTION_LEAVE) + " drop the item from your inventory and place it in the current room.");
        System.out.println(ShowCommand(ACTION_EQUIP, ACTION_ARM) + " hold that item in your hand to use it.");
        System.out.println(ShowCommand(ACTION_UNEQUIP, ACTION_UNARM) + " unhold that item from your hand.");
        System.out.println(ShowCommand(ACTION_ATTACK, ACTION_FIGHT) + " attack a monster in the current room. You will need a weapon. The parameter must be a direction.");
        System.out.println(ShowCommand(ACTION_LOCK, ACTION_CLOSE) + " close an exit. You will need a key. The parameter must be a direction.");
        System.out.println(ShowCommand(ACTION_UNLOCK, ACTION_OPEN) + " opens an exit. You will need a key. The parameter must be a direction.");
        System.out.println(ShowCommand(ACTION_LOOT, ACTION_STEAL) + " takes all items from an NPC to your inventory.");
        System.out.println(ShowCommand(ACTION_TALK, ACTION_SPEAK) + " to have a little chit chat with an NPC.");
        System.out.println(ShowCommand(ACTION_EXIT, ACTION_QUIT) + " end the game.");
    }

}
