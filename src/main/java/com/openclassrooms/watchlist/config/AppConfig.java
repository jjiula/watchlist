package com.openclassrooms.watchlist.config;

import com.openclassrooms.watchlist.repository.WatchlistRepository;
import com.openclassrooms.watchlist.service.MovieRatingServiceImpl;
import com.openclassrooms.watchlist.service.WatchlistService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public WatchlistRepository watchlistRepository() {
        return new WatchlistRepository();
    }

    @Bean
    public MovieRatingServiceImpl movieRatingService() {
        return new MovieRatingServiceImpl();
    }

    @Bean
    public WatchlistService watchlistService() {
        return new WatchlistService(watchlistRepository(),movieRatingService());
    }

}
