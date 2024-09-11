package com.openclassrooms.watchlist.actuator;

import com.openclassrooms.watchlist.service.MovieRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {

    private MovieRatingService movieRatingService;

    @Autowired
    public HealthCheck(MovieRatingService movieRatingService) {
        this.movieRatingService = movieRatingService;
    }

    @Override
    public Health health() {
        if (! movieRatingService.getMovieRating("Up").isPresent()) {
            return Health.down().withDetail("Cause", "OMDb API is not available").build();
        }

        return Health.up().build();
    }
}
