package practice.servlet;

import practice.model.Movie;
import practice.model.Veiculo;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

@WebServlet("/EntityManagerServlet")
@Transactional
public class EntityManagerServlet extends HttpServlet {

    @PersistenceContext(unitName = "my-persistence-unit")
    EntityManager entityManager;

    boolean first = true;

    @Resource(name = "DefaultManagedExecutorService")
    ManagedExecutorService executor;

    @Resource
    private UserTransaction userTransaction;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if(first){
            createDB();
            first=false;
        }
        else{

            Callable<Movie> task = () -> {
                Movie movie = randomMovie();
                userTransaction.begin();
                entityManager.persist( movie );
                userTransaction.commit();
                return movie;
            };

            try {
                executor.submit(task).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }

        try(PrintWriter writer = response.getWriter()){

//            TypedQuery<Veiculo> from_veiculo = entityManager.createQuery("from Veiculo ", Veiculo.class);
//            from_veiculo.getResultList().forEach(writer::println);

            TypedQuery<Movie> movies = entityManager.createQuery("from Movie ", Movie.class);
            movies.getResultList().forEach(writer::println);
        }



    }


    private void createDB(){
        List veiculos = new ArrayList<Veiculo>();
        veiculos.add(new Veiculo(1, "Fiat", "Uno", "disponible", "https://www.localiza.com/brasil-site/geral/Frota/NUNS.png", "A - ECONÔMICO (ECMN)", 50.f, "Características - Duas portas  Cinco pessoas  Duas mala(s) grande(s)  Uma mala(s) pequena(s) Sem Ar-condicionado Sem Dir. Hidráulica  Vidro elétrico"));
        veiculos.add(new Veiculo(2, "Hyundai", "HB20", "disponible", "https://www.localiza.com/brasil-site/geral/Frota/HB2X.png", "C - ECONÔMICO COM AR (EDMR)", 50.f, "Características: Ar-condicionado  Dir. Hidráulica  Vidro elétrico  Trava elétrica  4 portas  Air bag  ABS  5 pessoas  2 mala(s) grande(s)  1 mala(s) pequena(s)"));
        veiculos.add(new Veiculo(3, "Ford", "Sedan", "disponible", "https://www.localiza.com/brasil-site/geral/Frota/KASE.png", "F - INTERMEDIÁRIO", 60.0f, "Características: Ar-condicionado  Dir. Hidráulica  Vidro elétrico  Trava elétrica  4 portas  Air bag  ABS  5 pessoas  2 mala(s) grande(s)  1 mala(s) pequena(s)"));
        veiculos.add(new Veiculo(4, "Citroen", "AirCross Start 1.6", "disponible", "https://www.localiza.com/brasil-site/geral/Frota/AIRC.png", "M - SUV COMPACTO", 65.0f, "Características - Ar-condicionado  Dir. Hidráulica  Vidro elétrico  Trava elétrica  4 portas  Air bag  ABS  5 pessoas  2 mala(s) grande(s)  2 mala(s) pequena(s)"));
        veiculos.add(new Veiculo(5, "Pegeout", "Suv", "disponible", "https://www.localiza.com/brasil-site/geral/Frota/PEU2.png", "SUV COMPACTO AUTOMÁTICO", 70.0f, "Características: Ar-condicionado  Vidro elétrico  Trava elétrica  Air bag  Automático  ABS  Direção Elétrica  5 pessoas  2 mala(s) grande(s)  2 mala(s) pequena(s)"));
        veiculos.add(new Veiculo(6, "Corolla", "GLI", "disponible", "https://www.localiza.com/brasil-site/geral/Frota/CORO.png", "L-Executive", 80.0f, "Características: Ar-condicionado  Dir. Hidráulica  Automático  Vidro elétrico  Trava elétrica  4 portas  Air bag  ABS  5 pessoas  3 mala(s) grande(s)  2 mala(s) pequena(s)"));
        veiculos.add(new Veiculo(7, "Fiat", "Doblo", "disponible", "https://www.localiza.com/brasil-site/geral/Frota/DBLO.png", "R- MINIVAN", 60.0f, "Características: Ar-condicionado  Dir. Hidráulica  Vidro elétrico     Trava elétrica      3 portas     Air bag      ABS     7 pessoas"));
        veiculos.add(new Veiculo(8, "Chevrolet", "GM Spin 1.8 ", "disponible", "https://www.localiza.com/brasil-site/geral/Frota/SPIX.png", "RX - MINIVAN ESPECIAL", 70.0f, "Características: 4 portas  Ar-condicionado  Vidro elétrico  Trava elétrica  Dir. Hidráulica  Automático  7 pessoas  2 mala(s) grande(s)"));
        veiculos.add(new Veiculo(9, "Fiat", "Fiorino", "disponible", "https://www.localiza.com/brasil-site/geral/Frota/FIFO.png", "U-FURGÃO", 60.0f, "Características:   2 portas    2 pessoas    620 KG    Sem Ar-Condicionado Sem Vidro elétrico Sem Dir. Hidráulica      Trava Elétrica"));
        veiculos.add(new Veiculo(10, "Ford", "Ranger 2.2", "disponible", "https://www.localiza.com/brasil-site/geral/Frota/RANG.png", "P - 4x4 ESPECIAL", 90.0f, "Características: Ar-condicionado  Dir. Hidráulica  Cabine Dupla  Tração 4x4  4 portas Banco de Couro  Diesel  Air bag  ABS  5 pessoas  "));
        veiculos.add(new Veiculo(11, "Jaguar", " XF R-Sport", "disponible", "https://www.localiza.com/brasil-site/geral/Frota/JXFR.png", "SP - SUPER PRIME", 120.0f, "Características: 4 portas  Ar-condicionado  Vidro elétrico  Trava elétrica  Air bag  Automático  Banco de Couro  5 pessoas  3 mala(s) grande(s)  2 mala(s) pequena(s)"));
        veiculos.add(new Veiculo(12, "Audi", "A4 Sedan 2.0", "disponible", "https://www.localiza.com/brasil-site/geral/Frota/AUDL.png", "LP -PRIME", 110.0f, "Características :4 portas  Ar-condicionado  Vidro elétrico  Trava elétrica  Air bag  Automático  Banco de Couro  ABS  Direção Elétrica  5 pessoas  3 mala(s) grande(s)"));

        Consumer<Veiculo> consumerVeiculo = veiculo -> entityManager.persist(veiculo);
        veiculos.forEach(consumerVeiculo);

        List movies = new ArrayList<Movie>();
        movies.add(new Movie("Quentin Tarantino", "Reservoir Dogs", 1992));
        movies.add(new Movie("Joel Coen", "Fargo", 1996));
        movies.add(new Movie("Joel Coen", "The Big Lebowski", 1998));

        Consumer<Movie> consumerMovie = movie -> entityManager.persist(movie);
        movies.forEach(consumerMovie);

    }


    private Movie randomMovie(){
        Random rnd = new Random();
        String director = Thread.currentThread().getName();
        String title = LocalTime.now().toString();
        int year = rnd.nextInt(1900 + 2050);
        return new Movie(director, title, year);
    }

}
