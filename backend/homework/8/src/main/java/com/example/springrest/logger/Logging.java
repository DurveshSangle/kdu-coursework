package com.example.springrest.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Logging {
    private final Logger logger = LoggerFactory.getLogger(Logging.class);

    public void logError(String msg){
        logger.error(msg);
    }
    public void logInfo(String msg){
        logger.info(msg);
    }
    public void logInfo(String msg,Object object){
        logger.info(msg,object);
    }
}
