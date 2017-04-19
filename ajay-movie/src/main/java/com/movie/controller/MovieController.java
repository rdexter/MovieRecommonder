package com.movie.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movie.service.MovieService;
import com.movie.vo.MovieUserMatrix;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@RestController
@RequestMapping("/api")
public class MovieController {
	/** The logger. */
	private static final Logger LOGGER = Logger.getLogger(MovieController.class);
	
	@Autowired
	MovieService service;
	
	@RequestMapping(value = "/updateMovieRating", method = RequestMethod.POST)
	@ResponseBody
	public String updateMovieRating(@RequestBody String jsonString){
		long start = System.currentTimeMillis();
		String apiName="updateMovieRating";
		LOGGER.info("Entering into updateMovieRating : " + jsonString);
		JSONObject respJson=new JSONObject();
		JSONObject reqJson=(JSONObject) JSONSerializer.toJSON(jsonString);
		MovieUserMatrix matrix= new MovieUserMatrix();
		boolean isValidReqJson=validateJson(reqJson,matrix);
		
		if(isValidReqJson){
				int isUpdatedSuccess=service.updateMovieRating(matrix);
				respJson.put("isUpdatedSuccess", isUpdatedSuccess);
		}
		else{
			respJson.put("Error", "invalidJson");
		}
		logProcessingTime(apiName, start);
		return respJson.toString();
	}
	
	private boolean validateJson(JSONObject reqJson,MovieUserMatrix matrix) {
		if(reqJson.has("userId")){
			matrix.setUserId(reqJson.getLong("userId"));
		}
		else{
			return false;
		}
		if(reqJson.has("movieId")){
			matrix.setMovieId(reqJson.getLong("movieId"));
		}
		else{
			return false;
		}
		if(reqJson.has("rating")){
			matrix.setRating(reqJson.getInt("rating"));
		}
		else{
			return false;
		}
		if(reqJson.has("like")){
			matrix.setUserId(reqJson.getLong("like"));
		}
		return true;
	}

	@RequestMapping(value = "/getUserRecommondation", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getUserRecommondation(@RequestParam("userId") long userId){
		long start = System.currentTimeMillis();
		LOGGER.info("Entering into getUserRecommondation : " + userId);
		JSONObject respJson=new JSONObject();
		String apiName="getUserRecommondation";
		List<Long> movieIds=service.getRecommondation(userId);
		respJson.put("Recommonded MovieIds", movieIds);
		logProcessingTime(apiName, start);
		return respJson;
	}
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject home(){
		long start = System.currentTimeMillis();
		String apiName="home";
		
		System.out.println("REQUEST home : ");
		LOGGER.info("REQUEST home : ");
		JSONObject response=new JSONObject();
		response.put("home", "home");
		response.put("previousRating", "3");
		response.put("newRating", "5");
		response.put("userId", "1212");
		response.put("movieId", "43");
		logProcessingTime(apiName, start);
		return response;
	}
	@RequestMapping(value = "/addMovieLikeByTheUser", method = RequestMethod.GET)
	@ResponseBody
	public int addMovieLikeByTheUser(){
		long start = System.currentTimeMillis();
		String apiName="addMovieLikeByTheUser";
		
		System.out.println("REQUEST home : ");
		long userId=0l;
		long movieId=0l;
		int response=service.addMovieLikeByTheUser(userId, movieId);
		logProcessingTime(apiName, start);
		return response;
	}
	private void logProcessingTime(String api, long start) {
		System.out.println("Processing time : " + api + " : " + (System.currentTimeMillis() - start) + " ms");
		LOGGER.info("Processing time : " + api + " : " + (System.currentTimeMillis() - start) + " ms");
	}
}
