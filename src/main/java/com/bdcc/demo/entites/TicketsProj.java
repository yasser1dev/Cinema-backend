package com.bdcc.demo.entites;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "t",types = {Ticket.class})
public interface TicketsProj {
    public Long getId();
    public String getNomClient();
    public double getPrix();
    public Integer getCodePayement();
    public boolean getReserved();
    public Place getPlace();
}
