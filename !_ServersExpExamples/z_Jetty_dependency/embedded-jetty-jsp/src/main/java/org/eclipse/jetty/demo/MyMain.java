package org.eclipse.jetty.demo;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class MyMain {
    public static void main(String[] args) throws Exception {
        // from https://www.eclipse.org/jetty/documentation/current/using-annotations-embedded.html
        //Create the server
        Server server = new Server(8080);

        //Enable parsing of jndi-related parts of web.xml and jetty-env.xml
        org.eclipse.jetty.webapp.Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList.setServerDefault(server);
        classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration", "org.eclipse.jetty.plus.webapp.EnvConfiguration", "org.eclipse.jetty.plus.webapp.PlusConfiguration");
        classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", "org.eclipse.jetty.annotations.AnnotationConfiguration");

        //Create a WebApp
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
//        webapp.setWar("../../tests/test-webapps/test-servlet-spec/test-spec-webapp/target/test-spec-webapp-9.0.4-SNAPSHOT.war");
//        webapp.setWar("../../target/embeddedJettyJsp.war");
//        webapp.setWar("../../ExperimentalServers/embedded-jetty-jsp/target/embeddedJettyJsp.war");
        webapp.setWar("C:\\Users\\martymcair\\OneDrive\\Dropbox\\GitHub\\-WebApps-Experimental-\\!_ServersExpExamples\\z_ttt\\embedded-jetty-jsp\\target\\embeddedJettyJsp.war");
        server.setHandler(webapp);

        //Define an env entry with webapp scope.
        org.eclipse.jetty.plus.jndi.EnvEntry maxAmount = new org.eclipse.jetty.plus.jndi.EnvEntry(webapp, "maxAmount", new Double(100), true);

        server.start();
        server.join();
    }
}