package ru.job4j.collections;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }

    public static void main(String[] args) {
        SimpleStack<Integer> arr = new SimpleStack<>();
        arr.push(1);
        System.out.println(arr.pop());
        System.out.println(arr.pop());
        System.out.println(arr.pop());
    }
}