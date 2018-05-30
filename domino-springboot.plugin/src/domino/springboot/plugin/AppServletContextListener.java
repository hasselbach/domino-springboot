package domino.springboot.plugin;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppServletContextListener
               implements ServletContextListener{

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("ServletContextListener destroyed");
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("ServletContextListener started");
    }
}