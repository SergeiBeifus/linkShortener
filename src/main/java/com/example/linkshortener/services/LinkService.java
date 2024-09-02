package com.example.linkshortener.services;

import com.example.linkshortener.exceptions.LinkErrors;
import com.example.linkshortener.exceptions.LinkServiceException;
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

    public Link createLink(Link link) throws LinkServiceException {
        UrlValidator urlValidator = new UrlValidator();
        if (!urlValidator.isValid(link.originalUrl)) {
            throw new LinkServiceException(LinkErrors.ORIGINAL_LINK_INVALID);
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

        return linkRepository.save(link);
    }

    public Link getLink(String redirectUrl) throws LinkServiceException {
        return linkRepository.findById(redirectUrl).orElseThrow(() -> new LinkServiceException(LinkErrors.LINK_NOT_FOUND));
    }

    public List<Link> getAllLinks() {
        return linkRepository.findAll();
    }
}
