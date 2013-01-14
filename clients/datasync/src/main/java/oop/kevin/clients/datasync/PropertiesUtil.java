package oop.kevin.clients.datasync;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: MichaelLee
 * Date: 12-12-6
 * Time: 下午1:17
 * <p/>
 */
public class PropertiesUtil {

    public static void resolveProperties() {
        try {
            Properties prop = new Properties();
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("quartz.properties");
            if (inputStream != null) {
                prop.load(inputStream);
                inputStream.close();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
