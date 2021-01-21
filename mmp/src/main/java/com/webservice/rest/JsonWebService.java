package com.webservice.rest;

import static com.gruppo12.getList.GetJson;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/plget")
public class JsonWebService {

    @Path("{f}/{g}")
    @GET
    @Produces("application/json")
    public Response GetMyJson(@PathParam("f") String id, @PathParam("g") String id_song) {
 

        String result=GetJson(id,id_song);
        return Response.status(200).entity(result).build();
    }
}
