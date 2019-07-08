package com.renevoi.moviecatalogservice.resources;

import com.renevoi.moviecatalogservice.models.CatalogItem;
import com.renevoi.moviecatalogservice.models.Movie;
import com.renevoi.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    private RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder webClientBuilder;

    public MovieCatalogResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {

        /*List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("4567", 3)
        );*/
        // Using restTemplate to consume/access the ratings API
        UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);

        return userRating.getUserRating().stream().map(rating -> {
            //Using Rest template
            // For each movie ID, call movie info service and get details (used restTemplate to consume movie API)
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);

            //Using WebClient
            /*Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();*/
            // Put them all together
            return new CatalogItem(movie.getName(), "sample description", rating.getRating());
        }).collect(Collectors.toList());

        /*return Collections.singletonList(
                new CatalogItem("Transformer", "sample", 4)
        );*/

    }

}
