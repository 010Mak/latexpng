package app;

import java.awt.Color;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyProgram {

    private static final String VERSION = "1.2.0";

    public static void main(String[] args) {

        CommandLineParser cli = new CommandLineParser(args);

        if (cli.isHelp()) {
            printHelp();
            return;
        }
        if (cli.isVersion()) {
            System.out.println("latexpng " + VERSION);
            return;
        }

        ASCIIArt.title();

        List<String> exprs = new ArrayList<>(cli.getExpressions());

        if (cli.isInteractive()) {
            System.out.println("Interactive LaTeX shell — type :q twice to finish");
            Scanner scan = new Scanner(System.in);
            StringBuilder sb = new StringBuilder();
            int quit = 0;

            while (scan.hasNextLine()) {
                String line = scan.nextLine();

                if (":q".equals(line.trim())) {
                    quit++;
                    if (quit == 2) break;
                    System.out.println("(again to confirm)");
                    continue;
                }
                quit = 0;
                sb.append(line).append('\n');
            }
            String block = sb.toString().trim();
            if (!block.isEmpty()) exprs.add(block);
        }

        if (exprs.isEmpty()) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter LaTeX (blank line to finish): ");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isBlank()) break;
                exprs.add(line);
            }
        }

        if (exprs.isEmpty()) {
            System.out.println("No expressions given, exiting.");
            return;
        }

        ImageExporter exporter = new ImageThing(
                cli.getOutDir(),
                cli.getFontSize(),
                cli.getBackgroundColor()
        );

        for (String e : exprs) {
            try {
                Path file = exporter.export(e);
                System.out.println("Wrote " + file);
            } catch (Exception ex) {
                System.out.println("Failed on " + e + ": " + ex.getMessage());
            }
        }
    }

    public static void printHelp() {
        System.out.println(
            "latexpng " + VERSION + " — convert LaTeX snippets to PNG\n\n" +
            "USAGE\n" +
            "  latexpng -e \"<expr>\" [-e \"<expr2>\"] [-o <dir>] [-s <pt>] [--bg <color>]\n" +
            "  latexpng -i [-o <dir>] [-s <pt>] [--bg <color>]   # multi-line shell\n" +
            "  latexpng --help | --version\n\n" +
            "FLAGS\n" +
            "  -e, --expr <expr>      LaTeX expression (repeatable)\n" +
            "  -o, --out  <dir>       Output directory (default: out)\n" +
            "  -s, --size <pt>        Font size in points (default: 24)\n" +
            "  --bg, --background     Background: #RRGGBB | black | white | transparent\n" +
            "  -i, --interactive      Open multi-line shell (:q twice to finish)\n" +
            "  -h, --help             Show this help and exit\n" +
            "  --version              Print version and exit\n"
        );
    }
}
