package ru.ddsurok.utils;

import java.io.File;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.PropertyConfigurator;

public class Log4jInit implements ServletContextListener {
       
    @Override
    public void contextInitialized(ServletContextEvent event) {
        String webInfDir=event.getServletContext().getRealPath("/WEB-INF/");
        PropertyConfigurator.configure(new File(webInfDir,"log4j.properties").toString());
        com.sun.xml.ws.fault.SOAPFaultBuilder.captureStackTrace = false;
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent event) {}
}
