package com.jet.project.endpoints;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.InternalServerErrorException;
import com.jet.project.service.TwitterCRUD;

@Api(name = "feedsCrud")
public class TwitterEndpoint {
	private TwitterCRUD crud;

	@ApiMethod(name = "twitter.getAll", path = "twitter", httpMethod = HttpMethod.GET)
	public JSONArray getTwitterFeeds() throws Exception{
		JSONArray feeds = new JSONArray();
		crud = new TwitterCRUD();
		try {
			feeds = crud.getAll();

		} catch (Exception e) {

			handleErrors(e);
		}

		return feeds;
	}

	@ApiMethod(name = "twitter.save", path = "twitter", httpMethod = HttpMethod.POST)
	public void saveTwitterFeed(JSONObject data) throws Exception{
		crud = new TwitterCRUD(data);

		try {
			crud.save();
		} catch (Exception e) {
			handleErrors(e);
		}
	}

	@ApiMethod(name = "twitter.delete", path = "twitter_delete", httpMethod = HttpMethod.POST)
	public void deleteTwitterFeed(JSONObject data) throws Exception{
		crud = new TwitterCRUD(data);
		try {
			crud.remove();
		} catch (Exception e) {

			handleErrors(e);
		}

	}

	private void handleErrors(Exception e) throws Exception{
		e.printStackTrace();
		throw new InternalServerErrorException("Something is wrong with our server. Please try again later");
	}
}
