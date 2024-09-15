package backend.kdu.hw3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class logger {
    Logger logger = LoggerFactory.getLogger(logger.class);

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
