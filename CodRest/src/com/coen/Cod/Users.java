package com.coen.Cod;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class Users {
    @GET
    @Path("{name}/{password}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage(@PathParam("name") String name, @PathParam("password") String password)
    {
        return "Hello " + name + " with password " + password;
    }

    @PUT
    @Path("{name}/{password}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessagePut(@PathParam("name") String name, @PathParam("password") String password)
    {
        return "Hello " + name + " with password " + password;
    }

    @DELETE
    @Path("{name}/{password}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessageDelete(@PathParam("name") String name, @PathParam("password") String password)
    {
        return "Hello " + name + " with password " + password;
    }
}
