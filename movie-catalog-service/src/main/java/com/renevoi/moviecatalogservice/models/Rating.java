package com.renevoi.moviecatalogservice.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Rating {
    private String movieId;
    private int rating;
}
