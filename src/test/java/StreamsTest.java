import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class StreamsTest {

    List expected = List.of(2, 4, 6, 8, 10);
    IntStream range = IntStream.rangeClosed(1, 10);

    @Test
    public void filter(){

        List<Integer> collected = range
                .filter( Util.evenNumber)
                .boxed()
                .collect(Collectors.toList());

        assertEquals(expected,collected);

    }


    @Test
    public void filterParallel(){

        List<Integer> collected = range
                .parallel()
                .filter( Util.evenNumber)
                .boxed()
                .collect(Collectors.toList());

        assertEquals(expected,collected);

    }

}
