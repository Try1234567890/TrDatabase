package me.tr.trlibs;

import me.tr.trlibs.api.logger.Logger;
import me.tr.trlibs.api.mangers.database.StorageManager;
import me.tr.trlibs.api.mangers.files.FileManager;
import me.tr.trlibs.api.mangers.files.parts.Section;
import me.tr.trlibs.api.mangers.files.type.File;

public class TrLibs {
    private static TrLibs main;
    private Logger logger;
    private StorageManager storageManager;
    private FileManager fileManager;

    public void initialize() {
        main = this;
        logger = new Logger();
        fileManager = new FileManager();
        storageManager = new StorageManager();
    }

    public TrLibs() {
        initialize();
    }

    //public static void main(String[] args) {
    //    File file = new TrLibs().getFileManager().loadFile("F:/Java/TrLibs/src/main/java/resources/config.yaml");
    //    Section section1 = file.getSection("sections");
    //    Section section2 = section1.getSection("section");
    //    System.out.println(section2.getFullPath());
    //    System.out.println(section2.getString("test"));
    //}

    public Logger getLogger() {
        return logger;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public StorageManager getStorageManager() {
        return storageManager;
    }

    public static TrLibs getMain() {
        return main;
    }
}
