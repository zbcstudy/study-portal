//package com.wondertek.baiying.web;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import org.springframework.web.util.HtmlUtils;
//
//import java.io.IOException;
//
///**
// * 基于xss的jsonSerializer
// * Created by wd on 2017/9/8.
// */
//public class XssStringJsonSerializer extends JsonSerializer<String> {
//
//    @Override
//    public Class<String> handledType() {
//        return String.class;
//    }
//
//    @Override
//    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
////        if (value != null) {
////            String encodedValue = HtmlUtils.htmlEscape(value);
////            JsonGenerator.writeString(encodedValue);
////        }
//    }
//}
