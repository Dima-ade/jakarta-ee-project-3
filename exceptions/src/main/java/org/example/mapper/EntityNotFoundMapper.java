package org.example.mapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.util.logging.Logger;

@Provider
public class EntityNotFoundMapper {

    public EntityNotFoundMapper() {
        System.out.println("\n\n\t\t************************************************************EntityNotFoundMapper.EntityNotFoundMapper\n\n");
    }

    private static final Logger logger = Logger.getLogger(EntityNotFoundMapper.class.getName());
    public Response toResponse(EntityNotFoundException e) {
        logger.warning("NOT FOUND");
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
