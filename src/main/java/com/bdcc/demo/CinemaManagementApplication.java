package com.bdcc.demo;

import com.bdcc.demo.entites.Film;
import com.bdcc.demo.entites.Salle;
import com.bdcc.demo.entites.Ticket;
import com.bdcc.demo.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CinemaManagementApplication implements CommandLineRunner {
    @Autowired
    private ICinemaInitService iCinemaInitService;
    @Autowired
    private RepositoryRestConfiguration restConf;
    public static void main(String[] args) {
        SpringApplication.run(CinemaManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        restConf.exposeIdsFor(Film.class, Salle.class, Ticket.class);

        /*iCinemaInitService.initVilles();
        iCinemaInitService.initCinemas();
        iCinemaInitService.initSalles();
        iCinemaInitService.initPlaces();
        iCinemaInitService.initSeances();
        iCinemaInitService.initCategories();
        iCinemaInitService.initFilms();
        iCinemaInitService.initProjections();
        iCinemaInitService.initTickets();

*/





    }
}
