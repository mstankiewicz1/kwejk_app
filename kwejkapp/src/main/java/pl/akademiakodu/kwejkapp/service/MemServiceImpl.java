package pl.akademiakodu.kwejkapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.akademiakodu.kwejkapp.dao.MemDao;
import pl.akademiakodu.kwejkapp.model.Mem;

import java.util.List;

/**
 * Created by itml on 24.06.2017.
 */
@Service
public class MemServiceImpl implements MemService {

    @Autowired
    private MemDao memDaoImpl;

    @Override
    public List<Mem> getList() {
        return memDaoImpl.findAll();
    }

    public Mem findById(Long id) throws MemNotFoundException {
        Mem mem = memDaoImpl.findById(id);
        return getMemAndUpdate(mem);
    }

    @Override
    public Mem findRandom() throws MemNotFoundException {
        Mem mem = memDaoImpl.findRandom();
        return getMemAndUpdate(mem);
    }

    @Override
    public void save(Mem mem) {
        memDaoImpl.save(mem);
    }

    private Mem getMemAndUpdate(Mem mem) throws MemNotFoundException {
        if (mem == null) {
            throw new MemNotFoundException();
        } else {
            mem.increaseVisitCount();
            memDaoImpl.update(mem);
        }
        return mem;
    }
}