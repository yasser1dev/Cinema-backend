package com.bdcc.demo.web;

import com.bdcc.demo.dao.CinemaRepo;
import com.bdcc.demo.dao.SalleRepo;
import com.bdcc.demo.dao.VilleRepo;
import com.bdcc.demo.entites.Cinema;
import com.bdcc.demo.entites.Salle;
import com.bdcc.demo.entites.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("CinemaManagement")
public class CineController {
    @Autowired
    CinemaRepo cinemaRepo;
    @Autowired
    SalleRepo salleRepo;
    @Autowired
    VilleRepo villeRepo;

    @GetMapping({"/all","/"})
    public String getAllCinema(Model model,@RequestParam(value = "mc",defaultValue = "") String mc){
        List<Cinema> cinemas=cinemaRepo.findByNameContains(mc);

        model.addAttribute("cinemas",cinemas);
        model.addAttribute("mc",mc);
        return "cinema";
    }



    @GetMapping("/delete")
    public String deleteCinema(Long id,String mc){
        for(Salle s:cinemaRepo.getOne(id).getSalles()){
            salleRepo.deleteById(s.getId());
        }
        cinemaRepo.deleteById(id);
        return "redirect:http://localhost:8080/CinemaManagement/all";
    }

    @PostMapping("/add")
    public String addCine(Model model,Cinema cinema){
        model.addAttribute("c",cinema);
        cinemaRepo.save(cinema);
        return "redirect:http://localhost:8080/CinemaManagement/all";
    }

    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("c",new Cinema());
        List<Ville> listVille=villeRepo.findAll().subList(0,4);
        model.addAttribute("villes",listVille);
        return "addCinema";
    }



    @GetMapping("/updateForm")
    public String updateForm(Model model,@RequestParam(value = "id") Long id){
        Cinema cinema=cinemaRepo.findById(id).get();
        //System.out.println(cinema.getVille().getName());
        List<Ville> listVille=villeRepo.findAll();
        model.addAttribute("villes",listVille);
        model.addAttribute("c",cinema);
        return "addCinema";
    }



}
