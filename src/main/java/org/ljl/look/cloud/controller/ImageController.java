package org.ljl.look.cloud.controller;

import org.ljl.look.cloud.entity.Image;
import org.ljl.look.cloud.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PutMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void put(@Validated @RequestBody Image image) {
        imageService.add(image);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<String> get(@RequestParam String belongTo) {
        return imageService.select(belongTo);
    }
}
