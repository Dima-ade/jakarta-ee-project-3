package org.example.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Arrays;
import java.util.logging.Logger;

@Provider
public class EJBExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger logger = Logger.getLogger(EJBExceptionMapper.class.getName());

    public EJBExceptionMapper() {
        System.out.println("\n\n\t\t==========================================================EJBExceptionMapper.EJBExceptionMapper\n\n");
    }

    public Response toResponse(Exception exception) {
        System.out.println("INTERNAL SERVER ERROR");
        logger.warning("INTERNAL SERVER ERROR " + Arrays.toString(exception.getStackTrace()) + " " + exception.getMessage());
        return Response.status(500).build();
    }
}