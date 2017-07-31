package pl.akademiakodu.kwejkapp.service;

import pl.akademiakodu.kwejkapp.model.Mem;

import java.util.List;

/**
 * Created by itml on 24.06.2017.
 */
public interface MemService {
    List<Mem> getList();

    Mem findById(Long id) throws MemNotFoundException;

    Mem findRandom() throws MemNotFoundException;

    void save(Mem mem);
}
