import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class SoundPlayerUsingClip implements Runnable{

    private String filePath;
    public SoundPlayerUsingClip(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try{
            while(true) {
                FileInputStream fis = new FileInputStream(filePath);
                Player p = new Player(fis);
                p.play();
            }
        }catch(Exception e){System.out.println(e);}
    }
}