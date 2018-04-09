package practice;

import practice.model.Movie;
import practice.model.Veiculo;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@WebListener
public class AppListener implements ServletContextListener {


    @PersistenceContext(unitName = "my-persistence-unit")
    EntityManager entityManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Iniciou a sessão!");
    }



    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Derrubou a sessão!");
    }

}
