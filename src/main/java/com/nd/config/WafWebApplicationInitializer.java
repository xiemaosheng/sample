package com.nd.config;


import com.nd.gaea.rest.AbstractWafWebApplicationInitializer;

/**
 *
 * @author
 * @since
 */

public class WafWebApplicationInitializer extends AbstractWafWebApplicationInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { ApiSecurityConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebMvcConfig.class};
    }

}
