package myserver.servlets;

import myserver.login.LoginSessionInmemoryDao;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        File file = new File(getClass().getClassLoader().getResource("Login.html").getFile());
        Cookie[] coocies = req.getCookies();
        if(coocies != null){
            for(int i = 0; i < coocies.length; i++){
                if(LoginSessionInmemoryDao.isTrue(coocies[i].getValue())){
                    System.out.println("Зашли под админом");
                    file = new File(getClass().getClassLoader().getResource("adminOrdersList.html").getFile());
                }
            }
        }

        try(BufferedReader out = new BufferedReader(new FileReader(file))){
            String sCurrentLine;
            while ((sCurrentLine = out.readLine()) != null) {
                resp.getWriter().println(sCurrentLine);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
