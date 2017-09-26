package com.coen.Cod;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/teapot")
public class Teapot {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMessage() {
        return Response.status(418).entity(" I'm a teapot. \nThe requested entity body is short and stout. Tip me over and pour me out.").build();
    }
}