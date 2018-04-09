package practice;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/ExecutorServlet")
public class MyExecutorServlet extends HttpServlet {

    @Resource(name = "DefaultManagedExecutorService")
    ManagedExecutorService executor;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        executor.execute(new SimpleTask());
        writer.write("Task practice.SimpleTask executed! check logs");
    }

}
