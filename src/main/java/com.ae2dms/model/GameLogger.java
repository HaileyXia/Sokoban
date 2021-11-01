package com.ae2dms.model;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Class GameLogger {@link GameLogger} is maintenance.
 *
 * <p>Handles the game logging.</p>
 *
 * <p>The logfile is placed in the directory where the game is executed under a directory named "GAME_NAME"-logfiles.</p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class GameLogger extends Logger {

    private static Logger logger = Logger.getLogger("GameLogger");
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private Calendar calendar = Calendar.getInstance();

    /**
     * Method GameLogger() is extension.
     *
     * <p>Initialize file and simpleFormatter.</p>
     *
     * @throws IOException Catch I/O exception
     * @since 2020/10/25
     */
    public GameLogger() throws IOException {
        super("com.aes2dms.sokoban", null);

        File directory = new File(System.getProperty("user.dir") + "/" + "logs");
        directory.mkdirs();

        FileHandler fh = new FileHandler(directory + "/" + GameEngine.GAME_NAME + ".log");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
    }

    /**
     * Method createFormattedMessage(String message) is maintenance.
     *
     * <p>Returns a {@link String} containing the current date and time, and the message.</p>
     *
     * @param message the message need to be added behind time.
     * @return a string containing the current date and time, and the message
     * @since 2020/10/25
     */
    private String createFormattedMessage(String message) {
        return dateFormat.format(calendar.getTime()) + " -- " + message;
    }

    /**
     * Method info(String message) is maintenance.
     *
     * <p>Inherited Class {@link Logger}</p>
     *
     * @param message the message passed to method info()
     * @since 2020/10/25
     */
    public void info(String message) {
        logger.info(createFormattedMessage(message));
    }

    /**
     * Method warning(String message) is maintenance.
     *
     * <p>Inherited Class {@link Logger}</p>
     *
     * @param message the message passed to method warning()
     * @since 2020/10/25
     */
    public void warning(String message) {
        logger.warning(createFormattedMessage(message));
    }

    /**
     * Method severe(String message) is maintenance.
     *
     * <p>Inherited Class {@link Logger}</p>
     *
     * @param message the message passed to method severe()
     * @since 2020/10/25
     */
    public void severe(String message) {
        logger.severe(createFormattedMessage(message));
    }
}