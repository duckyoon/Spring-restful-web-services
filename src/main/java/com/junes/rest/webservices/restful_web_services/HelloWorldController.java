package com.junes.rest.webservices.restful_web_services;


import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

// REST API
@RestController
public class HelloWorldController {

    // 메시지의 매개 변수화나 국제화에 대한 지원을 하는 인터페이스
    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource){
        this.messageSource = messageSource;
    }

//    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello world";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean ("Hello world");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean (String.format("Hello World %s", name));
    }


    // REST API 국제화 사용
    // messages.properties 파일 만들어서, GET 요청 보낼 때, Header에 Accept_Language를 설정하면 사용 가능!
    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized() {

        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, locale);
//        return "Hello World V2";
    }


}

