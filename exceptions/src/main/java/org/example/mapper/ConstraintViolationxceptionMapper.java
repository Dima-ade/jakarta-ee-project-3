package org.example.mapper;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.logging.Logger;

@Provider
public class ConstraintViolationxceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    private static final Logger logger = Logger.getLogger(ConstraintViolationxceptionMapper.class.getName());

    public ConstraintViolationxceptionMapper() {
        System.out.println("\n\n\t\t==========================================================ConstraintViolationxceptionMapper.ConstraintViolationxceptionMapper\n\n");
    }

    public Response toResponse(ConstraintViolationException exception) {
        System.out.println("CONSTRAINT VIOLATION EXCEPTION");
        logger.warning("CONSTRAINT VIOLATION EXCEPTION");
        return Response.status(400).build();
    }
}