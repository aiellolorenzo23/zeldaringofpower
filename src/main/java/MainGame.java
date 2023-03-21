import world.World;
import world.globals.Globals;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainGame {

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String input;
        World world = new World();
        SplashGame(world);

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

        }

        System.out.println("Thanks for playing!");
    }

    public static void SplashGame(World world) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {

        String filePath = "C:/Users/laiello/Documents/ZeldaRingOfPower/src/main/resources/TLOZALTTP.mp3";
        Runnable run = new SoundPlayerUsingClip(filePath);
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

        Thread.sleep(3000);
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n");
        Thread.sleep(3000);
        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb\n" +
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb\n" +
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb\n" +
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb\n");

        //System.out.println("\tWelcome to The Legend of Zelda - THE RING OF POWER!");
        System.out.println("------------------------------------------------------------------");

        String look = "look", room = "room";
        List<String> words = new ArrayList<>();
        words.add(look);
        words.add(room);
        world.HandleInput(words);
    }
}
