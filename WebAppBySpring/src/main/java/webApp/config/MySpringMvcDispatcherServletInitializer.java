package webApp.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// класс вместо web.xml
public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {   // метод нам не нужен
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // вместо <param-value>/WEB-INF/applicationContextMVC.xml</param-value>
        return new Class[]{SpringConfig.class}; // возвращаем массив конфигов
    }

    @Override
    protected String[] getServletMappings() {
        // вместо <url-pattern>/</url-pattern>
        return new String[]{"/"};
    }
}
