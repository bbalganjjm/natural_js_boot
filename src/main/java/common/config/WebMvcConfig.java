package common.config;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2020.11.25
 */

import common.exception.ExceptionResolver;
import common.filter.XSSFilter;
import common.interceptor.PermissionCheckInterceptor;
import common.views.FileView;
import common.views.XlsxStreamingView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.PathExtensionContentNegotiationStrategy;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.*;

@Configuration
@ComponentScan(basePackages = { "common" }, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class) })
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    Environment env;

    /**
     * Servlet Filters
     */
    @Bean
    public FilterRegistrationBean<ShallowEtagHeaderFilter> shallowEtagHeaderFilter() {
        FilterRegistrationBean<ShallowEtagHeaderFilter> frb = new FilterRegistrationBean<>(new ShallowEtagHeaderFilter());
        frb.setUrlPatterns(Arrays.asList("/*"));
        return frb;
    }
    @Bean
    public FilterRegistrationBean<XSSFilter> xssFilter() {
        FilterRegistrationBean<XSSFilter> frb = new FilterRegistrationBean<>(new XSSFilter());
        frb.setUrlPatterns(Arrays.asList("/*"));
        return frb;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.KOREA);
        return slr;
    }
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    /**
     * 권한 체크 인터셉터
     *
     * excludeURLs에 AntPath Type의 URL로 지정하면 퀀한 체크에서 제외 됨.
     */
    @Bean
    public PermissionCheckInterceptor permissionCheckInterceptor() {
        PermissionCheckInterceptor pci = new PermissionCheckInterceptor();
        pci.setExcludeURLs(new String[] { "/**/login/*" });
        return pci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(permissionCheckInterceptor());
    }

    @Bean
    public MappingJackson2JsonView jsonView() {
        MappingJackson2JsonView mj2jv = new MappingJackson2JsonView();
        mj2jv.setPrefixJson(false);
        mj2jv.setExtractValueFromSingleKeyModel(true);
        return mj2jv;
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public SpringResourceTemplateResolver springResourceTemplateResolver() {
        SpringResourceTemplateResolver srtr = new SpringResourceTemplateResolver();
        srtr.setApplicationContext(this.applicationContext);
        srtr.setPrefix("classpath:templates/");
        srtr.setSuffix(".html");
        srtr.setTemplateMode(TemplateMode.HTML);

        if (env.getActiveProfiles().length == 0 || !"prod".equals(env.getActiveProfiles()[0])) {
            srtr.setCacheable(false);
        }
        return srtr;
    }
    @Bean
    public SpringTemplateEngine springTemplateEngine() {
        SpringTemplateEngine ste = new SpringTemplateEngine();
        ste.setTemplateResolver(springResourceTemplateResolver());
        ste.setEnableSpringELCompiler(true);
        return ste;
    }
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver tvr = new ThymeleafViewResolver();
        tvr.setCharacterEncoding("UTF-8");
        tvr.setTemplateEngine(springTemplateEngine());
        return tvr;
    }

//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.favorPathExtension(true)
//                .ignoreAcceptHeader(true)
//                .defaultContentType(MediaType.APPLICATION_JSON)
//                .mediaType("json", MediaType.APPLICATION_JSON)
//                .mediaType("view", MediaType.TEXT_HTML)
//                .mediaType("xlsx", MediaType.valueOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
//    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        Map<String, MediaType> mediaTypes = new HashMap<String, MediaType>();
        mediaTypes.put("json", MediaType.APPLICATION_JSON);
        mediaTypes.put("view", MediaType.TEXT_HTML);
        mediaTypes.put("xlsx", MediaType.valueOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));

        configurer.ignoreAcceptHeader(true)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .defaultContentTypeStrategy(new PathExtensionContentNegotiationStrategy(mediaTypes));
    }

    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();

        List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();
        viewResolvers.add(new BeanNameViewResolver());
        viewResolvers.add(thymeleafViewResolver());
        resolver.setViewResolvers(viewResolvers);

        List<View> defaultViews = new ArrayList<View>();
        defaultViews.add(jsonView());
        defaultViews.add(new XlsxStreamingView());
        resolver.setDefaultViews(defaultViews);

        resolver.setContentNegotiationManager(manager);

        return resolver;
    }

    @Bean
    public ExceptionResolver exceptionResolver() {
        ExceptionResolver er = new ExceptionResolver();
        er.setJson("jsonView");
        er.setForm("common/error/500"); // web(thymeleaf) form request
        return er;
    }

    @Bean
    public FileView fileView() {
        FileView fv = new FileView();
        return fv;
    }
}