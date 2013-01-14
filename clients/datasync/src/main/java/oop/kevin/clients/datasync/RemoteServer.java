package oop.kevin.clients.datasync;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.FileWatchdog;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.xml.DOMConfigurator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: MichaelLee
 * Date: 12-12-3
 * Time: 下午1:45
 * <p/>
 */
public class RemoteServer {

    private static final String SERVER_NAME = "haixue-rpc";
    private static final long LOG_WATCH_DELAY = 60000L;
    private static final String FORMAT_DATE = "yyyy-MM-dd HH:mm:ss";
    private static final RemoteServer INSTANCE_REMOTING_SERVER = new RemoteServer();
    private static final Logger LOGGER = Logger.getLogger(RemoteServer.class);

    public static final RemoteServer getInstance() {
        return INSTANCE_REMOTING_SERVER;
    }

    public void beforeStart(String dateFormat) {
        String remoteServerHome = System.getProperty("haixue-rpc-project.home");
        if (remoteServerHome == null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            LogLog.warn(simpleDateFormat.format(new Date()) + "[haixue-rpc-project.home] is not set.");
        } else {
            //RemoteFileWatchdog rfw = new RemoteFileWatchdog(remoteServerHome + "/conf/log4j.xml", LOG_WATCH_DELAY);
            configureAndWatch(remoteServerHome + "/conf/log4j.xml", LOG_WATCH_DELAY);
        }
        }

    public void startup() {
        LOGGER.info("==================RemoteServer---------startup====================");
        LOGGER.info(SERVER_NAME + "is ready on startup ...");

    }

    public static void configureAndWatch(String filename, long delay) {
        RemoteFileWatchdog rfw = new RemoteFileWatchdog(filename);
        rfw.setName("Log4jWatchdog");
        rfw.setDelay(delay);
        rfw.start();

    }
    private static final class RemoteFileWatchdog extends FileWatchdog {

        public RemoteFileWatchdog(String fileName) {
            super(fileName);
        }

        @Override
        public void doOnChange() {
            new DOMConfigurator().doConfigure(filename, LogManager.getLoggerRepository());
            LogLog.warn(new SimpleDateFormat(FORMAT_DATE).format(new Date()) + "[" + filename + "] load completed .");
        }
    }

}
