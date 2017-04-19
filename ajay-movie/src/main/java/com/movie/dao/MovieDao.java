package com.movie.dao;

import java.util.List;

import com.movie.vo.Movie;
import com.movie.vo.MovieUserMatrix;

public interface MovieDao {
	
	int addMovieLikeByTheUser(long userId,long movieId);
	
	int addMovieDisLikeByTheUser(long userId,long movieId);
	
	List<Long> getUsersWhoLikeTheMovie(long movieId);
	
	List<Long> getUsersWhoDisLikeTheMovie(long movieId);
	
	List<Long> getMoviesLikeByTheUser(long userId);
	
	List<Long> getMoviesDisLikeByTheUser(long userId);

	List<Long> getMoviesNotRatedByTheUsers(long userId);

	List<Long> getMovies();

	int updateMovieRating(MovieUserMatrix matrix);


}
