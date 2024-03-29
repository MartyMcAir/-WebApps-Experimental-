package com.boots;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    //public class AppRunner implements WebMvcConfigurer {
    public static void main(String[] args) {
        //		System.setProperty("spring.devtools.restart.enabled", "false");
//        System.setProperty("spring.devtools.add-properties", "false");

        // System.setProperty("server.servlet.context-path", "/myContextPath");
        SpringApplication.run(Application.class, args);
    }

    //    instead in application.properties
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/JSP/");
//        resolver.setSuffix(".jsp");
//        resolver.setViewClass(JstlView.class);
//        registry.viewResolver(resolver);
//    }


    //......................................................................................................
    //......................................................................................................
    //......................................................................................................
    // DON'T need
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configure) {
//        configure.enable();
//    }

//    public class ServletInitializer extends SpringBootServletInitializer {
//        @Override
//        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//            return application.sources(AppRunner.class);
//        }
//    }
}
