package me.tr.trlibs.api.mangers.files.type;

import me.tr.trlibs.TrLibs;
import me.tr.trlibs.api.logger.Logger;
import me.tr.trlibs.api.mangers.files.parts.Section;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * This abstract class represents a File.
 * A file manager needs to extend this class to work properly.
 */
public abstract class File implements FileMethods {
    private final TrLibs main = TrLibs.getMain();
    private final Map<String, Object> data;
    private final java.io.File file;

    /**
     * Create a new instance of the File class
     *
     * @param file File use by default for all methods
     * @param data Contents of the file structured as a {@code Map<String, Object>}
     */
    public File(java.io.File file, Map<String, Object> data) {
        this.data = data;
        this.file = file;
    }

    /**
     * Try to move file to new path
     *
     * @param newPath Path where move file.
     * @return true if file exists, otherwise false.
     */
    @Override
    public boolean move(java.io.File newPath) {
        try {
            if (getFile().exists()) {
                Files.move(getFile().toPath(), newPath.toPath());
                return true;
            }
        } catch (IOException e) {
            main.getLogger().log("Error while moving file " + getFile().getAbsoluteFile() + ": " + e.getMessage(), Logger.Levels.ERROR);
        }
        return false;
    }

    /**
     * Try to copy file to another file.
     *
     * @param newFile Another file where copy original file.
     * @return true if file exists, otherwise false.
     */
    @Override
    public boolean copy(java.io.File newFile) {
        try {
            if (getFile().exists()) {
                Files.copy(getFile().toPath(), newFile.toPath());
                return true;
            }

        } catch (IOException e) {
            main.getLogger().log("Error while coping file " + getFile().getAbsoluteFile() + ": " + e.getMessage(), Logger.Levels.ERROR);
        }
        return false;
    }

    /**
     * Try to rename file with new name.
     *
     * @param newName New name assign to file.
     * @return true if renamed successfully, otherwise false.
     */
    @Override
    public boolean rename(String newName) {
        if (getFile().exists()) {
            return getFile().renameTo(new java.io.File(getFile().getPath(), newName));
        }
        return false;
    }

    /**
     * Try to create a new file.
     *
     * @return The new File created if operation end successfully, otherwise null.
     */
    @Override
    public @Nullable java.io.File create() {
        try {
            if (!getFile().exists()) {
                if (getFile().createNewFile()) {
                    return getFile();
                }
            }
        } catch (IOException e) {
            main.getLogger().log("Error while creating file " + getFile().getAbsoluteFile() + ": " + e.getMessage(), Logger.Levels.ERROR);
        }
        return null;
    }

    /**
     * Try to delete file.
     *
     * @return true if deleted successfully, otherwise false.
     */
    @Override
    public boolean delete() {
        if (getFile().exists()) {
            return getFile().delete();
        }
        return false;
    }

    /**
     * This needs to be specified for each file type.
     * Save file. Convert from {@link java.io.File} to {@link  Map} (Serialize)
     *
     * @return true If saved successfully, otherwise else false
     */
    @Override
    public abstract boolean save();

    /**
     * This needs to be specified for each file type.
     * Save file. Convert from {@link  Map} to {@link java.io.File} (Deserialize)
     *
     * @return true If saved successfully, otherwise else false
     */
    @Override
    public abstract Map<String, Object> load();

    @Override
    public List<String> getStringList(String path) {
        Object value = getObject(path);
        return value instanceof List ? Arrays.stream(getStringArray(path)).toList() : new ArrayList<>();
    }

    @Override
    public List<Byte> getByteList(String path) {
        Object value = getObject(path);
        return value instanceof List ? Arrays.stream(getByteArray(path)).toList() : new ArrayList<>();
    }

    @Override
    public List<Short> getShortList(String path) {
        Object value = getObject(path);
        return value instanceof List ? Arrays.stream(getShortArray(path)).toList() : new ArrayList<>();
    }

    @Override
    public List<Integer> getIntList(String path) {
        Object value = getObject(path);
        return value instanceof List ? Arrays.stream(getIntArray(path)).toList() : new ArrayList<>();
    }

    @Override
    public List<Long> getLongList(String path) {
        Object value = getObject(path);
        return value instanceof List ? Arrays.stream(getLongArray(path)).toList() : new ArrayList<>();
    }

    @Override
    public List<Float> getFloatList(String path) {
        Object value = getObject(path);
        return value instanceof List ? Arrays.stream(getFloatArray(path)).toList() : new ArrayList<>();
    }

