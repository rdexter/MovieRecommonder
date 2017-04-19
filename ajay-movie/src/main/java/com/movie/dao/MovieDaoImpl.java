package com.movie.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.movie.controller.MovieController;
import com.movie.vo.Movie;
import com.movie.vo.MovieUserMatrix;


@Repository
public class MovieDaoImpl implements MovieDao {
	/** The logger. */
	private static final Logger LOGGER = Logger.getLogger(MovieDaoImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int addMovieLikeByTheUser(long userId, long movieId) {
		String sql="UPDATE rating SET ratings=?";
		Long actionTaken=3457000024l;
		Long status=9376503l;
		return jdbcTemplate.queryForInt(sql,actionTaken,status);
	}

	public int addMovieDisLikeByTheUser(long userId, long movieId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Long> getUsersWhoLikeTheMovie(long movieId) {
		LOGGER.info("Entering into getUsersWhoLikeTheMovie .. "+movieId);
		try{
			//String sql="select movieId from movie_user_matrix where userId=? and ";
			String sql="select user_id from rating where movie_id=? and ratings>=3";
			List<Long> movieIds=new ArrayList<Long>();
			List<Map<String,Object>> resultSet=jdbcTemplate.queryForList(sql, movieId);
			for (Map<String, Object> map : resultSet) {
				//Long id=Long.valueOf(map.get("movieId").toString());
				Long id=Long.valueOf(map.get("user_id").toString());
				movieIds.add(id);
			}
			return movieIds;
		}catch(Exception e){
			LOGGER.info("Exception occured..."+e);
		}
		return null;
	}

	public List<Long> getUsersWhoDisLikeTheMovie(long movieId) {
		LOGGER.info("Entering into getUsersWhoDisLikeTheMovie .. "+movieId);
		try{
			//String sql="select movieId from movie_user_matrix where userId=? and ";
			String sql="select user_id from rating where movie_id=? and ratings<3 and ratings>0";
			List<Long> movieIds=new ArrayList<Long>();
			List<Map<String,Object>> resultSet=jdbcTemplate.queryForList(sql, movieId);
			for (Map<String, Object> map : resultSet) {
				//Long id=Long.valueOf(map.get("movieId").toString());
				Long id=Long.valueOf(map.get("user_id").toString());
				movieIds.add(id);
			}
			return movieIds;
		}catch(Exception e){
			LOGGER.info("Exception occured..."+e);
		}
		return null;
	}

	public List<Long> getMoviesLikeByTheUser(long userId) {
		LOGGER.info("Entering into getMoviesLikeByTheUser .. "+userId);
		try{
			//String sql="select movieId from movie_user_matrix where userId=? and ";
			String sql="select movie_id from rating where user_id=? and ratings>=3";
			List<Long> movieIds=new ArrayList<Long>();
			List<Map<String,Object>> resultSet=jdbcTemplate.queryForList(sql, userId);
			for (Map<String, Object> map : resultSet) {
				//Long id=Long.valueOf(map.get("movieId").toString());
				Long id=Long.valueOf(map.get("movie_id").toString());
				movieIds.add(id);
			}
			return movieIds;
		}catch(Exception e){
			LOGGER.info("Exception occured..."+e);
		}
		
		return null;
	}

	public List<Long> getMoviesDisLikeByTheUser(long userId) {
		LOGGER.info("Entering into getMoviesDisLikeByTheUser .. "+userId);
		try{
			//String sql="select movieId from movie_user_matrix where userId=? and ";
			String sql="select movie_id from rating where user_id=? and ratings<3 and ratings>0";
			List<Long> movieIds=new ArrayList<Long>();
			List<Map<String,Object>> resultSet=jdbcTemplate.queryForList(sql, userId);
			for (Map<String, Object> map : resultSet) {
				//Long id=Long.valueOf(map.get("movieId").toString());
				Long id=Long.valueOf(map.get("movie_id").toString());
				movieIds.add(id);
			}
			return movieIds;
		}catch(Exception e){
			LOGGER.info("Exception occured..."+e);
		}
		
		return null;
	}

	public List<Long> getMoviesNotRatedByTheUsers(long userId) {
		LOGGER.info("Entering into getMoviesNotRatedByTheUsers .. "+userId);
		try{
			//String sql="select movieId from movie_user_matrix where userId=? and ";
			String sql="select movie_id from rating where user_id=? and ratings=0";
			List<Long> movieIds=new ArrayList<Long>();
			List<Map<String,Object>> resultSet=jdbcTemplate.queryForList(sql, userId);
			for (Map<String, Object> map : resultSet) {
				//Long id=Long.valueOf(map.get("movieId").toString());
				Long id=Long.valueOf(map.get("movie_id").toString());
				movieIds.add(id);
			}
			LOGGER.info("movieNotRatedByUser: "+movieIds.size());
			return movieIds;
		}catch(Exception e){
			LOGGER.info("Exception occured..."+e);
		}
		
		return null;
	}

	public List<Long> getMovies() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int updateMovieRating(MovieUserMatrix matrix) {
		LOGGER.info("Entering into updateMovieRating .. "+matrix.toString());
		
		try{
			String updateSql="UPDATE rating set ratings=? where user_id=? and movie_id=?";
			return jdbcTemplate.update(updateSql, matrix.getRating(),matrix.getUserId(),matrix.getMovieId());
		}catch(Exception e){
			LOGGER.info("Exception occured..."+e);
		}
		return 0;
	}
}
