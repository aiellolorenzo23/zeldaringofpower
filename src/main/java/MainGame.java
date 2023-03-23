import enums.EntityType;
import enums.ItemType;
import utils.Util;
import world.World;
import world.entities.Item;
import world.entities.Room;
import world.entities.TheHero;
import world.globals.Globals;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class MainGame {

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException, URISyntaxException {
        Scanner scanner = new Scanner(System.in);
        String input;
        World world = new World();
        SplashGame(world);

        File dungeonMusic = Util.getFileFromResources("TLOZALTTP.mp3");
        SoundPlayerUsingClip run = getClip(dungeonMusic);
        Thread t = new Thread(run);
        t.start();

        while (!world.isGameOver()) {

            // Get input
            input = scanner.nextLine();

            // Split string to words
            List<String> words = Globals.split(input);

            // Exit
            if (words.size() > 0 && (World.ACTION_EXIT.equals(words.get(0).toLowerCase()) || World.ACTION_QUIT.equals(words.get(0).toLowerCase())))
                break;

            // Parse command
            world.HandleInput(words);

            TheHero player = world.player;
            Room location = player.getLocation();

            if(location.getName().equals("Top of the Tower of Evil")) {
                stopMusic(run,t);
                File bossMusic = Util.getFileFromResources("GANON.mp3");
                SoundPlayerUsingClip bossRun = getClip(bossMusic);
                Thread bt = new Thread(bossRun);
                bt.start();
                BossCutscene(player,world);
            }

        }

        System.out.println("Thanks for playing!");

        System.exit(0);
    }

    public static void SplashGame(World world) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException, URISyntaxException {

        File prologueFile = Util.getFileFromResources("The_Legendary_Hero.mp3");
        SoundPlayerUsingClip run = getClip(prologueFile);
        Thread t = new Thread(run);
        t.start();

        System.out.println("                                    /@\n" +
                "                     __        __   /\\/\n" +
                "                    /==\\      /  \\_/\\/   \n" +
                "                  /======\\    \\/\\__ \\__\n" +
                "                /==/\\  /\\==\\    /\\_|__ \\\n" +
                "             /==/    ||    \\=\\ / / / /_/\n" +
                "           /=/    /\\ || /\\   \\=\\/ /     \n" +
                "        /===/   /   \\||/   \\   \\===\\\n" +
                "      /===/   /_________________ \\===\\\n" +
                "   /====/   / |                /  \\====\\\n" +
                " /====/   /   |  _________    /  \\   \\===\\    THE LEGEND OF \n" +
                " /==/   /     | /   /  \\ / / /  __________\\_____      ______       ___\n" +
                "|===| /       |/   /____/ / /   \\   _____ |\\   /      \\   _ \\      \\  \\\n" +
                " \\==\\             /\\   / / /     | |  /= \\| | |        | | \\ \\     / _ \\\n" +
                " \\===\\__    \\    /  \\ / / /   /  | | /===/  | |        | |  \\ \\   / / \\ \\\n" +
                "   \\==\\ \\    \\\\ /____/   /_\\ //  | |_____/| | |        | |   | | / /___\\ \\\n" +
                "   \\===\\ \\   \\\\\\\\\\\\\\/   /////// /|  _____ | | |        | |   | | |  ___  |\n" +
                "     \\==\\/     \\\\\\\\/ / //////   \\| |/==/ \\| | |        | |   | | | /   \\ |\n" +
                "     \\==\\     _ \\\\/ / /////    _ | |==/     | |        | |  / /  | |   | |\n" +
                "       \\==\\  / \\ / / ///      /|\\| |_____/| | |_____/| | |_/ /   | |   | |\n" +
                "       \\==\\ /   / / /________/ |/_________|/_________|/_____/   /___\\ /___\\\n" +
                "         \\==\\  /               | /==/\n" +
                "         \\=\\  /________________|/=/    THE RING OF POWER\n" +
                "           \\==\\     _____     /==/ \n" +
                "          / \\===\\   \\   /   /===/\n" +
                "         / / /\\===\\  \\_/  /===/\n" +
                "        / / /   \\====\\ /====/\n" +
                "       / / /      \\===|===/\n" +
                "       |/_/         \\===/\n" +
                "                      =");

        Prologue();

        //System.out.println("\tWelcome to The Legend of Zelda - THE RING OF POWER!");
        System.out.println("\n------------------------------------------------------------------\n\n");

        stopMusic(run,t);

        String look = "look", room = "room";
        List<String> words = new ArrayList<>();
        words.add(look);
        words.add(room);
        world.HandleInput(words);
    }

    public static void Prologue() throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("This is but one of the legends of which the people speak...\n");
        Thread.sleep(6000);
        System.out.println("Long ago, there existed a kingdom where a golden power lay hidden.\n" +
                "It was a prosperous land blessed with green forests, tall mountains, and peace.\n");
        Thread.sleep(8000);
        System.out.println("But one day, a man of great evil found the golden power and took it for himself. \n" +
                "With its strength at his command, he spread darkness across the kingdom. But then, when all hope had died, and the hour of doom seemed at hand...\n");
        Thread.sleep(10000);
        System.out.println("...a young boy clothed in green appeared as if from nowhere. Wielding the blade of evil's bane, he sealed the dark one away and gave the land light.\n");
        Thread.sleep(8000);
        System.out.println("This boy, who traveled through time to save the land, was known as the Hero of Time. The boy's tale was passed down through generations until it became legend... \n");
        Thread.sleep(8000);
        System.out.println("...but many other legends followed him. The spirit of the Hero was destined to revive until evil was completely eradicated from the world of light.\n");
        Thread.sleep(8000);
        System.out.println("From generation to generation the Hero and Princess Zelda protected the Triforce from the forces of evil, until...\n");
        Thread.sleep(8000);
        System.out.println("...the evil being Ganondorf, the reincarnation of the King of the Gerudo's thieves, came into possession of a new magical power.\n");
        Thread.sleep(8000);
        System.out.println("Ganondorf, the calamity of the kingdom of Hyrule, the chosen one of the Triforce of strength, deceived the king and his inhabitants by pouring his magical power into a ring. \n" +
                "A ring with which to dominate the minds of others, the power of awe absolute and destined to last only as long as the life of its own creator would have lasted.\n");
        Thread.sleep(12000);
        System.out.println("It was then that the Hero of Legends found himself alone once again to face the forces of evil.\nBut not even the Hero's courage and Zelda's wisdom were enough to defeat the calamity Ganon...the hero fell, and Ganon claims the Triforce becoming immortal.\n");
        Thread.sleep(10000);
        System.out.println("With all her strength left, Zelda imbued her magical power into the Master Sword, it was only a matter of time for a new hero to reclaim this sword to vanquish Ganon's forces and end the cycle of evil.\n");
        Thread.sleep(8000);
        System.out.println("Centuries passed, Ganon's dominion had reached the entire World of Light. But one day a new Hero was born again. \n" +
                "He reclaimed the Master Sword and with the power of the Goddess Hylia, he created a second ring, able to counteract the negative effects of the Ring of Power. \n" +
                "With such power and the trusty sword at his side, the Hero was ready to face Ganon one last time.\n\n");
        Thread.sleep(14000);
        System.out.println("And this is how, at the gates of the Tower of Evil, the last legend of the Hero who evil's bane began...\n");
        Thread.sleep(6000);
    }

    public static void BossCutscene(TheHero player, World world) throws InterruptedException {
        Thread.sleep(8000);
        System.out.println("\nGanondorf: And who you're supposed to be?! ");
        if(checkItems(ItemType.WEAPON, player)==2) {
            System.out.println("AHAHAHAHAH Do you think that THAT sword can defeat me? It failed once, and it'll fail again!\n");
        }
        else {
            System.out.println("You have NO POWERS at all! Die!\n");
            Thread.sleep(4000);
            System.out.println("Ganondorf blasts the Hero with his powerful magic. The Hero of the Legends fell.\n");
            Thread.sleep(4000);
            System.out.println("GAME OVER");
            world.gameOver = true;
            return;
        }

        Thread.sleep(4000);
        System.out.println("Ganondorf: I'm Immortal, no one can stop me. Even you, or even Zelda.\n");
        Thread.sleep(4000);
        System.out.println("Ganondorf: How dare you look at me like this?! ");
        if(checkItems(ItemType.POWER,player)==2){
            System.out.println("You stole the Spirituals keys?! You'll regret it, YOU FOOL!\n");
        }
        else{
            System.out.println("You must be a brave man...or A FOOL! I'll end this now!\n");
            Thread.sleep(4000);
            System.out.println("Ganondorf blasts the Hero with his powerful magic. The Hero of the Legends fell.\n");
            Thread.sleep(4000);
            System.out.println("GAME OVER");
            world.gameOver = true;
            return;
        }
        Thread.sleep(6000);
        System.out.println("So come on, bring it on! We'll see if the legends will be true and if that Sword, blessed with theese powers, could defeat me!.\n");
        Thread.sleep(4000);
    }

    private static int checkItems(ItemType itemType, TheHero player){
        AtomicInteger check = new AtomicInteger();
        player.getContains().forEach(e->{
            if(e.getType().equals(EntityType.ITEM)){
                Item item = (Item) e;
                if(item.getItemType().equals(itemType))
                    check.getAndIncrement();
            }
        });

        return check.get();
    }

    private static void stopMusic(SoundPlayerUsingClip clip, Thread t){
        clip.stopMusic();
        t.interrupt();
    }

    private static SoundPlayerUsingClip getClip(File file){
       return new SoundPlayerUsingClip(file);
    }
}
