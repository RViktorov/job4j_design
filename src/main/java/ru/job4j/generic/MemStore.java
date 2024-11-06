package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public class MemStore<T extends Base> implements Store<T> {
    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        if (!storage.containsKey(model.getId())) {
            storage.put(model.getId(), model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        if (storage.containsKey(id)) {
            storage.put(id, model);
            result = true;
        }
        return result;
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }

    @Override
    public T findById(String id) {
        T result = null;
        for (String data : storage.keySet()) {
            if (data.equals(id)) {
                result = storage.get(data);
                break;
            }
        }
        return result;
    }
}