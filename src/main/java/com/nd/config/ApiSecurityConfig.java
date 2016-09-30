package com.nd.config;


import com.nd.gaea.client.exception.ResponseErrorMessage;
import com.nd.gaea.client.http.BearerAuthorizationProvider;
import com.nd.gaea.client.support.DeliverBearerAuthorizationProvider;
import com.nd.gaea.rest.config.WafWebSecurityConfigurerAdapter;
import com.nd.gaea.rest.exceptions.WafErrorResolver;
import com.nd.gaea.rest.exceptions.WafRestErrorResolver;
import com.nd.gaea.rest.exceptions.rest.AbstractRestErrorHandler;
import com.nd.gaea.rest.exceptions.rest.RestErrorMappings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.servlet.http.HttpServletRequest;

@Configuration()
@EnableWebSecurity
@Order(101)
public class ApiSecurityConfig extends WafWebSecurityConfigurerAdapter {

    /**
     * 此处统一配置安全访问控制
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void onConfigure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/benchmark/blank").permitAll()
                .antMatchers(HttpMethod.GET, "/benchmark/bearer").authenticated()
                .antMatchers(HttpMethod.GET, "/benchmark/mac").authenticated()
                // 匹配"/students/**"的所有GET操作，无需要任何身份认证,不需要传token可以通过
                .antMatchers(HttpMethod.GET, "/students/**").permitAll()
                .antMatchers(HttpMethod.GET, "/books/**").permitAll()
                // 匹配"/students/**"的所有POST操作，需要用户拥有角色"teacher",需要传token进行验证
                .antMatchers(HttpMethod.POST, "/students/**").hasAuthority("teacher")
                // 匹配"/students/**"的所有PUT操作，需要用户拥有角色"student",需要传token进行验证
                .antMatchers(HttpMethod.PUT, "/students/**").hasAuthority("student")
                // 匹配"/students/**"的所有（其他）操作，需要用户拥有角色"teacher"或"role_biz_server"角色
                .antMatchers("/students/**").hasAnyAuthority("teacher","role_biz_server")
        // 若其他的Url地址均需要加身份认证，则请添加
//        .anyRequest().authenticated()
        ;
    }


    /**
     * demo:重写此方法，设置忽略路径
     */
/*	@Override
	public void configure(WebSecurity web) throws Exception {
		//忽略请求为/students/**的所有PUT方法的安全认证
		//web.ignoring().antMatchers(HttpMethod.PUT, "/students/**");
	}*/

    /**
     * 不使用mactoken的缓存类
     * @return
     */
/*	@Bean(name = {"mac_token_service"})
	@Primary
	public TokenService userCenterMacTokenService(){
		return new UserCenterMacTokenService();
	}*/

    /**
     * 设置传递Userid
     *
     * @return
     */
    @Bean
    @Primary
    public BearerAuthorizationProvider bearerAuthorizationProvider() {
        return new DeliverBearerAuthorizationProvider();
    }
    /**
     * demo:重写此方法，设置忽略路径
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //web.ignoring().antMatchers(new String[]{"/**"});
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    /**
     * 注册自定义异常处理类
     * @return
     */
    @Bean
    @Primary
    public WafErrorResolver wafErrorResolver() {
        WafRestErrorResolver resolver = new WafRestErrorResolver();
        resolver.addHandler(DataAccessException.class, new DbErrorHandler());
        for (RestErrorMappings mapping : RestErrorMappings.values()) {
            resolver.addHandler(mapping.getThrowableClass(), mapping.getHandler());
        }

        return resolver;
    }

    static class DbErrorHandler extends AbstractRestErrorHandler {

        private Logger logger = LoggerFactory.getLogger(this.getClass());

        @Override
        public ResponseEntity<ResponseErrorMessage> process(Throwable throwable, HttpServletRequest request) {
            ResponseErrorMessage errorMessage = new ResponseErrorMessage(throwable);
            errorMessage.setMessage("db error, error id："+errorMessage.getRequestId());
            errorMessage.setCode("DBERROR");
            this.updateRemoteErrorMessage(errorMessage, request);
            logger.error("DBERROR " + errorMessage.getRequestId(), throwable);
            HttpHeaders httpHandlers = getHttpHandlers(throwable, request);
            HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<ResponseErrorMessage>(errorMessage, httpHandlers, httpStatus);
        }

    }
}
