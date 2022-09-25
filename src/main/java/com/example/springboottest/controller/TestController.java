package com.example.springboottest.controller;


import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TestController {

    private final Environment env;

    @GetMapping(value = "/test/{key}")
    public Object testMethod(@PathVariable String key) {
        Properties props = new Properties();
        MutablePropertySources propSrcs = ((AbstractEnvironment) env).getPropertySources();
        StreamSupport.stream(propSrcs.spliterator(), false)
                .filter(ps -> ps instanceof EnumerablePropertySource)
                .map(ps -> ((EnumerablePropertySource<?>) ps).getPropertyNames())
                .flatMap(Arrays::<String>stream)
                .forEach(propName -> props.setProperty(propName, env.getProperty(propName)));

        log.info("ENV: {}", props);
        HashMap<String, Object> map = new HashMap<>();
        map.put("key", key);
        map.put("spring.application.name", props.getProperty("spring.application.name", ""));
        map.put("myenv", props.getProperty("myenv", ""));
        map.put("datetime", ZonedDateTime.now());

        return map;
    }

}
