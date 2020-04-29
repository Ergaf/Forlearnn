package myserver.zakazDao;

public class Zakaz {
    private int id;
    private String name;
    private String mail;
    private String promo;
    private String zakazstr;
    private String time;
    private double price;
    private int prosm;

    public Zakaz(int id) {
        this.id = id;
    }

    public Zakaz(String name, String mail, String promo, String zakazstr) {
        this.name = name;
        this.mail = mail;
        this.promo = promo;
        this.zakazstr = zakazstr;
    }

    public Zakaz(int id, String name, String mail, String promo, String zakazstr) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.promo = promo;
        this.zakazstr = zakazstr;
    }

    public Zakaz(int id, String name, String mail, String promo, String zakazstr, String time) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.promo = promo;
        this.zakazstr = zakazstr;
        this.time = time;
    }

    public Zakaz(int id, String name, String mail, String promo, String zakazstr, String time, double price, int prosm) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.promo = promo;
        this.zakazstr = zakazstr;
        this.time = time;
        this.price = price;
        this.prosm = prosm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String getZakazstr() {
        return zakazstr;
    }

    public void setZakazstr(String zakazstr) {
        this.zakazstr = zakazstr;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProsm() {
        return prosm;
    }

    public void setProsm(int prosm) {
        this.prosm = prosm;
    }

    @Override
    public String toString() {
        return "Zakaz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", promo='" + promo + '\'' +
                ", zakazstr='" + zakazstr + '\'' +
                ", time='" + time + '\'' +
                ", price=" + price +
                ", prosm=" + prosm +
                '}';
    }
}
