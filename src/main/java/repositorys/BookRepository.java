package repositorys;

import entity.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BookRepository extends CrudRepository<BookEntity, Integer>{
    BookEntity findOne(int i);

    void delete(int i);
}
