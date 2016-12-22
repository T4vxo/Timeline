/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.teknikum.services;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import se.teknikum.beans.EventManager;

/**
 *
 * @author DanLun2
 */
@Path("/")
public class EventService {

    @EJB
    EventManager eventManager;

    @GET
    @Path("events")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEvents() {
        return Response.ok(eventManager.getEvents()).build();
    }

    @GET
    @Path("events/decade/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEvents(@PathParam("id") int id) {
        return Response.ok(eventManager.getEventFromDecade(id)).build();
    }

    @GET
    @Path("events/decades/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDecades() {
        return Response.ok(eventManager.getDecades()).build();
    }
}
