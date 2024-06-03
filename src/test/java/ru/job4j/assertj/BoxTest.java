package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .contains("ere")
                .startsWithIgnoringCase("S")
                .endsWith("re");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube")
                .contains("be")
                .startsWithIgnoringCase("c")
                .endsWith("be");
    }

    @Test
    void isVertexEqualsEight() {
        Box box = new Box(8, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(8)
                .isEven()
                .isBetween(7, 9)
                .isLessThan(10)
                .isGreaterThan(7)
                .isPositive();
    }

    @Test
    void isVertexEqualsMinusEight() {
        Box box = new Box(-8, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(-1)
                .isGreaterThan(-9)
                .isLessThan(1)
                .isNegative();
    }

    @Test
    void isExist() {
        Box box = new Box(8, 10);
        boolean result = box.isExist();
        assertThat(result).isEqualTo(true);
    }

    @Test
    void isNotExist() {
        Box box = new Box(12, 10);
        boolean result = box.isExist();
        assertThat(result).isEqualTo(false);
    }

    @Test
    void isAreaCube() {
        Box box = new Box(8, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(600.0, withPrecision(0.005d))
                .isLessThan(700.0)
                .isGreaterThan(500.0)
                .isPositive();
    }

    @Test
    void isAreaDefault() {
        Box box = new Box(12, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(0, withPrecision(0.005d))
                .isGreaterThan(-1);
    }
}