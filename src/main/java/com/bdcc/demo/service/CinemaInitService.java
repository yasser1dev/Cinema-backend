package com.bdcc.demo.service;

import com.bdcc.demo.dao.*;
import com.bdcc.demo.entites.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaInitService implements ICinemaInitService {

    @Autowired
    VilleRepo villeRepo;
    @Autowired
    CinemaRepo cinemaRepo;
    @Autowired
    SalleRepo salleRepo;
    @Autowired
    PlaceRepo placeRepo;
    @Autowired
    SeanceRepo seanceRepo;
    @Autowired
    CategorieRepo categorieRepo;
    @Autowired
    FilmRepo filmRepo;
    @Autowired
    ProjectionRepo projectionRepo;
    @Autowired
    TicketRepo ticketRepo;
    @Override
    public void initVilles() {
        Stream.of("Casablanca","Rabat","Tanger","Marakersh").forEach(nameVille->{
            Ville ville=new Ville();
            ville.setName(nameVille);
            villeRepo.save(ville);
        });
    }

    @Override
    public void initCinemas() {
        villeRepo.findAll().forEach(ville ->{
            Stream.of("Mega","Imax","Rif","lynx").forEach(nameCine->{
                Cinema cinema=new Cinema();
                cinema.setName(nameCine);
                cinema.setVille(ville);
                cinema.setNbrSalle(3+(int)(Math.random()*7));
                cinemaRepo.save(cinema);
            });
        });
    }

    @Override
    public void initSalles() {
        cinemaRepo.findAll().forEach(cinema -> {
            for(int i=0;i<cinema.getNbrSalle();i++){
                Salle salle=new Salle();
                salle.setName("Salle"+(i+1));
                salle.setCinema(cinema);
                salle.setNbrPlace(15+(int)(Math.random()*20));
                salleRepo.save(salle);
            }
        });
    }

    @Override
    public void initPlaces() {
        salleRepo.findAll().forEach(salle -> {
            for(int i=0;i<salle.getNbrPlace();i++){
                Place place=new Place();
                place.setSalle(salle);
                place.setNumPlace(i+1);
                placeRepo.save(place);
            }
        });
    }

    @Override
    public void initSeances() {
        DateFormat dateFormat=new SimpleDateFormat("HH:mm");
        Stream.of("12:00","16:00","18:30","20:00").forEach(h->{
            Seance seance=new Seance();
            try {
                seance.setHeureDebut(dateFormat.parse(h));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            seanceRepo.save(seance);
        });
    }

    @Override
    public void initCategories() {
        Stream.of("Actions","Drama","Fiction","Histoire").forEach(nameCat->{
            Categorie categorie=new Categorie();
            categorie.setName(nameCat);
            categorieRepo.save(categorie);

        });
    }

    @Override
    public void initFilms() {
        double[] duree=new double[]{1.5,2,1.30,1.45};
        List<Categorie>  categorieList=categorieRepo.findAll();
        Stream.of("avengers","fury","interstellar","madmax").forEach(filmName->{
            Film film=new Film();
            film.setTitre(filmName);
            film.setDuree(duree[new Random().nextInt(duree.length)]);
            film.setCategorie(categorieList.get(new Random().nextInt(categorieList.size())));
            film.setPhoto(filmName+".jpg");
            filmRepo.save(film);
        });
    }

    @Override
    public void initProjections() {
        double[] prix=new double[]{60,120,50,40};
        List<Film> listFilm=filmRepo.findAll();
        villeRepo.findAll().forEach(ville -> {
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle -> {
                    int index=new Random().nextInt(listFilm.size());
                    seanceRepo.findAll().forEach(seance -> {
                        Projection projection=new Projection();
                        projection.setDateProjection(new Date());
                        projection.setFilm(listFilm.get(index));
                        projection.setSalle(salle);
                        projection.setSeance(seance);
                        projection.setPrix(prix[new Random().nextInt(prix.length)]);
                        projectionRepo.save(projection);
                    });

                });
            });
        });
    }

    @Override
    public void initTickets() {
        projectionRepo.findAll().forEach(projection -> {
            projection.getSalle().getPlaces().forEach(place -> {
                Ticket ticket=new Ticket();
                ticket.setPlace(place);
                ticket.setProjection(projection);
                ticket.setPrix(projection.getPrix());
                ticket.setReserved(false);
                ticketRepo.save(ticket);
            });
        });
    }
}
