package com.example.linkshortener.controllers;

import com.example.linkshortener.models.Link;
import com.example.linkshortener.services.LinkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class LinkController {
    private final LinkService linkService;

    @GetMapping("/")
    public String getAllLinks() {
        List<Link> links = linkService.getAllLinks();
        return links.toString();
    }

    @PostMapping("/")
    public String createLink(@Valid Link link) {
        return linkService.createLink(link).toString();
    }


    @GetMapping("/{name}")
    public String getOriginalLink(@PathVariable String name) {
        Link link = linkService.getLink(name);
        return link.toString();
    }

}
