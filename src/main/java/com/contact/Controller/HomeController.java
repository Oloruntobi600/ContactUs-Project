package com.contact.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Index page", description = "This is the Home Controller")
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Welcome to FootPrint!";
    }
}
