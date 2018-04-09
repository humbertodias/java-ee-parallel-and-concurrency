package practice.test;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortTest {

    List expected = List.of(2, 4, 6, 8, 10);
    IntStream range = IntStream.rangeClosed(1, 10).filter(Util.evenNumber);


    @Test
    public void sortReverse(){

        List<Integer> collected = range.boxed()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        List expectedReverse = Util.reverse(expected);
        assertEquals(expectedReverse,collected);

    }

    @Test
    public void sort(){

        List<Integer> collected = range.boxed()
                .sorted()
                .collect(Collectors.toList());
        assertEquals(expected,collected);

    }

}
