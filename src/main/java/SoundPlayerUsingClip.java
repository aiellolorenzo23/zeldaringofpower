import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class SoundPlayerUsingClip implements Runnable{

    private String filePath;
    public SoundPlayerUsingClip(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try(FileInputStream fis = new FileInputStream(filePath)){
            new Player(fis).play();
        }catch(Exception e){System.out.println(e);}
    }
}