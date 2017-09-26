package com.coen.Cod;

import com.coen.Data.DataStore;
import com.coen.Dto.StandardResult;
import com.coen.Dto.UserEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/game")
public class Game {
    @PUT
    @Path("roll")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public StandardResult getMessage(@FormParam("name") String name, @FormParam("password") String password)
    {
        Optional<UserEntity> maybeuser = DataStore.getInstance().MaybeUser(name, password);
        if (maybeuser.isPresent())
        {
            UserEntity user = maybeuser.get();
            boolean random = Math.random() < 0.5;
            if (random)
            {
                user.increaseLoss();
                return new StandardResult(false, "");
            }
            else
            {
                user.increaseWin();
                return new StandardResult(true, "");
            }
        }
        return new StandardResult(false, "Authentication failed");
    }
}
