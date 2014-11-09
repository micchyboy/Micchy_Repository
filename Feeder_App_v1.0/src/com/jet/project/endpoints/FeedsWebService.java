package com.jet.project.endpoints;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.InternalServerErrorException;
import com.google.api.server.spi.response.ServiceUnavailableException;
import com.jet.project.service.Feeds;
import com.jet.project.service.FeedsFactory;
import com.project.enums.FeedsType;

@Api(name = "feeds")
public class FeedsWebService {
	private Feeds feeds;
	@ApiMethod(name = "google.activities", path = "google_activity_feeds")
	public JSONArray getGoogleActivitiesFeeds() throws Exception{
		JSONArray feeds = new JSONArray();
		try {
			feeds = FeedsFactory.getFeeds(FeedsType.GOOGLE_ACTIVITIES);
		
		} catch (Exception e) {
			handleErrors(e);
		}
		return feeds;
	}
	
	@ApiMethod(name = "twitter", path = "twitter_feeds")
	public JSONArray getTwitterFeeds() throws Exception{
		JSONArray feeds = new JSONArray();
		try {
			feeds = FeedsFactory.getFeeds(FeedsType.TWITTER);
		
		
		} catch (Exception e) {
			
			handleErrors(e);
		}
		
		return feeds;
	}
	
	private void handleErrors(Exception e) throws Exception{
		if(e instanceof ParseException){
			throw new ServiceUnavailableException("Something is wrong with google activities service");
		}
		else{
			throw new InternalServerErrorException("Something is wrong with our server. Please try again later");
		}
	}
}
