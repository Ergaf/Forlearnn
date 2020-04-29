package myserver;

import myserver.servlets.LoginServlet;
import myserver.servlets.RegisterServlet;
import myserver.servlets.ZakazServlet;
import myserver.servlets.MainServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;


public class ServerConfig {
    private Server server;

    public void start() throws Exception {
        server = new Server();

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(90);
        server.setConnectors(new Connector[]{connector});

        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);

        servletHandler.addServletWithMapping(MainServlet.class, "/");
        servletHandler.addServletWithMapping(ZakazServlet.class, "/status");
        servletHandler.addServletWithMapping(RegisterServlet.class, "/register");
        servletHandler.addServletWithMapping(LoginServlet.class, "/login");
//        servletHandler.addFilterWithMapping(CORSFilter.class, "/status", 1);

        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }
}
