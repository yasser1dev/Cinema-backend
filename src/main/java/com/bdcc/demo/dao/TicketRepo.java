package com.bdcc.demo.dao;

import com.bdcc.demo.entites.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("http://localhost:4200")

public interface TicketRepo extends JpaRepository<Ticket,Long> {
}