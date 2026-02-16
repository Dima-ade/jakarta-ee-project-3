package org.example.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.example.exception.CustomBadRequestException;
import org.example.exception.MissingItemException;

import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;


@Path("/hello")
@Valid
@RequestScoped
@RolesAllowed({"USER"})
public class HelloResource {

    public HelloResource() {
        System.out.println("++++++++++++++++ HelloResource.HelloResource");
    }

    @GET
    @Produces(TEXT_PLAIN)
    public String getCallerAndRole() {
        return "Logged to application Jakarta EE";
    }

    @GET
    @Path("/world")
    @Produces(TEXT_PLAIN)
    public Response sayHello(@QueryParam("message") String message) throws MissingItemException, CustomBadRequestException {
        if (message == null || message.isBlank()) {
            System.out.println("In end point /hello/world the parameter is null or empty");
            throw new MissingItemException("The parameter is null or empty");
        } else if (message.length() > 3) {
            throw new CustomBadRequestException(String.format("The parameter value '%s' > 3 characters", message));
        } else {
            System.out.println("In end point /hello/world");
        }

        return Response.ok("Hello from Jakarta EE "+System.currentTimeMillis()+" message="+message).build();
    }
}
