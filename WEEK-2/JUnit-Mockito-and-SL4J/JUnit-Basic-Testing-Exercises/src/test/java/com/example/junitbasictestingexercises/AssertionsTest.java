package com.example.junitbasictestingexercises;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AssertionsTest {

    @Test
    public void testAssertions() {
        assertEquals(5, 2 + 3);

        // Assert not equals
        assertNotEquals(4, 2 + 3);

        // Assert true
        assertTrue(10 > 1);

        // Assert false
        assertFalse(1 > 10);

        // Assert null
        String str = null;
        assertNull(str);

        // Assert not null
        Object obj = new Object();
        assertNotNull(obj);

        // Assert same
        String s1 = "hello";
        String s2 = s1;
        assertSame(s1, s2);

        // Assert not same
        String s3 = new String("hello");
        assertNotSame(s1, s3);

        // Assert array equals
        int[] expected = {1, 2, 3};
        int[] actual = {1, 2, 3};
        assertArrayEquals(expected, actual);

        // Assert all
        assertAll(
                () -> assertTrue(3 < 5),
                () -> assertEquals("abc", "a" + "bc"),
                () -> assertNotNull(new Object())
        );
    }
}
