package gg.bonka.placeholderItemMeta.logging;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;

public class ConsoleLogger {

    private static final String prefix = String.format("%s%s%s", TextColor.INFO.getAnsi("["), TextColor.THEME.getAnsi("PIM"), TextColor.INFO.getAnsi("]"));

    public static void info(final String message) {
        print(message, TextColor.INFO);
    }

    public static void error(final String message) {
        print(message, TextColor.ERROR);
    }

    public static void success(final String message) {
        print(message, TextColor.SUCCESS);
    }

    private static void print(String message, TextColor chatColor) {
        Bukkit.getConsoleSender().sendMessage(String.format("%s %s", prefix, chatColor.getAnsi(message)));
    }

    @AllArgsConstructor
    private enum TextColor {
        INFO("\u001B[36m"),
        ERROR("\u001B[31m"),
        SUCCESS("\u001B[32m"),

        THEME("\u001B[34m");

        private final String ansi;

        public String getAnsi(String text) {
            // u001B[0m = reset
            // More info about ansi escape codes: https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
            return String.format("%s%s\u001B[0m", ansi, text);
        }
    }
}
