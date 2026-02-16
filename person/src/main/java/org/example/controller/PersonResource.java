package org.example.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.commons.IPersonResource;

import java.util.logging.Logger;

@Path("/person")
@Valid
@RequestScoped
public class PersonResource implements IPersonResource {

    private static final Logger logger = Logger.getLogger(PersonResource.class.getName());

    public PersonResource() {
        System.out.println("\n\t\t++++++++++++++++++++++++++++++++ PersonResource.PersonResource");
    }

    @GET
    @Path("/bonjour")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayBonjourPerson(){
        System.out.println("In end point /person/bonjour");
        logger.info("In end point /person/bonjour");

        return Response.ok("Bonjour from Jakarta EE!!!").build();
    }
}
