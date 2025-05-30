package me.tr.trDatabase.database.query.list.select;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SelectResult {
    private List<Map<String, Object>> results = new ArrayList<>();

    public SelectResult(List<Map<String, Object>> results) {
        this.results = results;
    }

    public List<Map<String, Object>> select() {
        return results;
    }

    public @Nullable Map<String, Object> selectLast() {
        return results.isEmpty() ? null : results.getFirst();
    }

    public @Nullable Map<String, Object> selectFirst() {
        return results.isEmpty() ? null : results.getFirst();
    }

    public @Nullable Map<String, Object> selectAt(int index) {
        return results.isEmpty() ? null : results.get(index);
    }


    @Override
    public String toString() {
        return results.toString();
    }
}
