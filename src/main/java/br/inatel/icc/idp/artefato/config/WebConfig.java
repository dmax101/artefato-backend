package br.inatel.icc.idp.artefato.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebMvc
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    Environment env;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        String frontend = env.getProperty("artefato.frontend.url");
        log.info("Enable front end: " + frontend);

        registry.addMapping("/**").allowedOrigins(frontend).allowedMethods("GET", "POST", "DELETE", "HEAD")
                .allowCredentials(false).maxAge(3600);
    }

}