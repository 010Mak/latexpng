package app;

import java.awt.Color;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class CommandLineParser {

    private final List<String> expressions = new ArrayList<>();
    private Path outDir = Paths.get("out");
    private boolean showHelp = false;
    private boolean showVersion = false;
    private boolean interactive = false;
    private int fontSize = 24;              
    private Color bgColor = null;           

    public CommandLineParser(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            switch (arg) {
                case "-o":
                case "--out":
                    i++;
                    outDir = Paths.get(args[i]);
                    break;

                case "-e":
                case "--expr":
                    i++;
                    expressions.add(args[i]);
                    break;

                case "-s":
                case "--size":
                    i++;
                    fontSize = Integer.parseInt(args[i]);
                    break;

                case "--bg":
                case "--background":
                    i++;
                    bgColor = parseColor(args[i]);
                    break;

                case "-i":
                case "--interactive":
                    interactive = true;
                    break;

                case "-h":
                case "--help":
                    showHelp = true;
                    break;

                case "--version":
                    showVersion = true;
                    break;

                default:
                    break;
            }
        }
    }

    private Color parseColor(String raw) {
        String text = raw.trim().toLowerCase();
        if (text.startsWith("#") && text.length() == 7) {
            return new Color(Integer.parseInt(text.substring(1), 16), true);
        }
        if ("black".equals(text)) 
        return Color.BLACK;
        if ("white".equals(text)) 
        return Color.WHITE;
        return null; 
    }

    public List<String> getExpressions() { 
        return expressions; 
    }
    public List<String> expressions() { 
        return expressions; 
    }
    public Path getOutDir() { 
        return outDir; 
    }
    public Path outDir() { 
        return outDir; 
    }
    public int getFontSize() { 
        return fontSize; 
    }
    public Color getBackgroundColor() { 
        return bgColor; 
    }
    public boolean isHelp() { 
        return showHelp; 
    }
    public boolean help() { 
        return showHelp; 
    }
    public boolean isVersion() { 
        return showVersion; 
    }
    public boolean version() { 
        return showVersion; 
    }
    public boolean isInteractive() { 
        return interactive; 
    }
}
