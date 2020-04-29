package myserver.servlets;

import com.google.gson.Gson;
import myserver.login.LoginSessionGen;
import myserver.login.LoginSessionInmemoryDao;
import myserver.login.SessionId;
import myserver.login.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.stream.Collectors;

public class LoginServlet extends HttpServlet {
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        File file = new File(getClass().getClassLoader().getResource("Login.html").getFile());
        try(BufferedReader out = new BufferedReader(new FileReader(file))){
            String sCurrentLine;
            while ((sCurrentLine = out.readLine()) != null) {
                resp.getWriter().println(sCurrentLine);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String reqData = req.getReader()
                .lines()
                .collect(Collectors.joining());
        User user = gson.fromJson(reqData, User.class);
        if (user.getName().equals("admin") & user.getPassword().equals("12346")){
            String session = LoginSessionGen.createSessionId();
            LoginSessionInmemoryDao.activeHash.add(session);
            out.print(session);
            out.flush();
        } else {
            String session = "0";
            out.print(session);
            out.flush();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String reqData = req.getReader()
                .lines()
                .collect(Collectors.joining());
        SessionId sessionId = gson.fromJson(reqData, SessionId.class);
        for(int i = 0; i < LoginSessionInmemoryDao.activeHash.size(); i++){
            if(sessionId.getSessionId().equals(LoginSessionInmemoryDao.activeHash.get(i))){
                LoginSessionInmemoryDao.activeHash.remove(i);
                break;
            }
        }
        out.print(reqData);
        out.flush();
    }
}
