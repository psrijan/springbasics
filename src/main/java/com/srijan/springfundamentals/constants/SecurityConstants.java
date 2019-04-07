package com.srijan.springfundamentals.constants;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SECURITY_HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
    public static final String WORD_URL = "/word/**";
    public static final String SENTENCE_URL = "/sentence/**";
    public static final String WORD_GROUP_URL = "/word-group/**";
//    public static final String  = "/users/sign-up";

}