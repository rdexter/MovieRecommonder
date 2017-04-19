package com.movie.service;

import java.util.List;

import com.movie.vo.MovieUserMatrix;

public interface MovieService {
	
	List<Long> getRecommondation(long userId);
	
	int addMovieDisLikeByTheUser(long userId,long movieId);
	
	int addMovieLikeByTheUser(long userId,long movieId);
	
	List<Long> getMovies();

	int updateMovieRating(MovieUserMatrix matrix);

}
