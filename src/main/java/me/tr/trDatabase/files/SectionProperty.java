package me.tr.trDatabase.files;

import me.tr.trFiles.configuration.Section;

public class SectionProperty {
    private Section section;
    private String[] keys;
    private Object def;

    public SectionProperty(Section section, String[] keys, Object def) {
        this.section = section;
        this.keys = keys;
        this.def = def;
    }

    public Section section() {
        return section;
    }

    public void section(Section section) {
        this.section = section;
    }

    public String[] keys() {
        return keys;
    }

    public void keys(String[] keys) {
        this.keys = keys;
    }

    public Object def() {
        return def;
    }

    public void def(Object def) {
        this.def = def;
    }
}
