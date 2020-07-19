package com.bdcc.demo.dao;

import com.bdcc.demo.entites.Film;
import com.bdcc.demo.entites.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin("http://localhost:4200")

public interface SalleRepo extends JpaRepository<Salle,Long> {
    public List<Salle> findByNameContains(String mc);
    public List<Salle> findByCinema_NameContains(String mc);

}
