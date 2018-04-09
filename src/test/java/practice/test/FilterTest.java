package practice.test;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterTest {

    List words = List.of("one", "two", "three", "four", "five");

    @Test
    public void filter() {
        Predicate<String> predicate = word -> word.contains("o");
        long count = words
                .stream()
                .filter(predicate)
                .count();
        assertEquals(3, count);
    }

}
