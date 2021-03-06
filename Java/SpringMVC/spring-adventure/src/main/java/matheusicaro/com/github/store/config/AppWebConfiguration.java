package matheusicaro.com.github.store.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import matheusicaro.com.github.store.controller.HomeController;
import matheusicaro.com.github.store.daos.ProductDAO;
import matheusicaro.com.github.store.infra.FileSaver;
import matheusicaro.com.github.store.models.ShoppingCart;

@EnableWebMvc
@EnableCaching
@ComponentScan(basePackageClasses = { HomeController.class, ProductDAO.class, FileSaver.class, ShoppingCart.class })
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public InternalResourceViewResolver InternalResourceView() {

		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");

		resolver.setExposedContextBeanNames("shoppingCart"); // Bean available for viewing in the view.

		return resolver;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

		messageSource.setBasename("/WEB-INF/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1);

		return messageSource;
	}

	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		registrar.registerFormatters(conversionService);

		return conversionService;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public CacheManager cacheManager() {

		CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder().maximumSize(100)
												.expireAfterAccess(5,TimeUnit.MINUTES);
		GuavaCacheManager manager = new GuavaCacheManager();
		manager.setCacheBuilder(builder);

		return manager;
	}
	
	@Bean
	public ViewResolver contentNegotiationViewResolver(ContentNegotiationManager manager) {

	    List<ViewResolver> viewResolvers = new ArrayList<>();
	    viewResolvers.add(InternalResourceView());
	    viewResolvers.add(new JsonViewResolver());

	    ContentNegotiatingViewResolver resolver = 
	        new ContentNegotiatingViewResolver();
	    resolver.setViewResolvers(viewResolvers);
	    resolver.setContentNegotiationManager(manager);

	    return resolver;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new LocaleChangeInterceptor());
	}
	
	@Bean
	public LocaleResolver localeResolver(){
	    return new CookieLocaleResolver();
	}
	

	// handle static configuration, performance, and tomcat resources, and utilizing bootstrap and JS. 
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public MailSender mailSender() {

	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setUsername("send.development.mi@gmail.com");
	    mailSender.setPassword("send123*");
	    mailSender.setPort(465);

	    Properties mailProperties = new Properties();
	    mailProperties.put("mail.smtp.auth", true);
	    mailProperties.put("mail.smpt.starttls.enable", true);
	    mailProperties.put("mail.smtp.socketFactory.port", "465");  
	    mailProperties.put("mail.smtp.socketFactory.fallback", "false");  
	    mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    
	    mailSender.setJavaMailProperties(mailProperties);

	    return mailSender;
	}
}

