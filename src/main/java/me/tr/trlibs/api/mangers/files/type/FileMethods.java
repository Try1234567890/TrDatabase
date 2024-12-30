package me.tr.trlibs.api.mangers.files.type;

import me.tr.trlibs.api.mangers.files.parts.Section;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This interface contains all methods a File must have.
 */
public interface FileMethods {
    boolean move(java.io.File newPath);

    boolean copy(java.io.File newFile);

    boolean rename(String newName);

    File create();

    boolean delete();

    boolean save();

    Map<String, Object> load();

    List<String> getStringList(String path);

    List<Byte> getByteList(String path);

    List<Short> getShortList(String path);

    List<Integer> getIntList(String path);

    List<Long> getLongList(String path);

    List<Float> getFloatList(String path);

    List<Double> getDoubleList(String path);

    List<Boolean> getBooleanList(String path);

    List<Character> getCharList(String path);

    List<Object> getObjectList(String path);

    String[] getStringArray(String path, String[] def);

    Byte[] getByteArray(String path, Byte[] def);

    Short[] getShortArray(String path, Short[] def);

    Integer[] getIntArray(String path, Integer[] def);

    Long[] getLongArray(String path, Long[] def);

    Float[] getFloatArray(String path, Float[] def);

    Double[] getDoubleArray(String path, Double[] def);

    Boolean[] getBooleanArray(String path, Boolean[] def);

    Character[] getCharArray(String path, Character[] def);

    Object[] getObjectArray(String path, Object[] def);

    String[] getStringArray(String path);

    Byte[] getByteArray(String path);

    Short[] getShortArray(String path);

    Integer[] getIntArray(String path);

    Long[] getLongArray(String path);

    Float[] getFloatArray(String path);

    Double[] getDoubleArray(String path);

    Boolean[] getBooleanArray(String path);

    Character[] getCharArray(String path);

    Object[] getObjectArray(String path);

    String getString(String path, String def);

    Byte getByte(String path, Byte def);

    Short getShort(String path, Short def);

    Integer getInt(String path, Integer def);

    Long getLong(String path, Long def);

    Float getFloat(String path, Float def);

    Double getDouble(String path, Double def);

    Boolean getBoolean(String path, Boolean def);

    Character getChar(String path, Character def);

    Object getObject(String path, Object def);

    String getString(String path);

    Byte getByte(String path);

    Short getShort(String path);

    Integer getInt(String path);

    Long getLong(String path);

    Float getFloat(String path);

    Double getDouble(String path);

    Boolean getBoolean(String path);

    Character getChar(String path);

    Object getObject(String path);

    Section getSection(String path);

    Set<String> getKeys();

    Collection<Object> getValues();

    java.io.File getFile();

    String getName();

    @Override
    String toString();
}
