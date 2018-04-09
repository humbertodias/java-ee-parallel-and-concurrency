package practice;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;

@WebServlet("/FactoryExecutorServiceServlet")
public class FactoryExecutorServiceServlet extends HttpServlet {

    @EJB
    PoolExecutorEJB ejb;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final PrintWriter writer = response.getWriter();

        writer.write("Invoking ExecutorService. Check Logs.");

        ExecutorService executorService = ejb.getThreadPoolExecutor();

        executorService.execute(new Runnable() {

            public void run() {

                System.out.println("Message from your Executor!");

            }

        });

    }

}