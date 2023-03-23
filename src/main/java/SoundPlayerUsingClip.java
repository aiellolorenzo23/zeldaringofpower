import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class SoundPlayerUsingClip implements Runnable{

    private File file;
    public SoundPlayerUsingClip(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        try{
            while(true) {
                FileInputStream fis = new FileInputStream(file);
                Player p = new Player(fis);
                p.play();
            }
        }catch(Exception e){System.out.println(e);}
    }
}