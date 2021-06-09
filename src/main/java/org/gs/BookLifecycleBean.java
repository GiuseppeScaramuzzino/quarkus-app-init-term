package org.gs;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class BookLifecycleBean {

  @Inject BookRepository repository;

  private Book book;

  private static final Logger LOGGER = Logger.getLogger(BookLifecycleBean.class);

  @Transactional
  void onStart(@Observes StartupEvent event) {
    LOGGER.info("The quarkus application is starting...");
    book = new Book();
    book.setTitle("FirstBook");
    book.setAuthor("Me");
    repository.persist(book);
    if (repository.isPersistent(book)) {
      LOGGER.info("The first book has been stored with id " + book.getId());
    } else {
      LOGGER.error("The first book has not been stored with id " + book.getId());
    }
  }

  @Transactional
  void onStop(@Observes ShutdownEvent event) {
    LOGGER.info("The quarkus application is stopping...");
    boolean deleted = repository.deleteById(book.getId());
    if (deleted) {
      LOGGER.info("The first book has been deleted");
    } else {
      LOGGER.error("The first book has not been deleted");
    }
  }
}
