package com.srijan.springfundamentals.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.cache.interceptor.KeyGenerator;
import java.util.Arrays;


@Configuration
@Slf4j
public class CacheConfiguration {
//    @Bean
//    public CacheManager cacheManager() {
//        SimpleCacheManager cacheManager = new SimpleCacheManager();
//        Cache booksCache = new ConcurrentMapCache("books");
//        cacheManager.setCaches(Arrays.asList(booksCache));
//        return cacheManager;
//    }

    @Bean("customKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new CustomKeyGenerator();
    }
}
