package com.coen.Cod;

import com.coen.Data.DataStore;
import com.coen.Dto.ScoreBoardResult;
import com.coen.Dto.StandardResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/scoreboard")
public class Scoreboard {
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ScoreBoardResult[] getMessage()
    {
        return DataStore.getInstance()
                .getUserEntities().stream()
                .map(x ->
                        new ScoreBoardResult(x.getUsername(), x.getWins(), x.getLoss()))
                .toArray(ScoreBoardResult[]::new);
    }

}
