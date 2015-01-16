package vmn.tve.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Application initializer.
 *
 */
public class ApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(final ServletContext context) throws ServletException {
        XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
        rootContext.setConfigLocation("classpath:context.xml");
        rootContext.setServletContext(context);
        rootContext.refresh();
        context.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(DispatcherServletConfig.class);

        ServletRegistration.Dynamic dispatcher = context.addServlet("dispatcher",
                new DispatcherServlet(dispatcherContext));
        dispatcher.setAsyncSupported(true);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/*");
    }
}
