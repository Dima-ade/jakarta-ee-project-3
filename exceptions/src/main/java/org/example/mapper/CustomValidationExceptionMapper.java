package org.example.mapper;

import jakarta.validation.ValidationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.logging.Logger;

@Provider
public class CustomValidationExceptionMapper implements ExceptionMapper<ValidationException>{

    private static final Logger logger = Logger.getLogger(CustomValidationExceptionMapper.class.getName());

    public CustomValidationExceptionMapper() {
        System.out.println("\n\n\t\t==========================================================CustomValidationExceptionMapper.CustomValidationExceptionMapper\n\n");
    }

    public Response toResponse(ValidationException exception) {
        System.out.println("VALIDATION EXCEPTION");
        logger.warning("VALIDATION EXCEPTION : "+ exception.getMessage());
        return Response.status(400).build();
    }
}