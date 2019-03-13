package com.srijan.springfundamentals.properties;

import com.srijan.springfundamentals.properties.models.MainUser;
import com.srijan.springfundamentals.properties.models.YAMLConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.core.io.Resource;
import javax.annotation.PostConstruct;

@Getter
@Setter
@Slf4j
@Component
public class PropertiesLoader {
    @Value("${hmac.token}")
    private String hmacToken;

    @Value("${hmac.username:test}")
    private String username;

    @Autowired
    private MainUser user;

    @Value("${random.number}")
    private String randomInt;

    @Value("${random.long}")
    private String randomLong;

    @Value("${random.randomUUID}")
    private String randomUUID;

    @Value("${foo.name}")
    private String fooName;

    @Autowired
    private Environment envi;


    @PostConstruct
    private void init() {
        //Way 1 to use environment variable
        log.info(" hmac token: {} ", hmacToken);
        log.info(" hmac username: {} ", username);

        //Way 2 to use environment PropertiesLoader
        log.info(" Getting Values From Environment : {} ", envi.getProperty("hmac.token"));

        // Way 3 using Grouped Component using @ConfigurationProperties()
        //This is a good way to group hierarchial properties inside a single file.
        log.info(" user.username : {} ", user.getUsername());
        log.info(" user.password : {} ", user.getPassword());
        log.info(" user.url : {} ", user.getUrl());

        //Generating Random Numbers in property files
        log.info(" randomlong : {} ", randomLong);
        log.info(" randomInt: {} ", randomInt);
        log.info(" randomUUID: {} ", randomUUID);

        properties();

        //you can also use multiple properties file to load your dependencies.
        log.info(" foo Name : {} " , fooName);

//        log.info("using environment: {}" , yamlConfig.getEnvironment());
//        log.info("name: {}" , yamlConfig.getName());
//        log.info("servers: {}" , yamlConfig.getServers());

    }

    @Bean
    public static PropertyPlaceholderConfigurer properties() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[]{ new ClassPathResource("foo.properties")};
        ppc.setLocations(resources);
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }

}
