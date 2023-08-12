package com.sogeti.filmland;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmLandHealth {
    @GetMapping("/messages")
    public String getMessage() {
        return "Hello from Docker!";
    }
}