    @Override
    public List<Double> getDoubleList(String path) {
        Object value = getObject(path);
        return value instanceof List ? Arrays.stream(getDoubleArray(path)).toList() : new ArrayList<>();
    }

    @Override
    public List<Boolean> getBooleanList(String path) {
        Object value = getObject(path);
        return value instanceof List ? Arrays.stream(getBooleanArray(path)).toList() : new ArrayList<>();
    }

    @Override
    public List<Character> getCharList(String path) {
        Object value = getObject(path);
        return value instanceof List ? Arrays.stream(getCharArray(path)).toList() : new ArrayList<>();
    }

    @Override
    public List<Object> getObjectList(String path) {
        Object value = getObject(path);
        return value instanceof List ? Arrays.stream(getObjectArray(path)).toList() : new ArrayList<>();
    }

    @Override
    public String[] getStringArray(String path, String[] def) {
        Object value = getObject(path, def);
        if (value instanceof List) {
            return getStringArray(path);
        }
        return def;
    }

    @Override
    public Byte[] getByteArray(String path, Byte[] def) {
        Object value = getObject(path, def);
        if (value instanceof List) {
            return getByteArray(path);
        }
        return def;
    }

    @Override
    public Short[] getShortArray(String path, Short[] def) {
        Object value = getObject(path, def);
        if (value instanceof List) {
            return getShortArray(path);
        }
        return def;
    }

    @Override
    public Integer[] getIntArray(String path, Integer[] def) {
        Object value = getObject(path, def);
        if (value instanceof List) {
            return getIntArray(path);
        }
        return def;
    }

    @Override
    public Long[] getLongArray(String path, Long[] def) {
        Object value = getObject(path, def);
        if (value instanceof List) {
            return getLongArray(path);
        }
        return def;
    }

    @Override
    public Float[] getFloatArray(String path, Float[] def) {
        Object value = getObject(path, def);
        if (value instanceof List) {
            return getFloatArray(path);
        }
        return def;
    }

    @Override
    public Double[] getDoubleArray(String path, Double[] def) {
        Object value = getObject(path, def);
        if (value instanceof List) {
            return getDoubleArray(path);
        }
        return def;
    }

    @Override
    public Boolean[] getBooleanArray(String path, Boolean[] def) {
        Object value = getObject(path, def);
        if (value instanceof List) {
            return getBooleanArray(path);
        }
        return def;
    }

    @Override
    public Character[] getCharArray(String path, Character[] def) {
        Object value = getObject(path, def);
        if (value instanceof List) {
            return getCharArray(path);
        }
        return def;
    }

    @Override
    public Object[] getObjectArray(String path, Object[] def) {
        Object value = getObject(path, def);
        if (value instanceof List) {
            return getObjectArray(path);
        }
        return def;
    }

