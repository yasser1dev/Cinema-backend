package com.bdcc.demo.dao;

import com.bdcc.demo.entites.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin("http://localhost:4200")

public interface FilmRepo extends JpaRepository<Film,Long> {

    public List<Film> findByTitreContains(String mc);
}
