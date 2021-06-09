package org.gs;

import io.quarkus.runtime.Startup;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Optional;

@Startup
public class BookStartUp {

  private static final Logger LOGGER = Logger.getLogger(BookStartUp.class);

  @Inject BookRepository repository;

  @PostConstruct
  public void init() {
    Optional<Book> book = repository.find("title", "FirstBook").singleResultOptional();
    if (book.isPresent()) {
      LOGGER.info(
          "The first book with title "
              + book.get().getTitle()
              + " and "
              + book.get().getId()
              + " has been found");
    } else {
      LOGGER.error("The first has not been found");
    }
  }
}
