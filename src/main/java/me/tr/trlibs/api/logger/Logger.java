package me.tr.trlibs.api.logger;

import me.tr.trlibs.api.logger.colors.Color;
import me.tr.trlibs.api.mangers.files.type.yaml.YamlFile;

public class Logger {
    private boolean debug = false;

    public void setDebugLocation(YamlFile file, String path) {
        debug = file.getBoolean(path, false);
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void info(String message) {
        send(message, Color.AQUA, "Info");
    }

    public void success(String message) {
        send(message, Color.GREEN, "Success");
    }

    public void warning(String message) {
        send(message, Color.YELLOW, "Warning");
    }

    public void error(String message) {
        send(message, Color.DARK_RED, "Error");
    }

    /**
     * Log a message to log
     *
     * @param message Message to log
     * @param level   Level of log
     */
    public void log(String message, Levels level) {
        switch (level) {
            case SUCCESS -> success(message);
            case WARNING -> warning(message);
            case ERROR -> error(message);
            default -> info(message);
        }
    }

    /**
     * Debug a message to debug
     *
     * @param message Message to debug
     * @param level   Level of debug
     */
    public void debug(String message, Levels level) {
        if (debug)
            switch (level) {
                case SUCCESS -> success(message);
                case WARNING -> warning(message);
                case ERROR -> error(message);
                default -> info(message);
            }
    }


    private void send(String message, Color color, String levelName) {
        System.out.println(color.toAnsi() + "[" + levelName + "]: " + message + Color.RESET.toAnsi());
    }

    public enum Levels {
        INFO, SUCCESS, WARNING, ERROR
    }
}
