package me.tr.trlibs.api.mangers.files.helper;

import me.tr.trlibs.TrLibs;
import me.tr.trlibs.api.logger.Logger;
import me.tr.trlibs.api.mangers.files.type.File;

import java.util.ArrayList;
import java.util.List;

public final class MultipleFiles {
    private java.io.File path;
    private List<File> files = new ArrayList<>();

    public MultipleFiles(java.io.File path) {
        if (!path.exists() || path.listFiles() == null) {
            TrLibs.getMain().getLogger().log("Error: Path cannot be null or empty", Logger.Levels.WARNING);
            return;
        }
        this.path = path;
        java.io.File[] filesInPath = path.listFiles();
        TrLibs main = TrLibs.getMain();
        if (filesInPath == null) {
            main.getLogger().log("Directory is null or not contains files.", Logger.Levels.ERROR);
            return;
        }
        for (java.io.File file : filesInPath) {
            File fileToLoad = main.getFileManager().getFileByExtension(file);
            if (fileToLoad == null) {
                main.getLogger().debug("File " + file.getAbsoluteFile() + " isn't a supported file", Logger.Levels.WARNING);
                continue;
            } else if (fileToLoad.getMapFile() == null || fileToLoad.getMapFile().isEmpty()) {
                main.getLogger().debug("File " + file.getAbsoluteFile() + " is an empty file", Logger.Levels.WARNING);
                continue;
            }
            files.add(fileToLoad);
        }
        if (files.isEmpty()) {
            main.getLogger().log("Directory is null or not contains files.", Logger.Levels.ERROR);
            return;
        }
        loadAll();
    }

    public MultipleFiles(String pathStr) {
        this(new java.io.File(pathStr));
    }

    public MultipleFiles() {
    }


    public void loadAll() {
        for (File file : files) {
            file.load();
        }
    }

    public void saveAll() {
        for (File file : files) {
            file.save();
        }
    }

    public List<File> getFiles() {
        return files;
    }

    public java.io.File getPath() {
        return path;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public void setPath(java.io.File path) {
        this.path = path;
    }
}
