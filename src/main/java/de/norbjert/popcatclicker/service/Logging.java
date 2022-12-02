package de.norbjert.popcatclicker.service;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class Logging {
    private static final Logger log = Logger.getLogger(Logging.class.getName());

    private Logging(){
    }

    public static Logger getLogger() {
        return log;
    }
}
