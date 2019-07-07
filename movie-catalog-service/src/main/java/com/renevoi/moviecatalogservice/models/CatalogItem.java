package com.renevoi.moviecatalogservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogItem {

    private String name;
    private String desc;
    private int rating;

}
