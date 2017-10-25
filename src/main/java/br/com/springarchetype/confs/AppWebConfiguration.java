package br.com.springarchetype.confs;

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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.google.common.cache.CacheBuilder;

/**
 * Classe que será usada como classe de configuração do servlet do SpringMVC.
 */

@EnableWebMvc 
@EnableCaching 
@ComponentScan(basePackages = { "br.com.springarchetype.controllers", 
								"br.com.springarchetype.repositories",
								"br.com.springarchetype.models",
								"br.com.springarchetype.services"
				              }) // Classes que serão mapeadas pelo Spring...
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

	
	/**
	 * Método que carregará os arquivos de mensagens de validação...
	 * 
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages"); 
		messageSource.setDefaultEncoding("UTF-8");
		// Tempo que o Spring fará cache do arquivo...
		messageSource.setCacheSeconds(1); 
		return messageSource;
	}

	/**
	 * Esse método seta o padrão de data deafult para nossa aplicação eliminando
	 * a necessidade de colocar o pattern dd/MM/yyyy na
	 * anotação @DateTimeFormat(pattern="dd/MM/yyyy") ficando apenas	 * 
	 * @DateTimeFormat
	 */
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		DateFormatterRegistrar formatterRegistrar = new DateFormatterRegistrar();
		formatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		formatterRegistrar.registerFormatters(conversionService);
		return conversionService;
	}

	/**
	 * Configuração dos arquivos estaticos no Spring...
	 * 
	 * Por padrão o spring pega qualquer requisição, inclusive o link de arquivos css e javascript. 
	 * Para corrigir isso precisamos fazer a nossa classe de configuração herdar de WebMvcConfigurerAdapter e 
	 * sobreescrever o método configureDefaultServletHandling. 
	 * 
	 * */	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("/resources/"); 
    }	
	

	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
	/**
	 * Objeto nativo do Spring para trabalhar com requisições Rest...
	 * */
	@Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }	
	
	/**
	 * Criando um gerenciador de caches na aplicação...
	 * */
	
	@Bean
    public CacheManager cacheManager() {
   		CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(5, TimeUnit.MINUTES);
        GuavaCacheManager manager = new GuavaCacheManager();
        manager.setCacheBuilder(builder);
        return manager;		
    }
	
	/**
	 * Registrando Interceptors...
	 * */
		
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new LocaleChangeInterceptor());	   
	}
	
	@Bean
	public LocaleResolver localeResolver(){
	    return new CookieLocaleResolver();
	}
	
	/**
	 * Implementando um enviador de e-mails...
	 * 
	 * */
	@Bean
	public MailSender mailSender(){
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setUsername("santaniello.fatec@gmail.com");
	    mailSender.setPassword("111111");
	    mailSender.setPort(587);
	    
	    Properties mailProperties = new Properties();
	    mailProperties.put("mail.smtp.auth", true);
	    mailProperties.put("mail.smpt.starttls.enable", true);
	    mailProperties.put("mail.smtp.starttls.enable", true);  
	    mailProperties.put("mail.smtp.socketFactory.port", 465);  
	    mailProperties.put("mail.smtp.socketFactory.fallback", false);  
	    mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    mailSender.setJavaMailProperties(mailProperties);
	    return mailSender;
	}	
}
