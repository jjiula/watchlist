package com.openclassrooms.watchlist;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.repository.WatchlistRepository;
import com.openclassrooms.watchlist.service.MovieRatingServiceImpl;
import com.openclassrooms.watchlist.service.WatchlistService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class WatchlistServiceTest {

    @Mock
    private WatchlistRepository watchlistRepositoryMock;

    @Mock
    private MovieRatingServiceImpl movieRatingServiceImplMock;

    @InjectMocks
    private WatchlistService watchlistService;

    @Test
    public void testGetWatchlistItemsReturnsAllItemsFromRepository() {

        //Arrange
        WatchlistItem item1 = new WatchlistItem("Star Wars", "6.7", "M", "", 1);
        WatchlistItem item2 = new WatchlistItem("Star Trek", "5.7", "M", "", 2);
        List<WatchlistItem> mockItems = Arrays.asList(item1, item2);

        Mockito.when(watchlistRepositoryMock.getList()).thenReturn(mockItems);

        //Act
        List<WatchlistItem> result = watchlistService.getWatchlistItems();

        //Assert
        assertTrue(result.size()==2);
        assertTrue(result.get(0).getTitle().equals("Star Wars"));
        assertTrue(result.get(0).getTitle().equals("Star Trek"));
    }

    @Test
    public void testGetWatchlistItemsRatingFormOmdbServiceOverrideTheValueInItems() {
        //Arrange
        WatchlistItem item1 = new WatchlistItem("Star Wars", "6.7", "M", "", 1);
        List<WatchlistItem> mockItems = Arrays.asList(item1);

        Mockito.when(watchlistRepositoryMock.getList()).thenReturn(mockItems);
        Mockito.when(movieRatingServiceImplMock.getMovieRating(any(String.class))).thenReturn("10");

        //Act
        List<WatchlistItem> result = watchlistService.getWatchlistItems();

        //Assert
        assertTrue(result.size()==2);
        assertTrue(result.get(0).getRating().equals("10"));
    }
}
