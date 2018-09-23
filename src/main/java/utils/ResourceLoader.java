package utils;

import java.io.FileNotFoundException;
import java.net.URL;

public class ResourceLoader {

    public static String loadResource(String name) throws FileNotFoundException {
        ClassLoader loader = ResourceLoader.class.getClassLoader();
        URL res = loader.getResource(name);
        if (res != null) {
            return res.getFile();
        } else {
            throw new FileNotFoundException();
        }
    }
}
