package myserver.servlets;

import com.google.gson.Gson;
import myserver.zakazDao.Dao;
import myserver.zakazDao.DaoZakazs;
import myserver.zakazDao.Zakaz;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ZakazServlet extends HttpServlet {
    private Dao dao = new DaoZakazs();
    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("был получен список заказов");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        List<Zakaz> zakazs = dao.readAll();
        String employeeJsonString = this.gson.toJson(zakazs);
        out.print(employeeJsonString);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDateTime logDateTime = new Timestamp(System.currentTimeMillis()).toLocalDateTime();
        String time = logDateTime.getDayOfMonth()+"/"+
                logDateTime.getMonthValue()+"/"+
                logDateTime.getYear()+" "+
                logDateTime.getHour()+":"+
                logDateTime.getMinute()+" "+
                logDateTime.getSecond();

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String reqData = req.getReader()
                .lines()
                .collect(Collectors.joining());
        System.out.println("пришел json: "+reqData+" в "+time);
        Zakaz zakaz = gson.fromJson(reqData, Zakaz.class);
        zakaz.setTime(time);
        System.out.println("сгенерировался обьект: "+zakaz.toString());
        dao.add(zakaz);
        out.print(reqData);
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        String reqData = req.getReader()
                .lines()
                .collect(Collectors.joining());
        System.out.println("пришел запрос на удаление:"+reqData+"вот");

        if(reqData.equals("\"deleteall\"")){
            boolean delete = dao.delete();
            PrintWriter out = resp.getWriter();
            System.out.println("БД была очищена");
            out.print(gson.toJson("{\"delete\":\""+delete+"\"}"));
            out.flush();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        String reqData = req.getReader()
                .lines()
                .collect(Collectors.joining());
        System.out.println("пришел id на удаление: "+reqData);
        Zakaz zakaz = gson.fromJson(reqData, Zakaz.class);
        System.out.println(zakaz.toString());
        dao.deleteById(zakaz.getId());

        PrintWriter out = resp.getWriter();
        out.print(gson.toJson("{\"delete\":\""+zakaz.getId()+"\"}"));
        out.flush();
    }
}
