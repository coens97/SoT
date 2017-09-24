package com.coen.Cod;

import com.coen.Data.DataStore;
import com.coen.Dto.StandardResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class Users {
    @GET
    @Path("{name}/{password}")
    @Produces(MediaType.APPLICATION_XML)
    public StandardResult getMessage(@PathParam("name") String name, @PathParam("password") String password)
    {
        if (DataStore.getInstance().ValidUser(name, password))
        {
            return new StandardResult(true, "");
        }
        else
        {
            return new StandardResult(false, "Can't find username or password");
        }
    }

    @PUT
    @Path("{name}/{password}")
    @Produces(MediaType.APPLICATION_XML)
    public StandardResult getMessagePut(@PathParam("name") String name, @PathParam("password") String password)
    {
        if (DataStore.getInstance().ValidUser(name, password))
        {
            return new StandardResult(true, "");
        }
        else
        {
            return new StandardResult(false, "Username already exists");
        }
    }
}
