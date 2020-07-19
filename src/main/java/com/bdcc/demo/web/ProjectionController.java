package com.bdcc.demo.web;

import com.bdcc.demo.dao.FilmRepo;
import com.bdcc.demo.dao.ProjectionRepo;
import com.bdcc.demo.dao.SalleRepo;
import com.bdcc.demo.dao.SeanceRepo;
import com.bdcc.demo.entites.Projection;
import com.bdcc.demo.entites.Salle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("ProjectionsManagement")
public class ProjectionController {
    @Autowired
    FilmRepo filmRepo;
    @Autowired
    SalleRepo salleRepo;
    @Autowired
    SeanceRepo seanceRepo;
    @Autowired
    ProjectionRepo projectionRepo;

    @GetMapping({"/","all"})
    public String getAll(Model model, @RequestParam(value = "mc",defaultValue = "") String mc){
        model.addAttribute("salles",salleRepo.findByCinema_NameContains(mc));
        model.addAttribute("mc",mc);
        return "ProjectionsManagement";
    }

    @GetMapping("/delete")
    public String delete(Model model,@RequestParam(value = "mc",defaultValue = "") String mc,@RequestParam(value = "id") Long id){
        model.addAttribute("salles",salleRepo.findByCinema_NameContains(mc));
        model.addAttribute("mc",mc);

        projectionRepo.deleteById(id);
        return "ProjectionsManagement";
    }

    @GetMapping("/form")
    public String getForm(Model model,@RequestParam(value = "idSalle") Long idSalle,
                          @RequestParam(value = "nomCine") String nomCine){
        Projection projection=new Projection();
        projection.setSalle(salleRepo.findById(idSalle).get());
        model.addAttribute("p",projection);
        model.addAttribute("nomCine",nomCine);
        model.addAttribute("films",filmRepo.findAll());
        model.addAttribute("seances",seanceRepo.findAll());
        model.addAttribute("salles",salleRepo.findByCinema_NameContains(nomCine));
        return "FormProjection";
    }

    @PostMapping("/add")
    public String add(Projection projection,@RequestParam(value = "nomCine") String nomCine){
        Salle salle=salleRepo.findById(projection.getSalle().getId()).get();
        salle.getProjections().add(projection);
        salleRepo.save(salle);
        projectionRepo.save(projection);

        return "redirect:http://localhost:8080/ProjectionsManagement/all?mc="+nomCine;
    }

}
