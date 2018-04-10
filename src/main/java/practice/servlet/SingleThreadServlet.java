package practice.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SingleThreadServlet")
public class SingleThreadServlet extends HttpServlet {

    List<String> threads = new ArrayList();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        threads.add(Thread.currentThread().getName());
        threads.forEach(resp.getWriter()::println);
    }
}
