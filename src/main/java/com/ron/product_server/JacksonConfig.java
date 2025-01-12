package com.ron.product_server;

//import javax.ws.rs.ext.ContextResolver;
//import javax.ws.rs.ext.Provider;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

//@Provider
//public class JacksonConfig implements ContextResolver<ObjectMapper> {
//    private final ObjectMapper objectMapper;
//
//    public JacksonConfig() {
//        objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule()); // 註冊 JavaTimeModule
//    }
//
//    @Override
//    public ObjectMapper getContext(Class<?> type) {
//        return objectMapper;
//    }
//}
