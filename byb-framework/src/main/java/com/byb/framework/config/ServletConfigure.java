package com.byb.framework.config;

import com.byb.framework.exception.GlobalExceptionHandler;
import com.byb.framework.resolver.FormArgumentResolver;
import com.byb.framework.resolver.SessionArgumentResolver;
import com.byb.framework.result.MarkDownResultTypeHandler;
import com.byb.framework.result.ResultJsonSerializer;
import com.byb.framework.result.ResultTypeHandler;
import com.byb.framework.result.ResultXmlSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.ResourceUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@EnableAsync
@Configuration
@EnableWebMvc
public class ServletConfigure extends WebMvcConfigurerAdapter implements ApplicationContextAware {

	public static final String ERROR_PAGE_PATH = "/error/500";

	private ApplicationContext applicationContext;

	private List<HttpMessageConverter<?>> converters;

	@Value("${photo.path}")
	private String path;

	@Bean
	public DefaultErrorAttributes errorAttributes() {
		return new DefaultErrorAttributes();
	}

	@Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
			return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_PAGE_PATH));
            container.addErrorPages(new ErrorPage(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ERROR_PAGE_PATH));
			container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, ERROR_PAGE_PATH));
			container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, ERROR_PAGE_PATH));
			container.addErrorPages(new ErrorPage(Throwable.class, ERROR_PAGE_PATH));
		};
    }

	/**
	 * 全局异常处理器
	 * @param errorAttributes
	 * @return
	 */
	@Bean
    public GlobalExceptionHandler globalExceptionHandler(ErrorAttributes errorAttributes){
    	return new GlobalExceptionHandler(errorAttributes);
	}

	/**
	 * 增加处理器去处理静态请求
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX,"classpath:/static/");
		registry.addResourceHandler("/user/pic/**", "/res/pic/**", "/pro/pic/**"
				, "/pro/file/**", "/mark/pic/**", "/tender/file/**", "/business/logo/**", "/hr/file/**")
				.addResourceLocations("file:" + path + File.separator);
		registry.addResourceHandler("swagger-ui.html").addResourceLocations(
				"classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations(
				"classpath:/META-INF/resources/webjars/");
		super.addResourceHandlers(registry);
	}

	/**
	 * 添加拦截器
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new OAuthInterceptor())
//				.excludePathPatterns("/error/**/*","/user/login","/user/checkLogin","/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
//		registry.addInterceptor(new SecurityInterceptor());
//		registry.addInterceptor(new SecurityInterceptor()).excludePathPatterns(
//				"/test/**/*", "/error/**/*", "/sys/user/login","/sys/user/logout",
//				"sys/user/register","/sys/user/check","/test/**/*","/role/permission");
		Map<String, HandlerInterceptorAdapter> interceptors = applicationContext.getBeansOfType(HandlerInterceptorAdapter.class);
		for (HandlerInterceptorAdapter interceptor : interceptors.values()){
			registry.addInterceptor(interceptor);
		}
	}

	/**
	 * 注册参数解析器
	 * @param argumentResolvers
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		HandlerMethodArgumentResolver formResolver = new FormArgumentResolver(converters);
		HandlerMethodArgumentResolver sessionResolver = new SessionArgumentResolver();
		argumentResolvers.add(formResolver);
		argumentResolvers.add(sessionResolver);
	}

	/**
	 * 注册数据转换器
	 * @param converters
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
		jsonHttpMessageConverter.setObjectMapper(new ResultJsonSerializer());
		converters.add(jsonHttpMessageConverter);
		ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
		byteArrayHttpMessageConverter.setDefaultCharset(Charset.forName("UTF-8"));
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		JacksonXmlModule module = new JacksonXmlModule();
		module.setDefaultUseWrapper(false);
		ResultXmlSerializer resultXml = new ResultXmlSerializer(module);
		//设置序列化不包含Java对象中为空的属性
		resultXml.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
		resultXml.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MappingJackson2XmlHttpMessageConverter xmlMessageConverter = new MappingJackson2XmlHttpMessageConverter();
		xmlMessageConverter.setObjectMapper(resultXml);
		converters.add(byteArrayHttpMessageConverter);
		converters.add(stringHttpMessageConverter);
		converters.add(xmlMessageConverter);
		this.converters = converters;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
				.allowedHeaders("*")
				.allowedMethods("GET", "POST", "OPTIONS", "DELETE", "PUT")
				.allowedOrigins("*")
				.allowCredentials(true)
				.maxAge(3600);
	}
	/**
	 * 注册返回结果处理器
	 * @param returnValueHandlers
	 */
	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		HandlerMethodReturnValueHandler handler = new ResultTypeHandler();
		HandlerMethodReturnValueHandler markHandler = new MarkDownResultTypeHandler();
		returnValueHandlers.add(handler);
		returnValueHandlers.add(markHandler);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
