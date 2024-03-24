package dsdcohort.Application.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/")
    public String homePage() {
        return "Your Spring Boot application is running!";
    }
}
