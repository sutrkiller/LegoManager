package cz.muni.fi.pa165.lego.mvc.config;

import cz.muni.fi.pa165.sampledata.LegoManagerWithSampleDataConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.validation.Validator;

/**
 * The central Spring context and Spring MVC configuration. The @Configuration
 * annotation declares it as Spring configuration. The @EnableWebMvc enables
 * default MVC config for using @Controller, @RequestMapping and so on, see
 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html#mvc-config-enable
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 12.12.2015
 */
@EnableWebMvc
@Configuration
@Import({LegoManagerWithSampleDataConfiguration.class})
@ComponentScan(basePackages = "cz.muni.fi.pa165")
public class MySpringMvcConfig extends WebMvcConfigurerAdapter {

    final static Logger log = LoggerFactory.getLogger(MySpringMvcConfig.class);

    public static final String TEXTS = "Texts";

    /**
     * Maps the main page to a specific view.
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        log.debug("mapping URL / to home view");
        registry.addViewController("/").setViewName("home");
    }

    /**
     * Enables default Tomcat servlet that serves static files.
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        log.debug("enabling default servlet for static files");
        configurer.enable();
    }

    /**
     * Enables
     * @param registry
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    /**
     * Provides mapping from view names to JSP pages in WEB-INF/jsp directory.
     * @return viewResolver
     */
    @Bean
    public ViewResolver viewResolver() {
        log.debug("registering JSP in /WEB-INF/jsp/ as views");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /**
     * Provides localized messages.
     * @return messageSource
     */
    @Bean
    public MessageSource messageSource() {
        log.debug("registering ResourceBundle 'Texts' for messages");
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:"+TEXTS);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * Provides JSR-303 Validator.
     * @return Validator
     */
    @Bean
    public Validator validator() {
        log.debug("registering JSR-303 validator");
        return new LocalValidatorFactoryBean();
    }

}
