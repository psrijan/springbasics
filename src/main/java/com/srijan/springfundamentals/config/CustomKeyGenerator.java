package com.srijan.springfundamentals.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

@Slf4j
public class CustomKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        String key =  target.getClass().getSimpleName()+ "_" +
                StringUtils.arrayToDelimitedString(params, "_");
        log.debug("Key : {} " , key);
        return key;
    }
}
