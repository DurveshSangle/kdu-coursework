package backend.kdu.hw3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class logger {
    Logger logger = LogManager.getLogger(logger.class);

    public void logWarn(String msg){
        logger.warn(msg);
    }
    public void logInfo(String msg){
        logger.info(msg);
    }
    public void logError(String msg){
        logger.error(msg);
    }
    public void logDebug(String msg){
        logger.debug(msg);
    }
}
