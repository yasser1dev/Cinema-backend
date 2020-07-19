package com.bdcc.demo.web;


import com.bdcc.demo.dao.CinemaRepo;
import com.bdcc.demo.dao.PlaceRepo;
import com.bdcc.demo.dao.SalleRepo;
import com.bdcc.demo.entites.Place;
import com.bdcc.demo.entites.Salle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("salle_place")
public class SallePlaceController {
    @Autowired
    PlaceRepo placeRepo;
    @Autowired
    SalleRepo salleRepo;

    @Autowired
    CinemaRepo cinemaRepo;


    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("salle",new Salle());
        model.addAttribute("cinemas",cinemaRepo.findAll());
        return "formSalle";
    }

    @GetMapping("/deletePlace")
    public String deletePlace(Model model,@RequestParam(value = "idSalle",defaultValue = "") Long idSalle,@RequestParam(value = "idPlace",defaultValue = "") Long id){
        model.addAttribute("nbrPlace",salleRepo.findById(idSalle).get().getNbrPlace());
        placeRepo.deleteById(id);
        return "redirect:http://localhost:8080/salle_place/places?idSalle="+idSalle+"&mc=";
    }

    @GetMapping("/update")
    public String formUpdate(Model model,@RequestParam(value = "id") Long id){
        Salle salle=salleRepo.findById(id).get();
        model.addAttribute("salle",salle);
        model.addAttribute("cinemas",cinemaRepo.findAll());
        return "formSalle";
    }

    @PostMapping("/addSalle")
    public String valider(Model model, Salle salle){
        salleRepo.save(salle);
        return "redirect:http://localhost:8080/salle_place/all?mc="+salle.getName();
    }



    @GetMapping({"/","all"})
    public String getAll(Model model, @RequestParam(value = "mc",defaultValue = "") String mc){
        List<Salle> salleList=salleRepo.findByNameContains(mc);
        model.addAttribute("salles",salleList);
        model.addAttribute("mc",mc);
        model.addAttribute("showPlaces",false);

        return "salle_place";
    }

    @GetMapping("/delete")
    public String deleteSalle(@RequestParam(value = "mc",defaultValue = "") String mc,
                              @RequestParam(value = "idSalle",defaultValue = "") Long id){
        List<Place> listPlace=placeRepo.findBySalle_Id(id);
        for(Place p:listPlace){
            placeRepo.deleteById(p.getId());
        }
        salleRepo.deleteById(id);
        return "redirect:http://localhost:8080/salle_place/";
    }

    @GetMapping("/places")
    public String getPlaces(Model model, @RequestParam(value = "mc",defaultValue = "") String mc,
                          @RequestParam(value = "idSalle",defaultValue = "") Long id){
        List<Salle> salleList=salleRepo.findByNameContains(mc);
        model.addAttribute("salles",salleList);
        model.addAttribute("mc",mc);
        model.addAttribute("showPlaces",true);
        model.addAttribute("idSalle",id);
        model.addAttribute("nbrPlace",salleRepo.findById(id).get().getNbrPlace());
        List<Place> listPlace=placeRepo.findBySalle_Id(id);
        model.addAttribute("places",listPlace);

        return "salle_place";
    }

    @PostMapping("/addPlace")
    public String addPlace(Model model,Place place,@RequestParam(value = "idSalle",defaultValue = "") Long id){
        Salle salle=salleRepo.findById(id).get();
        model.addAttribute("nbrPlace",salleRepo.findById(id).get().getNbrPlace());
        place.setSalle(salle);
        placeRepo.save(place);
        salle.getPlaces().add(place);
        salleRepo.save(salle);
        return "redirect:http://localhost:8080/salle_place/places?idSalle="+id;
    }

    @GetMapping("formPlace")
    public String formPlace(Model model,@RequestParam(value = "idSalle",defaultValue = "") Long id){
        Place place=new Place();
        model.addAttribute("place",place);
        model.addAttribute("idSalle",id);
        return "formPlace";
    }






}

