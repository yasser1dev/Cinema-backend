package com.bdcc.demo.web;

import com.bdcc.demo.dao.FilmRepo;
import com.bdcc.demo.dao.TicketRepo;
import com.bdcc.demo.entites.Film;
import com.bdcc.demo.entites.Ticket;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class CinemaRestController {
    @Autowired
    FilmRepo filmRepo;
    @Autowired
    TicketRepo ticketRepo;

    @GetMapping(path = "/imageFilm/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getMovieImagie(@PathVariable(name = "id") Long id) throws IOException {
        Film film=filmRepo.findById(id).get();
        String imageName=film.getPhoto();
        File file=new File(System.getProperty("user.home")+"/cinema/images/"+imageName);
        Path path= Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

    @PostMapping("/payerTicket")
    @Transactional
    public List<Ticket> payerTicket(@RequestBody TicketForm ticketForm){
        List<Ticket> listTickets=new ArrayList<>();
        ticketForm.getTicketsIds().forEach(idTicket->{
            Ticket ticket=ticketRepo.findById(idTicket).get();
            ticket.setNomClient(ticketForm.getNomClient());
            ticket.setCodePayement(ticketForm.getCodePayement());
            ticket.setReserved(true);
            ticketRepo.save(ticket);
            listTickets.add(ticket);
        });
        return listTickets;
    }


}

@Data
class TicketForm{
    private String nomClient;
    private Integer codePayement;
    private List<Long> ticketsIds=new ArrayList<>();
}
