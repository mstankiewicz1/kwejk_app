package pl.akademiakodu.kwejkapp.dao;

import org.springframework.stereotype.Repository;
import pl.akademiakodu.kwejkapp.model.Mem;

import javax.lang.model.type.ArrayType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by itml on 24.06.2017.
 */
@Repository
public class MemDaoStaticImpl implements MemDao {

    private static final Set<Mem> MEMS = new HashSet<>();

    static {
        Mem m1 = new Mem();
        m1.setId(1L);
        m1.setTitle("Cartoon");
        m1.setDescription("Cartoon description");
        m1.setTimestamp(LocalDateTime.now());
        m1.setVisitCount(0);
        m1.setImagePath("cartoon.gif");

        Mem m2 = new Mem();
        m2.setId(2L);
        m2.setTitle("Fun");
        m2.setDescription("Fun description");
        m2.setTimestamp(LocalDateTime.now());
        m2.setVisitCount(0);
        m2.setImagePath("fun.gif");

        Mem m3 = new Mem();
        m3.setId(3L);
        m3.setTitle("Dolphin");
        m3.setDescription("Dolphin description");
        m3.setTimestamp(LocalDateTime.now());
        m3.setVisitCount(0);
        m3.setImagePath("dolphin.gif");

        MEMS.add(m1);
        MEMS.add(m2);
        MEMS.add(m3);
    }

    @Override
    public List<Mem> findAll() {
        return new ArrayList<>(MEMS);
    }

    @Override
    public Mem findById(Long id) {
        for (Mem m : MEMS) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public void update(Mem mem) {
        MEMS.add(mem);
    }

    public Mem findRandom() {
        int maxId = MEMS.size();
        Random random = new Random();
        long randomId = random.nextInt(maxId) + 1;
        return findById(randomId);
    }

    @Override
    public void save(Mem mem) {
        long newId = MEMS.size() + 1;
        mem.setId(newId);
        MEMS.add(mem);
    }
}
