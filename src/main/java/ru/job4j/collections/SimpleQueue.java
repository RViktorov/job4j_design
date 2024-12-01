package ru.job4j.collections;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int sizeOtput;
    private int sizeInput;

    private void swap(SimpleStack<T> in, SimpleStack<T> out) {
        if (sizeInput > 0 && sizeOtput == 0) {
            while (sizeInput != 0) {
                out.push(in.pop());
                sizeOtput++;
                sizeInput--;
            }
        }
    }

    public T poll() {
        if (sizeInput == 0 && sizeOtput == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        swap(input, output);
        T result = output.pop();
        sizeOtput--;
        return result;
    }

    public void push(T value) {
        input.push(value);
        sizeInput++;
    }
}