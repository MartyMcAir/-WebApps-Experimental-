package org.example;

// mortbay - это jetty 6.1 _ old

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.ServletHandler;
import org.mortbay.jetty.webapp.WebAppContext;

import java.io.File;

/**
 * Hello world!
 */
public class JettyServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(9999);

//        setWebAppServlet(server);

        setServlet(server);
        server.start();
        server.join();
    }

    private static void setWebAppServlet(Server server) {
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setWar(new File("webapp/target/webapp-1.0-SNAPSHOT.war").getAbsolutePath());
        webAppContext.setContextPath("/web");

        server.setHandlers(new WebAppContext[]{webAppContext});
    }

    private static void setServlet(Server server) {
        ServletHandler handler = new ServletHandler();

        handler.addServletWithMapping(PingHandler.class, "/ping");
        server.setHandlers(new ServletHandler[]{handler});
    }
}
