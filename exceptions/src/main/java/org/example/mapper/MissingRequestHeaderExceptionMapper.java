package org.example.mapper;

import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.logging.Logger;

@Provider
public class MissingRequestHeaderExceptionMapper implements ExceptionMapper<NotAuthorizedException> {

    private static final Logger logger = Logger.getLogger(MissingRequestHeaderExceptionMapper.class.getName());

    public MissingRequestHeaderExceptionMapper() {
        System.out.println("\n\n\t\t==========================================================MissingRequestHeaderExceptionMapper.MissingRequestHeaderExceptionMapper\n\n");
    }

    public Response toResponse(NotAuthorizedException exception) {
        System.out.println("MISSING REQUEST HEADER EXCEPTION");
        logger.warning("MISSING REQUEST HEADER EXCEPTION");
        return Response.status(400).build();
    }
}