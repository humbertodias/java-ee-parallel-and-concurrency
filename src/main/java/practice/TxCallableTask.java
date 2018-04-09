package practice;

import javax.transaction.UserTransaction;
import java.util.concurrent.Callable;

public class TxCallableTask /*implements Callable<Long>*/ {
    /*
    long id;

    public TxCallableTask(long i) {

        this.id = i;

    }

    public Long call() {

        long value = 0;

        UserTransaction tx = lookupUserTransaction();

        SimpleEJB ejb = lookupEJB();

        try {

            tx.begin();

            value = ejb.calculate(id); // Do Transactions here

            tx.commit();

        } catch (Exception e) {

            e.printStackTrace();

            try {
                tx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }

        return value;

    }

// Lookup EJB and UserTransaction here ..
*/

}