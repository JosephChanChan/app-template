package com.byb.sznews.config;

import com.byb.framework.filter.PermissionFilter;
import com.byb.sznews.realms.SimpleSRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Joseph
 * @since 2019/7/6 11:03
 */
@Configuration
public class ShiroConfiguration {

    private static Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

    private static final String loginUrl = "/user/login";
    private static final String successUrl = "";
    private static final String unauthorizedUrl = "/user/unauthorized";

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager());
        filterFactoryBean.setLoginUrl(loginUrl);
        filterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);

        filterFactoryBean.setFilterChainDefinitions(definitions());

        Map<String, Filter> filterMap = new HashMap<>(4);
        filterMap.put("permission", new PermissionFilter());
        filterFactoryBean.setFilters(filterMap);
        return filterFactoryBean;
    }

    private String definitions() {
        StringBuilder builder = new StringBuilder();
        builder
                // 静态资源
                .append("/js/**").append("=").append("anon").append("\r\n")
                .append("/css/**").append("=").append("anon").append("\r\n")
                .append("/static/**").append("=").append("anon").append("\r\n")
                // swagger 2
                .append("/v2/api-docs").append("=").append("anon").append("\r\n")
                .append("/webjars/**").append("=").append("anon").append("\r\n")
                .append("/swagger-resources/**").append("=").append("anon").append("\r\n")
                .append("/swagger-ui.html").append("=").append("anon").append("\r\n")
                .append("/swagger*").append("=").append("anon").append("\r\n")
                // 其余任何请求，均要验证
                .append("/auth/login").append("=").append("anon").append("\r\n")
                .append("/auth/register").append("=").append("anon").append("\r\n")
                .append("/**").append("=").append("authc,permission");
        return builder.toString();
    }


    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(simpleSRealm());
        return securityManager;
    }

    @Bean
    public Realm simpleSRealm() {
        return new SimpleSRealm();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
