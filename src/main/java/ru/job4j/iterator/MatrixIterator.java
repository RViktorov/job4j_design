package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIterator(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (row < data.length - 1) {
            if (column > data[row].length - 1) {
                row++;
                column = 0;
            }
            while (data[row].length == 0 && row < data.length - 1) {
                row++;
            }
        }
        return row < data.length && column < data[row].length;
    }


    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}