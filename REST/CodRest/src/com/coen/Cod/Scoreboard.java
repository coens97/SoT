package com.coen.Cod;

import com.coen.Data.DataStore;
import com.coen.Dto.ScoreBoardResult;
import com.coen.Dto.ScoreBoardResults;
import com.coen.Dto.StandardResult;
import com.coen.Dto.UserEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.stream.Stream;

@Path("/scoreboard")
public class Scoreboard {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ScoreBoardResults getMessage()
    {
        return DataStore.getInstance().GetScoreboard();
    }

    @DELETE
    @Path("{name}/{password}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public StandardResult getMessageDelete(@PathParam("name") String name, @PathParam("password") String password)
    {
        Optional<UserEntity> maybeuser = DataStore.getInstance().MaybeUser(name, password);
        if (maybeuser.isPresent())
        {
            UserEntity user = maybeuser.get();
            user.setLoss(0);
            user.setWins(0);
            return new StandardResult(true, "Successful reset score");
        }
        return new StandardResult(false, "Authentication failed");
    }

}
