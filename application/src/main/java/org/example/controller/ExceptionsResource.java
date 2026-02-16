package org.example.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.exception.ItemNotFoundException;

import java.util.List;
import java.util.logging.Logger;

@Path("/exceptions")
@Valid
public class ExceptionsResource {

    private static final Logger logger = Logger.getLogger(ExceptionsResource.class.getName());

    @GET
    @Path("/city")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHelloCity(@QueryParam ("city")
                             @NotBlank String city) throws ItemNotFoundException {

        if (city == null || city.isBlank()) {
            throw new ItemNotFoundException("The city parameter is null or empty");
        }
        System.out.println("In end point /exceptions/city");
        logger.info("In end point /exceptions/city");

        return Response.ok("Hello from Jakarta EE - city").build();
    }

    @GET
    @Path("/clientId")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHelloHeader(@NotBlank(message = "The x-client-id header value is missing")
                           @HeaderParam("x-client-id")
                           String clientId){
        System.out.println("In end point /exceptions/clientId");
        logger.info("In end point /exceptions/clientId");

        return Response.ok("Hello from Jakarta EE - header").build();
    }

    @GET
    @Path("/weather")
    @Produces(MediaType.TEXT_PLAIN)
    public Response.Status sayHelloWeather(){
        System.out.println("In end point for exception - weather");

        List<String> weathers = null;
        if (weathers.isEmpty())
        {
            return Response.Status.NO_CONTENT;
        }

        return Response.Status.INTERNAL_SERVER_ERROR;
    }
}
