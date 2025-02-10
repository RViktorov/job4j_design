package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    MapEntry<K, V>[] table = new MapEntry[capacity];

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] tempTable = new MapEntry[capacity];
        for (MapEntry<K, V> mapEntry : table) {
            if (mapEntry != null) {
                int position = hash(Objects.hashCode(mapEntry.key) & (tempTable.length - 1));
                tempTable[position] = mapEntry;
            }
        }
        table = tempTable;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash(hash) & (table.length - 1);
    }

    private int calculationPosition(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private boolean compareKeys(K key1, K key2) {
        return Objects.equals(Objects.hashCode(key1), Objects.hashCode(key2))
                && Objects.equals(key1, key2);
    }

    @Override
    public boolean put(K key, V value) {
        if (count == capacity * LOAD_FACTOR) {
            expand();
        }
        boolean result = false;
        MapEntry<K, V> entry = new MapEntry<>(key, value);
        int position = calculationPosition(entry.key);
        if (table[position] == null) {
            table[position] = entry;
            result = true;
            count++;
            modCount++;
        }
        return result;
    }

    @Override
    public V get(K key) {
        V result = null;
        int position = calculationPosition(key);
        if (table[position] != null
                && compareKeys(key, table[position].key)) {
            result = table[position].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int position = indexFor(hash(Objects.hashCode(key)));
        if (table[position] != null
                && compareKeys(key, table[position].key)) {
            table[position] = null;
            result = true;
            count--;
            modCount++;
        }
        return result;
    }


    @Override
    public Iterator<K> iterator() {
        int expectedModCount = modCount;
        return new Iterator<>() {
            private int position;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (position < table.length - 1 && table[position] == null) {
                    position++;
                }
                return position < table.length && table[position] != null;
            }

            @Override
            public K next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[position++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof MapEntry)) {
                return false;
            }
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            return Objects.equals(key, mapEntry.key) && Objects.equals(value, mapEntry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        @Override
        public String toString() {
            return "MapEntry{"
                    + "key=" + key
                    + ", value=" + value
                    + '}';
        }
    }
}