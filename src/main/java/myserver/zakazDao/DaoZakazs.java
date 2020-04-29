package myserver.zakazDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoZakazs implements Dao {

    public boolean add(Zakaz zakaz) {
        try(Connection con = getConnection()){
            String sql = "INSERT INTO printpeaksdata.orders (name, mail, promo, zakazstr, time) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, zakaz.getName());
            ps.setString(2, zakaz.getMail());
            ps.setString(3, zakaz.getPromo());
            ps.setString(4, zakaz.getZakazstr());
            ps.setString(5, zakaz.getTime());
            return ps.execute();

        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public List<Zakaz> readAll() {
        List<Zakaz> zakazs = new ArrayList<>();
        try(Connection con = getConnection()){
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM printpeaksdata.orders";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String mail = rs.getString("mail");
                String promo = rs.getString("promo");
                String zakazstr = rs.getString("zakazstr");
                String time = rs.getString("time");
                double price = rs.getDouble("price");
                int prosm = rs.getInt("prosm");
                Zakaz zakaz = new Zakaz(id, name, mail, promo, zakazstr, time, price, prosm);
                zakazs.add(zakaz);
            }
            return zakazs;
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public List<Zakaz> readFromPromo(String promoF){
        List<Zakaz> zakazs = new ArrayList<>();
        try(Connection con = getConnection()){
            String sql = "SELECT * FROM printpeaksdata.orders WHERE promo=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, promoF);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String mail = rs.getString("mail");
                String promo = rs.getString("promo");
                String zakazstr = rs.getString("zakazstr");
                String time = rs.getString("time");
                double price = rs.getDouble("price");
                int prosm = rs.getInt("prosm");
                Zakaz zakaz = new Zakaz(id, name, mail, promo, zakazstr, time, price, prosm);
                zakazs.add(zakaz);
            }
            return zakazs;
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public int count(){
        try(Connection con = getConnection()){
            String sql = "SELECT count(*) FROM printpeaksdata.orders";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("count(*)");
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public boolean update(Zakaz current, Zakaz candidate){
        try(Connection con = getConnection()){
            String sql = "UPDATE printpeaksdata.orders SET name=?, mail=?, promo=?, zakazstr=?, time=?, price=?, prosm=? where id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, candidate.getName());
            ps.setObject(2, candidate.getMail());
            ps.setObject(3, candidate.getPromo());
            ps.setObject(4, candidate.getZakazstr());
            ps.setObject(5, candidate.getTime());
            ps.setObject(6, candidate.getPrice());
            ps.setObject(7, candidate.getProsm());
            ps.setObject(8, current.getId());
            return ps.execute();

        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public boolean delete(){
        try(Connection con = getConnection()){
            String sql = "DELETE from printpeaksdata.orders";
            PreparedStatement ps = con.prepareStatement(sql);
            return ps.execute();

        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public boolean idForNull(){
        try(Connection con = getConnection()){
            Statement statement = con.createStatement();
            String sql = "ALTER TABLE printpeaksdata.orders AUTO_INCREMENT=0";
            return statement.execute(sql);

        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public boolean deleteById(int id){
        try(Connection con = getConnection()){
            String sql = "DELETE from printpeaksdata.orders where id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            return ps.execute();

        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }



    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/?useSSL=false", "root", "horus333777");
    }
}
