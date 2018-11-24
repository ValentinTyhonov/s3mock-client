package com.vtyhonov.s3mockclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping
@ApiIgnore
public class HomeController {

    @GetMapping
    public RedirectView home() {
        return new RedirectView("swagger-ui.html");
    }
}
