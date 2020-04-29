package myserver;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        ServerConfig server = new ServerConfig();
        server.start();
//        Dao dao = new DaoSql();
//        int lol = 0;
//        while(lol < 10000){
//            Zakaz zakaz = new Zakaz("lalka", "gmail", "skrypin"+lol, "DELAI BLAD");
//            dao.add(zakaz);
//            System.out.println(lol);
//            lol++;
//        }
//        dao.delete();
//        List<Zakaz> zakazs = dao.readFromPromo("skrypin2700");
//        List<Zakaz> zakazs = dao.readAll();
//        zakazs.forEach(e -> {
//            System.out.println(e.name);
//        });
//        System.out.println(dao.count());
//        System.out.println(System.currentTimeMillis());

    }
}
