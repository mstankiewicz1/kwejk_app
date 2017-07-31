package pl.akademiakodu.kwejkapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import pl.akademiakodu.kwejkapp.model.Mem;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by itml on 25.06.2017.
 */
@Repository
public class MemDaoImpl implements MemDao {

    @Autowired
    private MemDaoCrud memDaoCrud;

    @Override
    public List<Mem> findAll() {
        Iterable<Mem> coll = memDaoCrud.findAllOrderByTimestampDesc();
        List<Mem> mems = new ArrayList<>();
        coll.forEach(mems::add);
        return mems;
    }

    @Override
    public Mem findById(Long id) {
        return memDaoCrud.findOne(id);
    }

    @Override
    public void update(Mem mem) {
        this.save(mem);
    }

    @Override
    public Mem findRandom() {
        List<Long> allIds = memDaoCrud.findAllIds();
        int listSize = allIds.size();
        Random random = new Random();
        int randomIndex = random.nextInt(listSize);

        if (randomIndex < 0) {
            return null;
        } else {
            long randomID = allIds.get(randomIndex);
            return findById(randomID);
        }
    }

    @Override
    public void save(Mem mem) {
        memDaoCrud.save(mem);
    }
}
