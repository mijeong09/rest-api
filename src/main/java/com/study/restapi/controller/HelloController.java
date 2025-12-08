package com.study.restapi.controller;

import com.study.restapi.dto.MessageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller => html 을 찾아서 반환
@RestController // => 데이터를 반환 (JAVA 객체를 JSON 으로 return 해줌)
public class HelloController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello Wolrd";
    }

    @GetMapping("/api/message")
    public MessageResponse message() {
        return new MessageResponse("hello", 200);
    }

    @GetMapping("/api/messages")
    public List<MessageResponse> messages() {
        return List.of(
            new MessageResponse("hello", 200),
            new MessageResponse("hello", 200),
            new MessageResponse("hello", 200)
        );
    }
}
