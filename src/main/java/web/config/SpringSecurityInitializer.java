package web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


@Configuration
@EnableWebSecurity
public class SpringSecurityInitializer
        extends AbstractSecurityWebApplicationInitializer {
    //пуой класс, использующийся для регистрации модуля в спринг-контейнере
    /*public SpringSecurityInitializer() {
        super(SecurityConfig.class);
    }*/
}