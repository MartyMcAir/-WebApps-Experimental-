package com.app;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;

public class EmbeddedJettyMain {

    public static void main(String[] args) throws Exception {

        Server server = new Server();

        //Creating the web application context
        WebAppContext webapp = new WebAppContext();
        webapp.setResourceBase("src/main/webapp");
        server.setHandler(webapp);

        // HTTP Configuration
        HttpConfiguration http = new HttpConfiguration();
        http.addCustomizer(new SecureRequestCustomizer());

        //Configuration for HTTPS redirect
        http.setSecurePort(8443);
        http.setSecureScheme("https");
        ServerConnector connector = new ServerConnector(server);
        connector.addConnectionFactory(new HttpConnectionFactory(http));
        //Setting HTTP  port
        connector.setPort(8080);

        //HTTPS configuration
        HttpConfiguration https = new HttpConfiguration();
        https.addCustomizer(new SecureRequestCustomizer());


        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath(EmbeddedJettyMain.class.getResource("keystore").toExternalForm());
        sslContextFactory.setKeyStorePassword("javacodegeeks");
        sslContextFactory.setKeyManagerPassword("javacodegeeks");

        ServerConnector sslConnector = new ServerConnector(server, new SslConnectionFactory(sslContextFactory, "http/1.1"), new HttpConnectionFactory(https));
        sslConnector.setPort(8443);

        server.setConnectors(new Connector[]{connector, sslConnector});

        server.start();
        server.join();

    }
}
