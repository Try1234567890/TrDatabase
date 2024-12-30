package me.tr.trlibs.api.mangers.files.type.yaml;

import me.tr.trlibs.TrLibs;
import me.tr.trlibs.api.logger.Logger;
import me.tr.trlibs.api.mangers.files.type.File;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a Yaml file saved in memory
 */
public class YamlFile extends File {
    private final TrLibs main = TrLibs.getMain();
    private final Yaml yaml = new Yaml();
    private Map<String, Object> data = new HashMap<>();
    private java.io.File file = null;

    /**
     * Create a new instance of YamlFile.
     *
     * @param file The file to load from
     */
    public YamlFile(java.io.File file) {
        super(file, loadData(file));
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    main.getLogger().log("File " + file.getPath() + " created successfully.", Logger.Levels.SUCCESS);
                }
            } catch (IOException e) {
                main.getLogger().log("Error while creating file " + file.getPath() + ": " + e.getMessage(), Logger.Levels.ERROR);
            }
        } else if (!main.getFileManager().isYaml(file)) {
            main.getLogger().log("Error while loading file " + file.getAbsoluteFile() + ": File " + file.getAbsoluteFile() + " isn't a yaml file. ", Logger.Levels.ERROR);
            return;
        }
        this.file = file;
        data = load();
    }

    /**
     * Create a new instance of YamlFile.
     *
     * @param path The path to file to load from.
     */
    public YamlFile(String path) {
        this(new java.io.File(path));
    }

    /**
     * Save virtual file structured in a {@link Map} to it file.
     *
     * @return {@code true} if operation ends successfully, otherwise {@code false}.
     */
    @Override
    public boolean save() {
        try (FileWriter writer = new FileWriter(file)) {
            yaml.dump(data, writer);
            return true;
        } catch (IOException e) {
            main.getLogger().log("Error while saving file " + file.getAbsoluteFile() + ": " + e.getMessage(), Logger.Levels.ERROR);
        }
        return false;
    }


    @Override
    public Map<String, Object> load() {
        return loadData(file);
    }

    private static Map<String, Object> loadData(java.io.File file) {
        try {
            if (!file.exists()) return null;
            try (FileReader reader = new FileReader(file)) {
                return new Yaml().loadAs(reader, Map.class);
            }
        } catch (IOException e) {
            TrLibs.getMain().getLogger().log("Error while loading file " + file.getAbsoluteFile() + ": " + e.getMessage(), Logger.Levels.ERROR);
        }
        return new HashMap<>();
    }
}
