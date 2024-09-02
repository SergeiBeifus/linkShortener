package com.example.linkshortener.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Link {
    @Id
    public String redirectUrl;
    @NotNull
    @NotBlank
    public String originalUrl;

}
