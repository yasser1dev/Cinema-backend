package com.bdcc.demo.dao;

import com.bdcc.demo.entites.Cinema;
import com.bdcc.demo.entites.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin("http://localhost:4200")
public interface CinemaRepo extends JpaRepository<Cinema,Long> {
    public List<Cinema> findByNameContains(String mc);

}
