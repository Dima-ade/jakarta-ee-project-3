package org.example.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.example.exception.CustomBadRequestException;


//@Provider
public class CustomBadRequestExceptionMapper implements ExceptionMapper<CustomBadRequestException> {


    public CustomBadRequestExceptionMapper() {
        System.out.println("\n\n\t\t====================== constructor =>"+getClass().getName()+"\n\n");
    }

    public Response toResponse(CustomBadRequestException
                                       exception) {
        Response response = Response.status(Response.Status.BAD_REQUEST.getStatusCode(), exception.getMessage())
                //.header("Content-Type", "application/json")
                .build();
        System.out.println(getClass().getName()+" before return");
        return response;
    }
}