package com.example.linkshortener.services;

import com.example.linkshortener.models.Link;
import com.example.linkshortener.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class LinkService {
    private final LinkRepository linkRepository;

    public boolean createLink(Link link) {
        UrlValidator urlValidator = new UrlValidator();
        if (urlValidator.isValid(link.originalUrl)) {
            return false;
        }

        if (link.redirectUrl == null || link.redirectUrl.isEmpty()) {
            while (true) {
                String redirectUrl = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
                if (this.getLink(redirectUrl) == null) {
                    link.redirectUrl = redirectUrl;
                    break;
                }
            }

        }

        linkRepository.save(link);
        return true;
    }

    public Link getLink(String redirectUrl) {

        return linkRepository.findById(redirectUrl).orElse(null);
    }

    public List<Link> getAllLinks() {
        return linkRepository.findAll();
    }
}
