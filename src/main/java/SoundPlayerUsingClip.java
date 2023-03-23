import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class SoundPlayerUsingClip implements Runnable{

    private File file;
    private Player p;
    public SoundPlayerUsingClip(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        try{
            FileInputStream fis = new FileInputStream(file);
            p = new Player(fis);
            while(true) {
                p.play();
            }
        }catch(Exception e){System.out.println(e);}
    }

    public void stopMusic(){
        p.close();
    }
}