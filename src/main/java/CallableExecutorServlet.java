import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@WebServlet("/CallableExecutorServlet")
public class CallableExecutorServlet extends HttpServlet {

    @Resource(name = "DefaultManagedExecutorService")
    ManagedExecutorService executor;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter writer = response.getWriter();

        Future<Long> futureResult = executor.submit(new CallableTask(5));

        while (!futureResult.isDone()) {

            // Wait
            try {
                TimeUnit.SECONDS.sleep(1);
                //Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        try {
            writer.write("Callable Task returned " + futureResult.get());
        } catch ( Exception e) {
            e.printStackTrace();
        }

    }

}