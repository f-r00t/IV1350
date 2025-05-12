package main.java.se.kth.iv1350.pos.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
* Prints log messages to a file. The log file will be in the
* current directory and will be called log.txt.
*/
public class LogHandler {
    private static LogHandler INSTANCE;
    private PrintWriter logStream;

    /**
    * Creates a new instance and also creates a new log file.
    * An existing log file will be deleted.
    */
    public LogHandler() {
        try {
            logStream = new PrintWriter(
            new FileWriter("log.txt"), true);
        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }

    public static LogHandler getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new LogHandler();
        }
        return INSTANCE;
    }

    /**
    * Prints the specified string to the log file.
    *
    * @param message The string that will be printed to the
    * log file.
    */
    public void log(String message) {
        logStream.println(message);
    }
}
