package com.kodekonveyor;

import java.lang.reflect.Field;
import java.util.Optional;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.MethodParameter;

import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;
import com.kodekonveyor.annotations.InterfaceClass;

@SpringBootApplication
@ExcludeFromCodeCoverage("glue")
@InterfaceClass
public class SpringConfig extends SpringBootServletInitializer {

  @Value("${com.kodekonveyor.market.jdbcUri}")
  private String jdbcUri;

  @Value("${com.kodekonveyor.market.jdbcDriver}")
  private String jdbcDriver;

  public static void main(final String[] args) {
    SpringApplication.run(SpringConfig.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(
      final SpringApplicationBuilder builder
  ) {
    return builder.sources(SpringConfig.class);
  }

  @Bean
  public DataSource dataSource() {
    final DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName(jdbcDriver);
    dataSourceBuilder.url(jdbcUri);
    return dataSourceBuilder.build();
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  @Scope("prototype")
  public Logger logger(final InjectionPoint injectionPoint) {
    return LoggerFactory.getLogger(
        Optional.ofNullable(injectionPoint.getMethodParameter())
            .<Class<?>>map(MethodParameter::getContainingClass)
            .orElseGet(() -> Optional.ofNullable(injectionPoint.getField())
                .map(Field::getDeclaringClass)
                .orElseThrow(IllegalArgumentException::new)
            )
    );
  }
}
