package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/WebApplicationInitializer.html
@Configuration
public class WebConfig implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) throws ServletException {
//        LoadFromXmlConfig(container);
        loadFromJavaCode(container);
    }

    private void loadFromJavaCode(ServletContext container) {
        // Create the 'root' Spring application container
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(WebConfig.class);

        // Manage the lifecycle of the root application container
        container.addListener(new ContextLoaderListener(rootContext));
        container.setRequestCharacterEncoding("UTF-8");
        container.setResponseCharacterEncoding("UTF-8");

        // Create the dispatcher servlet's Spring application container
        AnnotationConfigWebApplicationContext appContext =
                new AnnotationConfigWebApplicationContext();

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher",
                new DispatcherServlet(appContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

    private void LoadFromXmlConfig(ServletContext container) {
        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
//        appContext.setServletContext();
        appContext.setConfigLocation("/WEB-INF/spring/dispatcher-config.xml");

        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher",
                new DispatcherServlet(appContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/simple");
    }


}
