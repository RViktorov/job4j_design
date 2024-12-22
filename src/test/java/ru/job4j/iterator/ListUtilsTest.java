package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {


    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(2, 1, 3);
    }

    @Test
    void whenRemoveIfThree() {
        ListUtils.removeIf(input, i -> i == 3);
        assertThat(input).hasSize(1).containsSequence(1);
    }

    @Test
    void whenReplaceIfThree() {
        ListUtils.replaceIf(input, i -> i == 3, 4);
        assertThat(input).hasSize(2).containsSequence(1, 4);
    }

    @Test
    void whenRemoveAllIfThree() {
        input.add(4);
        input.add(8);
        List<Integer> output = new ArrayList<>(Arrays.asList(1, 2, 3, 6));
        ListUtils.removeAll(input, output);
        assertThat(input).hasSize(2).containsSequence(4, 8);
    }
}