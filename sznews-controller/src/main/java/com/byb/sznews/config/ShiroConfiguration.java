package com.byb.sznews.config;

import com.byb.framework.constant.ShiroUrl;
import com.byb.sznews.filter.PermissionFilter;
import com.byb.framework.result.Result;
import com.byb.sznews.realms.CredentialsBasedPassword;
import com.byb.sznews.realms.SimpleSRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

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




    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager());
        filterFactoryBean.setLoginUrl(ShiroUrl.loginUrl);
        filterFactoryBean.setSuccessUrl(ShiroUrl.successUrl);
        filterFactoryBean.setUnauthorizedUrl(ShiroUrl.unauthorizedUrl);

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
                // 其它不需要验证的接口
                .append("/auth/login").append("=").append("anon").append("\r\n")
                .append("/auth/register").append("=").append("anon").append("\r\n")
                .append("/error/**").append("=").append("anon").append("\r\n")
                // 其余任何请求，均要验证
                .append("/**").append("=").append("authc,permission");
        return builder.toString();
    }

    @Bean
    public SimpleSRealm simpleSRealm() {
        SimpleSRealm simpleSRealm = new SimpleSRealm();
        simpleSRealm.setCredentialsMatcher(new CredentialsBasedPassword());
        return simpleSRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(simpleSRealm());
        return securityManager;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助 SpringAOP 描使用 Shiro 注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
