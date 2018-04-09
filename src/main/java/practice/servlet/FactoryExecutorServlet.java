package practice.servlet;

import practice.task.SimpleTask;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedThreadFactory;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/FactoryExecutorServlet")
public class FactoryExecutorServlet extends HttpServlet {

    @Resource(name = "DefaultManagedThreadFactory")
    ManagedThreadFactory factory;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter writer = response.getWriter();

        Thread thread = factory.newThread(new SimpleTask());

        thread.setName("My Managed Thread");

        thread.setPriority(Thread.MAX_PRIORITY);

        thread.start();

        writer.write("Thread started. Check logs");

    }

}