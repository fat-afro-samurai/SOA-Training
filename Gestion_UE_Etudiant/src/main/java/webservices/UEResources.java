package webservices;
import com.sun.jdi.Value;
import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.swing.text.html.parser.Entity;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ue")
public class UEResources {
    UniteEnseignementBusiness helper = new UniteEnseignementBusiness();
    @Path("/liste")
    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public  Response liste()
    {
        return Response.status(200).entity(
                helper.getListeUE()
        ).build();
    }
    @Path("/ajout")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response ajout(UniteEnseignement ue)
    {
        if (helper.addUniteEnseignement(ue))
        {
            return  Response.status(201).entity("added succesfully").build();
        }else { return  Response.status(409).entity("already exist").build();
        }
    }
    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello() {
        return Response
                .status(200)
                .entity("hello skander")
                .build();
    }

    @Path("/update/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response update(@PathParam("id") int code, UniteEnseignement ue) {
        if (helper.updateUniteEnseignement(code, ue)) {
            return Response.status(200).entity("updated successfully").build();
        } else {
            return Response.status(404).entity("not found").build();
        }
    }


    @Path("/delete/{id}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("id") int code) {
        if (this.helper.deleteUniteEnseignement(code)) {
            return Response.status(200).entity("delete successfully").build();
        } else {
            return Response.status(404).entity("not found").build();
        }
    }


    @Path("/search")
    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public  Response search(@QueryParam(value = "sem") int semestre ){
        return Response.status(200).entity(this.helper.getUEBySemestre(semestre)).build();
    }

}