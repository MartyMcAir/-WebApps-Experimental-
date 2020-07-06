package listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {
    // слушатель используется для получения инфы какого либо события,
    // достпу к которому мы имеем ограниченный
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class);

    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("Application started");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("Application destroyed");
    }

}