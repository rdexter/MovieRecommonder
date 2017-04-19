package com.movie.vo;

public class MovieUserMatrix {
	private long userId;
	private long movieId;
	private Integer rating;
	private Integer like;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getMovieId() {
		return movieId;
	}
	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
		if(rating>=3){
			like=1;
		}
		else{
			like=0;
		}
	}
	public Integer getLike() {
		return like;
	}
	public void setLike(Integer like) {
		this.like = like;
	}
	@Override
	public String toString() {
		return "MovieUserMatrix [userId=" + userId + ", movieId=" + movieId + ", rating=" + rating + ", like=" + like + "]";
	}
	
}
