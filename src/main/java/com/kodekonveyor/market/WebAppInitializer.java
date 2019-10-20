package com.kodekonveyor.market;

import javax.servlet.ServletContext;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

public class WebAppInitializer implements WebApplicationInitializer {

  public static XmlWebApplicationContext context;

  @Override
  public void onStartup(final ServletContext servletContext) {
    System.out.println("onStartup");

    final WebApplicationContext context = getContext();
    servletContext.addListener(new ContextLoaderListener(context));

    final CharacterEncodingFilter characterEncodingFilter =
        new CharacterEncodingFilter();
    characterEncodingFilter.setEncoding("UTF-8");
    characterEncodingFilter.setForceEncoding(true);
    servletContext.addFilter("characterEncodingFilter", characterEncodingFilter)
        .addMappingForUrlPatterns(null, false, "/*");
  }

  private XmlWebApplicationContext getContext() {
    context = new XmlWebApplicationContext();
    context.setConfigLocations("/WEB-INF/applicationContext.xml");
    return context;
  }

}
