package org.gs;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/books")
public class BookResource {

    @Inject
    BookRepository repository;

    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        List<Book> books = repository.listAll();
        return Response.ok(books).build();
    }
}