package ru.job4j.set;

import ru.job4j.collections.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean result = false;
        if (!contains(value)) {
            set.add(value);
            result = true;
        }
        return result;
    }

    @Override
    public boolean contains(T value) {
        boolean result = false;
        Iterator<T> iter = iterator();
        while (iter.hasNext()) {
            if (Objects.deepEquals(value, iter.next())) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

}