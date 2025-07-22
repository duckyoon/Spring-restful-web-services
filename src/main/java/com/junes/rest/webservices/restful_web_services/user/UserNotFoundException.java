package com.junes.rest.webservices.restful_web_services.user;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND) // 404 에러코드 반환
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message){
        super(message); // 메시지를 받아서 부모 클래스에게 전달
    }
}
