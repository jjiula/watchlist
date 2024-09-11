package com.openclassrooms.watchlist.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.openclassrooms.watchlist.controller.WatchlistController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ConditionalOnProperty(name = "app.environment", havingValue = "prod")
public class MovieRatingServiceImpl implements MovieRatingService {

    // the url that works has api key at the end, not the beginning...
    // this is the code from the video
    String apiUrl = "https://www.omdbapi.com/?apikey=a1dc4a8e";

    private final Logger logger = LoggerFactory.getLogger(MovieRatingServiceImpl.class);

    @Override
    public String getMovieRating(String title) {

        try {
            RestTemplate template = new RestTemplate();

            logger.info("OMDb API called with URL: {}", apiUrl + title);

            template.getForEntity(apiUrl + title, ObjectNode.class);

            ResponseEntity<ObjectNode> response =
                    template.getForEntity(apiUrl + title, ObjectNode.class);

            ObjectNode jsonObject = response.getBody();

            logger.debug("OMDb API response: {}", jsonObject);

            return jsonObject.path("imdbRating").asText();
        } catch (Exception e) {
            logger.warn("Something went wrong while calling OBDb API" + e.getMessage());
            return null;
        }
    }
}
