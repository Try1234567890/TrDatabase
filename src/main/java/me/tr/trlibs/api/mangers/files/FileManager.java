package me.tr.trlibs.api.mangers.files;

import me.tr.trlibs.TrLibs;
import me.tr.trlibs.api.logger.Logger;
import me.tr.trlibs.api.mangers.files.helper.MultipleFiles;
import me.tr.trlibs.api.mangers.files.type.File;
import me.tr.trlibs.api.mangers.files.type.json.JsonFile;
import me.tr.trlibs.api.mangers.files.type.yaml.YamlFile;
import org.jetbrains.annotations.Nullable;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class FileManager {
    private final TrLibs main = TrLibs.getMain();

    public FileManager() {
    }

    /**
     * Try to get a new instance of {@link File} by file extension
     *
     * @param path Path to file where extract extension
     * @return new instance of {@link File}
     */
    public @Nullable File getFileByExtension(String path) {
        if (isYaml(path)) {
            return new YamlFile(path);
        } else if (isJson(path)) {
            return new JsonFile(path);
        } else return null;
    }

    /**
     * Try to get a new instance of {@link File} by file extension
     *
     * @param file File where extract extension
     * @return new instance of {@link File}
     */
    public @Nullable File getFileByExtension(java.io.File file) {
        if (isYaml(file)) {
            return new YamlFile(file);
        } else if (isJson(file)) {
            return new JsonFile(file);
        } else return null;
    }

    /**
     * Load all files in path specified
     *
     * @param path Path where try to load all files.
     * @return An instance of {@link MultipleFiles}
     */
    public @Nullable MultipleFiles loadFiles(java.io.File path) {
        if (!path.exists() || !path.isDirectory()) {
            main.getLogger().log("Path " + path.getPath() + " doesn't exists or isn't a directory.", Logger.Levels.WARNING);
            return null;
        }
        if (path.listFiles() == null) {
            main.getLogger().log("Path " + path.getPath() + " doesn't contains files.", Logger.Levels.WARNING);
            return null;
        }
        return new MultipleFiles(path);
    }


    /**
     * Load all files in path specified
     *
     * @param pathStr Path where try to load all files.
     * @return An instance of {@link MultipleFiles}
     */
    public @Nullable MultipleFiles loadFiles(String pathStr) {
        java.io.File path = new java.io.File(pathStr);
        if (!path.exists()) {
            main.getLogger().log("Path " + path.getPath() + " doesn't exists.", Logger.Levels.WARNING);
            return null;
        }
        if (path.listFiles() == null) {
            main.getLogger().log("Path " + path.getPath() + " doesn't contains files.", Logger.Levels.WARNING);
            return null;
        }
        return new MultipleFiles(path);
    }

    /**
     * Load file to a supported file
     *
     * @param file File to load from
     * @return An instance of {@link File}
     */
    public @Nullable File loadFile(java.io.File file) {
        try {
            if (!file.exists() && !file.isFile()) {
                main.getLogger().error("Error while loading file " + file.getPath() + ". " + file.getPath() + " not found.");
                return null;
            }
            if (isSupportedFile(file))
                return getFileByExtension(file);
            else
                main.getLogger().log("Error while loading file " + file.getPath() + ". " + file.getPath() + " not supported: File " + getExtension(file) + " isn't supported.", Logger.Levels.ERROR);
        } catch (NullPointerException e) {
            main.getLogger().log("Error while loading file " + file.getPath() + ". " + file.getPath() + " not found: " + e.getMessage(), Logger.Levels.ERROR);
        }
        return null;
    }

    /**
     * Load file to a supported file
     *
     * @param path File path to load from
     * @return An instance of {@link File}
     */
    public @Nullable File loadFile(String path) {
        java.io.File file = new java.io.File(path);
        return loadFile(file);
    }

    /**
     * Save {@link java.io.File} at outFileDir got from jarDir
     *
     * @param jarDir     Directory to file jar.
     * @param inFileDir  Directory to file inside jar.
     * @param outFileDir Directory to where to save the file.
     * @return {@code true} if operation ends successfully, otherwise {@code false}.
     */
    public File saveFileFromJar(java.io.File jarDir, java.io.File inFileDir, java.io.File outFileDir) {
        if (!isSupportedFile(outFileDir)) {
            main.getLogger().log("Error while saving to file " + outFileDir + ". " + outFileDir + " not supported: File " + getExtension(outFileDir) + " isn't supported.", Logger.Levels.ERROR);
            return null;
        }
        try (FileWriter out = new FileWriter(outFileDir)) {
            if (!outFileDir.exists() || !outFileDir.isFile())
                if (outFileDir.createNewFile())
                    main.getLogger().log("File " + outFileDir + " created successfully.", Logger.Levels.SUCCESS);
            InputStream is = getFileFromJar(jarDir, inFileDir);
            if (is == null) return null;
            while (is.available() > 0) {
                out.write(is.read());
            }
            is.close();
            return loadFile(outFileDir);
        } catch (IOException e) {
            main.getLogger().log("Error while saving from jar file " + outFileDir + ". " + outFileDir + " not found: " + e.getMessage(), Logger.Levels.ERROR);
        } catch (SecurityException e) {
            main.getLogger().log("Error while got file " + outFileDir + ". No permission to manage " + outFileDir + ": " + e.getMessage(), Logger.Levels.ERROR);
        }
        return null;
    }

    /**
     * Save {@link java.io.File} at outFileDir got from jarDir
     *
     * @param jarDir     Directory to file jar.
     * @param inFileDir  Directory to file inside jar.
     * @param outFileDir Directory to where to save the file.
     * @return {@code true} if operation ends successfully, otherwise {@code false}.
     */
    public File saveFileFromJar(String jarDir, String inFileDir, String outFileDir) {
        return saveFileFromJar(new java.io.File(jarDir), new java.io.File(inFileDir), new java.io.File(outFileDir));
    }

    /**
     * Get {@link InputStream} at fileDir inside jar at jarDir
     *
     * @param jarDir  Directory to file jar.
     * @param fileDir Directory to file inside jar.
     * @return {@link InputStream} if file exists, otherwise {@code null}.
     */
    public @Nullable InputStream getFileFromJar(java.io.File jarDir, java.io.File fileDir) {
        if (!jarDir.exists() || !jarDir.isFile()) {
            main.getLogger().log("Error while loading file " + jarDir.getPath() + ". File " + jarDir.getPath() + " not found.", Logger.Levels.ERROR);
            return null;
        }
        if (!hasExtension(jarDir, ".jar")) {
            main.getLogger().log("Error while loading file " + jarDir.getPath() + ". File " + jarDir.getPath() + " isn't a .jar file", Logger.Levels.ERROR);
            return null;
        }
        try {
            if (!fileDir.exists() || !fileDir.isFile())
                if (fileDir.createNewFile())
                    main.getLogger().log("File " + fileDir.getPath() + " created successfully.", Logger.Levels.SUCCESS);
            if (!isSupportedFile(fileDir)) {
                main.getLogger().log("Error while loading file " + fileDir.getPath() + ". " + fileDir.getPath() + " not supported: File " + getExtension(fileDir) + " isn't supported.", Logger.Levels.ERROR);
                return null;
            }
            JarFile jarFile = new JarFile(jarDir);
            ZipEntry file = jarFile.getEntry(fileDir.getPath());
            if (file == null) return null;
            return jarFile.getInputStream(file);
        } catch (IOException e) {
            main.getLogger().log("Error while got file " + fileDir + ". " + fileDir + " not found: " + e.getMessage(), Logger.Levels.ERROR);
        } catch (SecurityException e) {
            main.getLogger().log("Error while got file " + fileDir + ". No permission to manage " + fileDir + ": " + e.getMessage(), Logger.Levels.ERROR);
        }
        return null;
    }

    /**
     * Get {@link InputStream} at fileDir inside jar at jarDir
     *
     * @param jarDirStr  Directory to file jar.
     * @param fileDirStr Directory to file inside jar.
     * @return {@link InputStream} if file exists, otherwise {@code null}.
     */
    public @Nullable InputStream getFileFromJar(String jarDirStr, String fileDirStr) {
        return getFileFromJar(new java.io.File(jarDirStr), new java.io.File(fileDirStr));
    }

    /**
     * Checks if the given file is a supported file
     *
     * @param file File to check
     * @return true if the file is a supported file, otherwise false
     */
    public boolean isSupportedFile(java.io.File file) {
        return isYaml(file) || isJson(file);
    }

    /**
     * Checks if the given file is a supported file
     *
     * @param fileName File name to check
     * @return true if the file is a supported file, otherwise false
     */
    public boolean isSupportedFile(String fileName) {
        return isYaml(fileName) || isJson(fileName);
    }

    /**
     * Check if the file specified is a yaml file.
     *
     * @param file the file to check.
     * @return true if the instance is a yaml file, otherwise false.
     */
    public boolean isYaml(java.io.File file) {
        return isYaml(file.getName());
    }

    /**
     * Check if the file name is a yaml file.
     *
     * @param file The file name to check.
     * @return true if the instance is a yaml file, otherwise false.
     */
    public boolean isYaml(String file) {
        return hasExtension(file, "yml") || hasExtension(file, "yaml");
    }

    /**
     * Check if the file name is a json file.
     *
     * @param file The file to check.
     * @return true if the instance is a json file, otherwise false.
     */
    public boolean isJson(java.io.File file) {
        return isJson(file.getName());
    }

    /**
     * Check if the file name is a json file.
     *
     * @param file The file name to check.
     * @return true if the instance is a json file, otherwise false.
     */
    public boolean isJson(String file) {
        return hasExtension(file, "json");
    }

    /**
     * Check if the file has the specified extension.
     *
     * @param file      File to check
     * @param extension Extension to check for.
     * @return true if the file has the specified extension, otherwise false.
     */
    public boolean hasExtension(java.io.File file, String extension) {
        return hasExtension(file.getName(), extension);
    }

    /**
     * Check if the file has the specified extension.
     *
     * @param fileName  File name to check
     * @param extension Extension to check for.
     * @return true if the file has the specified extension, otherwise false.
     */
    public boolean hasExtension(String fileName, String extension) {
        return getExtension(fileName).equals(extension);
    }

    /**
     * Get the extension of the specified file.
     *
     * @param name File name where extract file extension from
     * @return extension of the specified file extract
     */
    public String getExtension(String name) {
        String[] split = name.split("\\.");
        return split[split.length - 1];
    }

    /**
     * Get the extension of the specified file.
     *
     * @param file File where extract file extension from
     * @return extension of the specified file extract
     */
    public String getExtension(java.io.File file) {
        String[] split = file.getName().split("\\.");
        return split[split.length - 1];
    }
}
