package org.example.controller;

import java.net.URI;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.example.entities.Book;

@Path("/books")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    //@PersistenceContext(unitName="bookstore-PU")
    @PersistenceUnit(name="bookstore-PU")
    EntityManagerFactory entityManagerFactory;

    @Context
    UriInfo uriInfo;

    public BookResource() {
    }

    @GET
    public Response getAll() {
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Book> all = em.createQuery("SELECT b FROM Book b", Book.class)
                .getResultList();

        return Response.ok()
                .entity(all)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Book book = em.find(Book.class, id);
        if (book == null) {
            throw new NotFoundException("Book with id " + id + " not found");
        }

        return Response.ok()
                .entity(book)
                .build();
    }

    @POST
    @Transactional
    public Response create(@Valid Book book) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.persist(book);


        final URI location = uriInfo.getBaseUriBuilder()
                .path(BookResource.class)
                .path(book.getId().toString())
                .build();

        return Response.created(location)
                .entity(book)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, @Valid Book book) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Book existing = em.find(Book.class, id);
        if (existing == null) {
            throw new NotFoundException("Book with id " + id + " not found");
        }
        existing.setAuthor(book.getAuthor());
        existing.setTitle(book.getTitle());
        existing.setIsbn(book.getIsbn());
        existing.setPrice(book.getPrice());

        return Response.ok()
                .entity(existing)
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Book book = em.find(Book.class, id);
        if (book == null) {
            throw new NotFoundException("Book with id " + id + " not found");
        }
        em.remove(book);

        return Response.noContent()
                .build();
    }
}