package pl.akademiakodu.kwejkapp.dao;

import pl.akademiakodu.kwejkapp.model.Mem;

import java.util.List;
import java.util.Set;

/**
 * Created by itml on 24.06.2017.
 */
public interface MemDao {
    List<Mem> findAll();
    Mem findById(Long id);
    void update(Mem mem);
    Mem findRandom();
    void save(Mem mem);
}
