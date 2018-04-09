package practice.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.IntPredicate;

public class Util {

    public static IntPredicate evenNumber = number -> number % 2 == 0;

    public static List reverse(List list){
        List newList = new ArrayList(list);
        Collections.reverse(newList);
        return newList;
    }

    public static void delay(){
        delay(1);
    }

    public static void delay(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
