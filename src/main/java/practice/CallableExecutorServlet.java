package practice;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@WebServlet("/CallableExecutorServlet")
public class CallableExecutorServlet extends HttpServlet {

    @Resource(name = "DefaultManagedExecutorService")
    ManagedExecutorService executor;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try (PrintWriter writer = response.getWriter()) {

//        Future<Long> futureResult = executor.submit(new CallableTask(5));
            Future<Long> futureResult = executor.submit(new CallableListenerTask(5));

            while (!futureResult.isDone()) {
                Util.delay();
            }

            writer.write("Callable Task returned " + futureResult.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

}