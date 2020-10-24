package com.example.pokemon.config;


import org.springframework.stereotype.Component;

@Component
public class AuthenticationEntryPoint /*extends BasicAuthenticationEntryPoint*/ {

    /*@Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.addHeader("WWW-Authenticate", "Basic realm - " + getRealmName());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
        var writer = response.getWriter();
        writer.println("Http Status 401: " + authException.getMessage());
    }*/
/*
    @Override
    public void afterPropertiesSet() {
        setRealmName("SupernovaIT");
        super.afterPropertiesSet();
    }*/
}
