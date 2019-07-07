package com.renevoi.moviecatalogservice.resources;

import com.renevoi.moviecatalogservice.models.CatalogItem;
import com.renevoi.moviecatalogservice.models.Rating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {


    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {


        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("4567", 3)
        );

        return ratings.stream().map(rating -> new CatalogItem("Transformer", "sample", 4))
                .collect(Collectors.toList());

        /*return Collections.singletonList(
                new CatalogItem("Transformer", "sample", 4)
        );*/

    }

}
