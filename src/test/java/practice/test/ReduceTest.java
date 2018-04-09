package practice.test;

import org.junit.jupiter.api.Test;

import java.util.OptionalDouble;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.DoubleStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReduceTest {

    DoubleStream doubleStream = DoubleStream.of(1.0, 2.0, 3.0, 4.0);

    @Test
    public void reduceWithoutLambdaDoubles(){
        DoubleBinaryOperator doubleBinaryOperator = new DoubleBinaryOperator() {
            @Override
            public double applyAsDouble(double left, double right) {
                return left + right;
            }
        };
        OptionalDouble reduce = doubleStream.reduce(doubleBinaryOperator);
        assertEquals(10.0, reduce.getAsDouble(), 0.1);
    }

    @Test
    public void reduceWithLambdaDoubles(){
        DoubleBinaryOperator doubleBinaryOperator = (left, right) -> left + right;
        OptionalDouble reduce = doubleStream.reduce(doubleBinaryOperator);
        assertEquals(10.0, reduce.getAsDouble(), 0.1);
    }

}
