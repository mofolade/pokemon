package com.example.pokemon.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
    /*static class CustomRepresentationModel extends RepresentationModel<CustomRepresentationModel> {
        public CustomRepresentationModel(Link initialLink) {
            super(initialLink);
        }
    }

    @GetMapping
    public CustomRepresentationModel index() {
        return new CustomRepresentationModel(linkTo(CRUDController.class).withRel("crud"));
    }*/
}
