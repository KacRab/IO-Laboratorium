package org.example.web.rest.dto;

import lombok.Data;

@Data
public class KsiazkaDTO {
    private String title;
    private String cover;
    private int autorId;
    private float rating;
}
