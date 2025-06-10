package app;

public final class ASCIIArt {

    private static final String TITLE =
          "  _______  __   __                  \n"
        + " |__   __| \\ \\ / /                  \n"
        + "    | | ___ \\ V / _ __  _ __   __ _ \n"
        + "    | |/ _ \\ > < | '_ \\| '_ \\ / _` |\n"
        + "    | |  __// . \\| |_) | | | | (_| |\n"
        + "    |_|\\___/_/ \\_\\ .__/|_| |_|\\__, |\n"
        + "                 | |           __/ |\n"
        + "                 |_|          |___/ \n";

    
    public static void title() {
        System.out.println(TITLE);
        System.out.println();
    }

    private ASCIIArt() { }
}
