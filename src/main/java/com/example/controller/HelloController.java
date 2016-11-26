package com.example.controller;

import com.example.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.example.Constants.BeanNames.EXAMPLE_SERVICE;

@Slf4j
@ResponseBody
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Resource(name = EXAMPLE_SERVICE)
    private HelloService helloService;

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<String> hello(@PathVariable String name) {
        try {
            return new ResponseEntity<>(helloService.hello(name), HttpStatus.OK);
        } catch (Exception exception) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("error", exception.getMessage());
            return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
