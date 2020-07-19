package com.bdcc.demo.web;


import com.bdcc.demo.dao.CategorieRepo;
import com.bdcc.demo.dao.FilmRepo;
import com.bdcc.demo.entites.Categorie;
import com.bdcc.demo.entites.Cinema;
import com.bdcc.demo.entites.Film;
import com.bdcc.demo.entites.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/FilmManagement")
public class FilmController {
    @Autowired
    CategorieRepo categorieRepo;
    @Autowired
    FilmRepo filmRepo;
    String photoUpdate;

    @GetMapping({"/all","/"})
    public String getAll(Model model,@RequestParam(value = "mc",defaultValue = "") String mc){
        List<Film> filmList=filmRepo.findByTitreContains(mc);
        model.addAttribute("films",filmList);
        model.addAttribute("mc",mc);
        return "films";
    }

    @PostMapping("/add")
    public String addFilm(Model model, Film film){
        model.addAttribute("f",film);
        if(film.getPhoto().equals("")){
            film.setPhoto(photoUpdate);
            System.out.println("#####>"+film.getPhoto());

        }
        filmRepo.save(film);
        return "redirect:http://localhost:8080/FilmManagement/all?mc="+film.getTitre();
    }

    @GetMapping("/updateForm")
    public String updateForm(Model model,@RequestParam(value = "id") Long id){
        Film film=filmRepo.findById(id).get();
        List<Categorie> categories=categorieRepo.findAll();
        model.addAttribute("categories",categories);
        photoUpdate=film.getPhoto();
        model.addAttribute("f",film);
        return "formFilm";
    }

    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("f",new Film());
        List<Categorie> categories=categorieRepo.findAll();
        model.addAttribute("categories",categories);

        return "formFilm";
    }


    @GetMapping("/delete")
    public String delete(Long id,String mc){
        filmRepo.deleteById(id);
        return "redirect:http://localhost:8080/FilmManagement/all";
    }
}
