package me.tr.trDatabase.files;

public class ValueRetriever {
    private SectionProperty sectionProperty;

    public ValueRetriever() {

    }

    public ValueRetriever(SectionProperty sectionProperty) {
        this.sectionProperty = sectionProperty;
    }

    public void sectionProperty(SectionProperty sectionProperty) {
        this.sectionProperty = sectionProperty;
    }

    public String asString() {
        for (String key : sectionProperty.keys()) {
            String value = sectionProperty.section().getString(key);
            if (value != null) {
                return value;
            }
        }
        return sectionProperty.def() instanceof String ? (String) sectionProperty.def() : null;
    }

    public int asInt() {
        for (String key : sectionProperty.keys()) {
            String value = sectionProperty.section().getString(key);
            if (value != null) {
                return Integer.parseInt(value);
            }
        }
        return sectionProperty.def() instanceof Integer ? (Integer) sectionProperty.def() : -1;
    }
}
