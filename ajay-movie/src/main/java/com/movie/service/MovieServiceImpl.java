package com.movie.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.dao.MovieDao;
import com.movie.utils.MovieUtils;
import com.movie.vo.MovieUserMatrix;
import com.movie.vo.User;

@Service
public class MovieServiceImpl implements MovieService {
	/** The logger. */
	private static final Logger LOGGER = Logger.getLogger(MovieServiceImpl.class);
	@Autowired
	private MovieDao dao;

	public void setDao(MovieDao dao) {
		this.dao = dao;
	}

	/*
	 * Sim(U1,U2)=[int(L1,L2)+int(D1,D2)-int(L1,D2)-int(L2,D1)]/[union(L1,L2,D1,D2)]
	 * Sim(U1,U2)=[A+B-C-D]/E
	 */
	private double calculateSimilarity(User user1, long userId2) {
		List<Long> L1=null;
		List<Long> D1=null;
		if(user1.getListOfMoviesLikedByTheUser()==null){
			L1=dao.getMoviesLikeByTheUser(user1.getUserId());
			user1.setListOfMoviesLikedByTheUser(L1);
			LOGGER.info("MovieLiked By the user: "+user1.getUserId()+" are: "+user1.getListOfMoviesLikedByTheUser());
		}
		else{
			L1=user1.getListOfMoviesLikedByTheUser();
		}
		if(user1.getListOfMoviesDisLikedByTheUser()==null){
			D1=dao.getMoviesDisLikeByTheUser(user1.getUserId());
			user1.setListOfMoviesDisLikedByTheUser(D1);
			LOGGER.info("MovieDisLiked By the user: "+user1.getUserId()+" are: "+user1.getListOfMoviesDisLikedByTheUser());
		}
		else{
			D1=user1.getListOfMoviesDisLikedByTheUser();
		}
		
		List<Long> L2=dao.getMoviesLikeByTheUser(userId2);
		List<Long> D2=dao.getMoviesDisLikeByTheUser(userId2);

		List<Long>A=MovieUtils.intersection(L1, L2);
		List<Long>B=MovieUtils.intersection(D1, D2);
		List<Long>C=MovieUtils.intersection(L1, D2);
		List<Long>D=MovieUtils.intersection(L2, D1);

		List<Long>u1=MovieUtils.union(L1, L2);
		List<Long>u2=MovieUtils.union(D1, D2);
		List<Long>E=MovieUtils.union(u1, u2);

		int a=A.size();
		int b=B.size();
		int c=C.size();
		int d=D.size();
		int e=E.size();

		Double sim =(Double.valueOf(a+b-c-d))/e;
		return sim;
	}

	/*
	 * P(U,M) = (ZL-ZD)/(ML+MD)
	 * P(U,M) = is the possibility of a user U liking the movie M
	 * ZL and ZD are the sum of the similarity indices of user U with all the users 
	 * who have liked or disliked the movie M, respectively.
	 * |ML|+|MD| represents the total number of users who have liked or disliked the movie M
	 */

	private double  getProbability(User u1,long movieId){
		List<Long> ML=dao.getUsersWhoLikeTheMovie(movieId);
		List<Long> MD=dao.getUsersWhoDisLikeTheMovie(movieId);
		
		Double ZL=calculateZ(u1, ML);
		Double ZD=calculateZ(u1, MD);

		List<Long> ML_union_MD= MovieUtils.union(ML, MD);

		int MLUML=ML_union_MD.size();//MLUML means |ML|+|MD|

		Double p=(ZL-ZD)/MLUML; //p=P(U,M)

		return p;
	}

	private Double  calculateZ(User u1,List<Long>userIds){
		Double Z=(double) 0;
		for (Long user: userIds) {
			Z=Z+calculateSimilarity(u1, user);
		}
		return Z;
	}

	public List<Long> getRecommondation(long userId){
		LOGGER.info("Entering into getRecommondation..."+userId);
		
		List<Long> movieIds=dao.getMoviesNotRatedByTheUsers(userId);
		
		User u1=new User();
		u1.setUserId(userId);
		LOGGER.info("movieIds..."+movieIds);
		Map<Long,Double> listOfProbabilties= new HashMap<Long,Double>();
		for (Long movieId : movieIds) {
			Double probabiltyOfUserLikeThisMovie=getProbability(u1, movieId);
			listOfProbabilties.put(movieId, probabiltyOfUserLikeThisMovie);	
		}
		//LOGGER.info("listOfProbabilties..."+listOfProbabilties);
		MovieUtils.printMap(listOfProbabilties);
		
		Map<Long,Double> sortedListOfProbabilties=MovieUtils.sortByValue(listOfProbabilties);
		List<Long> sortedMovieIds=new ArrayList<Long>();
		for(Map.Entry<Long, Double> entry:sortedListOfProbabilties.entrySet()){
			sortedMovieIds.add(entry.getKey());
		}
		LOGGER.info("sortedMovieIds..."+sortedMovieIds);
		return sortedMovieIds;
	}

	public int addMovieLikeByTheUser(long userId,long movieId){
		return dao.addMovieLikeByTheUser(userId, movieId);
	}

	public int addMovieDisLikeByTheUser(long userId,long movieId) {
		return dao.addMovieDisLikeByTheUser(userId, movieId);
	}
	public List<Long> getMovies() {
		return dao.getMovies();
	}
	public int updateMovieRating(MovieUserMatrix matrix) {
		return dao.updateMovieRating(matrix);
	}

}
