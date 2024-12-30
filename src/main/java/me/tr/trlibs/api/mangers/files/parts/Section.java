package me.tr.trlibs.api.mangers.files.parts;

import me.tr.trlibs.TrLibs;
import me.tr.trlibs.api.logger.Logger;
import me.tr.trlibs.api.mangers.files.type.File;

import java.util.Arrays;
import java.util.Map;

public class Section extends File {
    public static final String PATH_SEPARATOR = "\\.";
    private final TrLibs main = TrLibs.getMain();
    private final Map<String, Object> data;
    private String parent;
    private String path;
    private String name;
    private File file;

    public Section(File file, String path) {
        super(file.getFile(), extractSection(file.getMapFile(), path));
        this.file = file;
        this.path = path;
        String[] parts = path.split(PATH_SEPARATOR);
        parent = parts.length > 1 ? String.join(".", Arrays.copyOf(parts, parts.length - 1)) : null;
        name = parts[parts.length - 1];
        data = extractSection(file.getMapFile(), path);
        if (data == null) {
            main.getLogger().log("Section " + path + " not exists.", Logger.Levels.ERROR);
        }
    }

    @Override
    public Map<String, Object> load() {
        return extractSection(file.load(), path);
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> extractSection(Map<String, Object> data, String path) {
        if (data == null || path == null || path.isEmpty()) {
            return null;
        }
        String[] keys = path.split(PATH_SEPARATOR);
        Map<String, Object> currentSection = data;
        for (String key : keys) {
            if (currentSection.containsKey(key) && currentSection.get(key) instanceof Map) {
                currentSection = (Map<String, Object>) currentSection.get(key);
            } else {
                return null;
            }
        }
        return currentSection;
    }

    public String getPath() {
        return path;
    }

    public File getFileManager() {
        return file;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public java.io.File getFile() {
        return file.getFile();
    }

    public String getParent() {
        return parent;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getFullPath() {
        return parent != null ? parent + "." + name : name;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean save() {
        return file.save();
    }

    @Override
    public Section getSection(String path) {
        String fullPath = this.path.isEmpty() ? path : this.path + "." + path;
        return super.getSection(fullPath);
    }

    @Override
    public boolean rename(String name) {
        setName(name);
        save();
        return true;
    }

    public Map<String, Object> getMapSection() {
        return data;
    }

    @Override
    public Map<String, Object> getMapFile() {
        return file.getMapFile();
    }
}