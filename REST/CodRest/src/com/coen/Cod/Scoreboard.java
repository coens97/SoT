package com.coen.Cod;

import com.coen.Data.DataStore;
import com.coen.Dto.ScoreBoardResult;
import com.coen.Dto.ScoreBoardResults;
import com.coen.Dto.StandardResult;
import com.coen.Dto.UserEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/scoreboard")
public class Scoreboard {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ScoreBoardResults getMessage()
    {
        /*ScoreBoardResult[] results = DataStore.getInstance()
                .getUserEntities().stream()
                .map(x ->
                        new ScoreBoardResult(x.getUsername(), x.getWins(), x.getLoss()))
                .toArray(ScoreBoardResult[]::new);
        return new ScoreBoardResults(results);*/
        return new ScoreBoardResults();
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public StandardResult getMessageDelete(@FormParam("name") String name, @FormParam("password") String password)
    {
        Optional<UserEntity> maybeuser = DataStore.getInstance().MaybeUser(name, password);
        if (maybeuser.isPresent())
        {
            UserEntity user = maybeuser.get();
            user.setLoss(0);
            user.setWins(0);
            return new StandardResult(true, "");
        }
        return new StandardResult(false, "Authentication failed");
    }

}
