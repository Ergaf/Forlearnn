package myserver.zakazDao;

import java.util.List;

public interface Dao {
    boolean add(Zakaz zakaz);
    List<Zakaz> readAll();
    List<Zakaz> readFromPromo(String promoF);
    int count();
    boolean update(Zakaz current, Zakaz candidate);
    boolean delete();
    public boolean idForNull();
    boolean deleteById(int id);
}
