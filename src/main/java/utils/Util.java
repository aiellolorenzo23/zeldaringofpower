package utils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class Util {

    public static File getFileFromResources(String fileName) throws URISyntaxException {
        URL resource = Util.class.getClassLoader().getResource(fileName);
       return new File(resource.toURI());
    }
}
