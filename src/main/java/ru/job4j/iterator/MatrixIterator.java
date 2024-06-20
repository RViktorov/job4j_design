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
        if (row <= data.length - 1) {
            while (data[row].length == 0 && row < data.length - 1) {
                row++;
            }
        }
        return row < data.length && column < data[row].length;
    }

    @Override
    public Integer next() {
        Integer result;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (column < data[row].length - 1) {
            result = data[row][column++];
        } else {

            result = data[row++][column];
            column = 0;
        }
        return result;
    }
}