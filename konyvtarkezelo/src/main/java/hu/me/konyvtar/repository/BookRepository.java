package hu.me.konyvtar.repository;

import hu.me.konyvtar.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByLibraryId(Long libraryId);
}