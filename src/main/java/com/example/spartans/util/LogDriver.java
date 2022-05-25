package com.example.spartans.util;

import com.example.spartans.SpartansApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogDriver {
    public void warning(String nameClass, String message) {
        Logger logger = LogManager.getLogger(nameClass);
        logger.warn(message);
    }

    public void info(String nameClass, String message) {
        Logger logger = LogManager.getLogger(nameClass);
        logger.info(message);
    }

    public void error(String nameClass, String message, Exception e) {
        Logger logger = LogManager.getLogger(nameClass);
        logger.error(message, e);
    }
}
