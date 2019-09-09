package com.github.hemoptysisheart.spring.example.grpc.runner;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ApplicationContext;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author hemoptysisheart
 * @since 2019/09/01
 */
@SpringBootApplication(scanBasePackages = "com.github.hemoptysisheart.spring.example.grpc")
public class GrpcRunner {
  private static final Logger log = getLogger(GrpcRunner.class);

  public static void main(String... args) {
    SpringApplication application = new SpringApplication(GrpcRunner.class);
    application.addListeners(new ApplicationPidFileWriter());
    application.setWebApplicationType(WebApplicationType.SERVLET);

    final ApplicationContext ctx = application.run(args);

    if (log.isInfoEnabled()) {
      for (String name : ctx.getBeanDefinitionNames()) {
        log.info("bean : {}={}", name, ctx.getBean(name));
      }
    }
  }
}
