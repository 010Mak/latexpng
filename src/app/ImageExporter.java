package app;

import java.nio.file.Path;

public abstract class ImageExporter {
    protected final Path outDir;

    public ImageExporter(Path outDir) {
        this.outDir = outDir;
    }

    public abstract Path export(String expr) throws Exception;
}
