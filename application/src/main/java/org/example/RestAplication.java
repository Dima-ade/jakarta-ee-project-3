package org.example;

import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.example.beans.AllowedRoles;
import org.example.controller.BookResource;
import org.example.controller.ExceptionsResource;
import org.example.controller.HelloResource;
import org.example.controller.PersonResource;
import org.example.mapper.*;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;


@ApplicationPath("/api")
@ApplicationScoped
@BasicAuthenticationMechanismDefinition(realmName = "basicAuth")
@DeclareRoles({ "USER", "CALLER" })
public class RestAplication extends Application {
    private static final Logger logger = Logger.getLogger(RestAplication.class.getName());

    public RestAplication() {
        logger.info("\n\t=================== In RestAplication constructor");
    }

    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(HelloResource.class);
        s.add(PersonResource.class);
        s.add(ExceptionsResource.class);
        s.add(BookResource.class);

        s.add(AllowedRoles.class);

        s.add(MissingItemExceptionMapper.class);
        s.add(CustomBadRequestExceptionMapper.class);
        s.add(ConstraintViolationxceptionMapper.class);
        s.add(CustomValidationExceptionMapper.class);
        s.add(EJBExceptionMapper.class);
        s.add(EntityNotFoundMapper.class);
        s.add(GenericExceptionMapper.class);
        s.add(ItemNotFoundExceptionMapper.class);
        s.add(MissingRequestHeaderExceptionMapper.class);

        return s;
    }
}
