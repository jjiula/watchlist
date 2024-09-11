package com.openclassrooms.watchlist.service;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.exception.DuplicateTitleException;
import com.openclassrooms.watchlist.repository.WatchlistRepository;

import java.util.List;

public class WatchlistService {

    WatchlistRepository watchlistRepository;
    MovieRatingServiceImpl movieRatingServiceImpl;

    public WatchlistService(WatchlistRepository watchlistRepository, MovieRatingServiceImpl movieRatingServiceImpl) {
        super();
        this.watchlistRepository = watchlistRepository;
        this.movieRatingServiceImpl = movieRatingServiceImpl;
    }

    public List<WatchlistItem> getWatchlistItems() {
        List<WatchlistItem> watchlistItems = watchlistRepository.getList();

        for (WatchlistItem watchlistItem: watchlistItems) {
            String rating = movieRatingServiceImpl.getMovieRating(watchlistItem.getTitle());

            if (rating != null) {
                watchlistItem.setRating(rating);
            }
        }

        return watchlistRepository.getList();
    }

    public int getWatchlistItemsSize() {
        return watchlistRepository.getList().size();
    }

    public WatchlistItem findWatchlistItemById(Integer id) {
        return watchlistRepository.findById(id);
    }

    public void addOrUpdateWatchlistItem(WatchlistItem watchlistItem) {
        WatchlistItem existingItem = findWatchlistItemById(watchlistItem.getId());

        if (existingItem == null) {
            if (watchlistRepository.findByTitle(watchlistItem.getTitle())!=null) {
                throw new DuplicateTitleException();
            }
            watchlistRepository.addItem(watchlistItem);
        } else {
            existingItem.setComment(watchlistItem.getComment());
            existingItem.setPriority(watchlistItem.getPriority());
            existingItem.setRating(watchlistItem.getRating());
            existingItem.setTitle(watchlistItem.getTitle());
        }
    }
}
