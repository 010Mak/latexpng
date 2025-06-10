package app;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.nio.file.*;

public final class ImageThing extends ImageExporter {

    private final int fontSize;
    private final Color bgColor;

    public ImageThing(Path outDir, int fontSize, Color bgColor) {
        super(outDir);
        this.fontSize = fontSize;
        this.bgColor = bgColor;  
    }

    @Override
    public Path export(String expr) throws Exception {

        TeXFormula formula = new TeXFormula(expr);

        BufferedImage img = (BufferedImage) formula.createBufferedImage(
                TeXConstants.STYLE_DISPLAY,
                fontSize,
                Color.BLACK,
                bgColor 
        );

        Path target = outDir.resolve(sanitize(expr) + ".png");
        Files.createDirectories(target.getParent());
        ImageIO.write(img, "png", target.toFile());
        return target;
    }

    private String sanitize(String s) {
        return s.replaceAll("[^A-Za-z0-9_-]", "_");
    }
}
