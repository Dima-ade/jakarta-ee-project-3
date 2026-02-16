package org.example.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.example.exception.MissingItemException;

//@Provider
public class MissingItemExceptionMapper implements ExceptionMapper<MissingItemException> {


    public MissingItemExceptionMapper() {
        System.out.println("\n\n\t\t====================== constructor =>"+getClass().getName()+"\n\n");
    }

    public Response toResponse(MissingItemException exception) {
        Response response = Response.status(Response.Status.NOT_FOUND.getStatusCode(), exception.getMessage()).build();
        System.out.println(getClass().getName()+" before return");
        return response;
    }
}