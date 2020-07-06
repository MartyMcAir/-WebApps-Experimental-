package webApp.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

// класс вместо applicationContextMVC.xml
@Configuration // указываем что это конфиг - класс
@ComponentScan("webApp") // указываем какой пакет сканить на наличие аннотаций
@EnableWebMvc   // активируем использование WebMvc аннотаций <mvc:annotation-driven/>
public class SpringConfig implements WebMvcConfigurer {
    // для поднастройки Spring MVC под себя реализуем WebMvcConfigurer интерфейс
    // и в configureViewResolvers() указыаем нужный шаблонизатор
    private final ApplicationContext applicationContext;

    @Autowired
    public SpringConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        // подстраиваем кофиги под наш TimeLeaf шаблонизатор
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    // для указания нужного нам шаблонизатора
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // Указываем что хотим юзать ThymeLeaf - шаблонизатор
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }
}