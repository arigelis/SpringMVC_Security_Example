package web;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//import web.config.RootConfig;
import web.config.SecurityConfig;
import web.config.WebConfig;

import javax.servlet.ServletContext;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    // Метод, указывающий на класс конфигурации
    @Override
    protected Class<?>[] getRootConfigClasses() {
        /*return new Class<?>[]{
                RootConfig.class
        };*/
        return null;
    }

    // Добавление конфигурации, в которой инициализируем ViewResolver, для корректного отображения jsp.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebConfig.class
        };
    }


    /* Данный метод указывает url, на котором будет базироваться приложение */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

//
//    @Override
//    public void onStartup(ServletContext sc) {
//
//        AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
////        root.register(RootConfig.class);
//        root.register(SecurityConfig.class);
//        root.register(WebConfig.class);
//
//        sc.addListener(new ContextLoaderListener(root));
////        sc.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
////                .addMappingForUrlPatterns(null, false, "/*");
//        sc.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain"))
//                .addMappingForUrlPatterns(null, false, "/*");
//    }
}