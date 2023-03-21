package world.globals;

import enums.Direction;

import java.util.*;

public class Globals {
    public static List<String> split(String str) {
        List<String> words = new ArrayList<>();

        if (!str.isEmpty()) {
            words = (Arrays.asList(str.split(" ")));
        }

        return words;
    }

    public static String dirToStr(Direction dir) {
        String result = "";

        switch (dir) {
            case NORTH:
                result = "north";
                break;
            case EAST:
                result = "east";
                break;
            case SOUTH:
                result = "south";
                break;
            case WEST:
                result = "west";
                break;
            default:
                break;
        }

        return result;
    }

    public static Direction strToDir(String str) {
        Direction result = null;

        if (str.equals("north"))
            result = Direction.NORTH;
        else if (str.equals("east"))
            result = Direction.EAST;
        else if (str.equals("south"))
            result = Direction.SOUTH;
        else if (str.equals("west"))
            result = Direction.WEST;

        return result;
    }

    public static boolean isDir(String str) {
        return strToDir(str) != null;
    }

    public static String getCurrentTime() {
        Date dNow = new Date();
        return ""+dNow.getTime();
    }

    public static int random(int value){
        return new Random().nextInt(value);
    }
}
