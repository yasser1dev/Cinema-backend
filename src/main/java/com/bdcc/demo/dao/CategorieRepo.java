package com.bdcc.demo.dao;

import com.bdcc.demo.entites.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategorieRepo extends JpaRepository<Categorie,Long> {
}
