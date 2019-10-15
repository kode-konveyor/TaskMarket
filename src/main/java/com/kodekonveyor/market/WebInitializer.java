package com.kodekonveyor.market;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.kodekonveyor.market.SpringConfig;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/dynamic-registration"};
    }

}
