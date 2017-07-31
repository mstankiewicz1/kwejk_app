package pl.akademiakodu.kwejkapp.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.akademiakodu.kwejkapp.model.Mem;

import java.util.List;

/**
 * Created by itml on 25.06.2017.
 */
@Repository
public interface MemDaoCrud extends CrudRepository<Mem, Long> {

    @Query("SELECT m.id FROM Mem m")
    List<Long> findAllIds();

    @Query("SELECT m FROM Mem m ORDER BY m.timestamp DESC")
    List<Mem> findAllOrderByTimestampDesc();

}