    @Override
    @SuppressWarnings("unchecked")
    public String[] getStringArray(String path) {
        Object value = getObject(path);
        if (value instanceof List) {
            return ((List<String>) value).toArray(new String[0]);
        }
        return new String[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Byte[] getByteArray(String path) {
        Object value = getObject(path);
        if (value instanceof List) {
            return ((List<Byte>) value).toArray(new Byte[0]);
        }
        return new Byte[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Short[] getShortArray(String path) {
        Object value = getObject(path);
        if (value instanceof List) {
            return ((List<Short>) value).toArray(new Short[0]);
        }
        return new Short[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Integer[] getIntArray(String path) {
        Object value = getObject(path);
        if (value instanceof List) {
            return ((List<Integer>) value).toArray(new Integer[0]);
        }
        return new Integer[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Long[] getLongArray(String path) {
        Object value = getObject(path);
        if (value instanceof List) {
            return ((List<Long>) value).toArray(new Long[0]);
        }
        return new Long[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Float[] getFloatArray(String path) {
        Object value = getObject(path);
        if (value instanceof List) {
            return ((List<Float>) value).toArray(new Float[0]);
        }
        return new Float[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Double[] getDoubleArray(String path) {
        Object value = getObject(path);
        if (value instanceof List) {
            return ((List<Double>) value).toArray(new Double[0]);
        }
        return new Double[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Boolean[] getBooleanArray(String path) {
        Object value = getObject(path);
        if (value instanceof List) {
            return ((List<Boolean>) value).toArray(new Boolean[0]);
        }
        return new Boolean[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Character[] getCharArray(String path) {
        Object value = getObject(path);
        if (value instanceof List) {
            return ((List<Character>) value).toArray(new Character[0]);
        }
        return new Character[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object[] getObjectArray(String path) {
        Object value = getObject(path);
        if (value instanceof List) {
            return ((List<Object>) value).toArray(new Object[0]);
        }
        return new Object[0];
    }

    @Override
    public String getString(String path, String def) {
        Object value = getObject(path);
        return value instanceof String ? (String) value : def;
    }

    @Override
    public Byte getByte(String path, Byte def) {
        Object value = getObject(path);
        return value instanceof Byte ? (Byte) value : def;
    }

    @Override
    public Short getShort(String path, Short def) {
        Object value = getObject(path);
        return value instanceof Short ? (Short) value : def;
    }

    @Override
    public Integer getInt(String path, Integer def) {
        Object value = getObject(path);
        return value instanceof Integer ? (Integer) value : def;
    }

    @Override
    public Long getLong(String path, Long def) {
        Object value = getObject(path);
        return value instanceof Long ? (Long) value : def;
    }

    @Override
    public Float getFloat(String path, Float def) {
        Object value = getObject(path);
        return value instanceof Float ? (Float) value : def;
    }

    @Override
    public Double getDouble(String path, Double def) {
        Object value = getObject(path);
        return value instanceof Double ? (Double) value : def;
    }

    @Override
    public Boolean getBoolean(String path, Boolean def) {
        Object value = getObject(path);
        return value instanceof Boolean ? (Boolean) value : def;
    }

    @Override
    public Character getChar(String path, Character def) {
        Object value = getObject(path);
        return value instanceof Character ? (Character) value : def;
    }

    @Override
    public Object getObject(String path, Object def) {
        Object value = getObject(path);
        return value != null ? value : def;
    }

    @Override
    public String getString(String path) {
        Object value = getObject(path);
        return value instanceof String ? (String) value : null;
    }

    @Override
    public Byte getByte(String path) {
        Object value = getObject(path);
        return value instanceof Byte ? (Byte) value : null;
    }

    @Override
    public Short getShort(String path) {
        Object value = getObject(path);
        return value instanceof Short ? (Short) value : null;
    }

    @Override
    public Integer getInt(String path) {
        Object value = getObject(path);
        return value instanceof Integer ? (Integer) value : null;
    }

    @Override
    public Long getLong(String path) {
        Object value = getObject(path);
        return value instanceof Long ? (Long) value : null;
    }

    @Override
    public Float getFloat(String path) {
        Object value = getObject(path);
        return value instanceof Float ? (Float) value : null;
    }

    @Override
    public Double getDouble(String path) {
        Object value = getObject(path);
        return value instanceof Double ? (Double) value : null;
    }

    @Override
    public Boolean getBoolean(String path) {
        Object value = getObject(path);
        return value instanceof Boolean ? (Boolean) value : null;
    }

    @Override
    public Character getChar(String path) {
        Object value = getObject(path);
        return value instanceof Character ? (Character) value : null;
    }

    @Override
    public Object getObject(String path) {
        String[] parts = path.split("\\.");
        Object current = null;
        for (int i = 0; i < parts.length; i++) {
            if (current == null) {
                current = data.get(parts[i]);
            } else {
                current = ((Map<?, ?>) current).get(parts[i]);
            }
        }
        return current;
    }

    /**
     * Get {@link Section} in the path specified.
     *
     * @param path The path where search the section.
     * @return {@link Section} in the path if exists.
     */
    @Override
    public Section getSection(String path) {
        return new Section(this, path);
    }

    /**
     * Get all parameter keys of the file at root
     *
     * @return {@link Set} of all parameter keys of the file at root
     */
    @Override
    public Set<String> getKeys() {
        return data.keySet();
    }

    /**
     * Get all parameter keys' values of the file at root
     *
     * @return {@link Collection} of all parameter keys' values of the file at root
     */
    @Override
    public Collection<Object> getValues() {
        return data.values();
    }

    /**
     * Get {@link java.io.File} specified at creation.
     *
     * @return {@link java.io.File} specified at creation.
     */
    @Override
    public java.io.File getFile() {
        return file;
    }

    @Override
    public String getName() {
        return file.getName();
    }

    /**
     * Get {@link java.io.File} specified at creation in a {@link String}.
     *
     * @return {@link java.io.File} specified at creation in a {@link String}.
     */
    @Override
    public String toString() {
        return data.toString();
    }

    /**
     * Get {@link java.io.File} specified at creation in a {@link Map}.
     *
     * @return {@link java.io.File} specified at creation in a {@link Map}.
     */
    public Map<String, Object> getMapFile() {
        return data;
    }


}
