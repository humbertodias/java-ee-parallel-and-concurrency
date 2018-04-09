package practice.servlet;

import practice.task.SimpleTask;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@WebServlet("/ScheduledExecutor")
public class ScheduledExecutor extends HttpServlet {

    @Resource(name = "DefaultManagedScheduledExecutorService")
    ManagedScheduledExecutorService scheduledExecutor;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try (PrintWriter writer = response.getWriter()) {
            ScheduledFuture<?> futureResult = scheduledExecutor.schedule(new SimpleTask(), 10, TimeUnit.SECONDS);
            writer.write("Waiting 10 seconds before firing the task");
        }

    }

}