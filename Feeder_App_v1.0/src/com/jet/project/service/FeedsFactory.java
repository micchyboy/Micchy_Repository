package com.jet.project.service;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import com.project.enums.FeedsType;

public class FeedsFactory {
	public static JSONArray getFeeds(FeedsType type) throws ParseException, IOException{
		Feeds feed = null;
		switch(type){
			case GOOGLE_ACTIVITIES:{
				feed = new GoogleActivitiesFeeds();
				break;
			}
			case TWITTER:{
				feed = new TwitterFeeds();
				break;
			}
			default:{
				throw new RuntimeException("No such feeds!");
			}
		}


		return feed.getFeeds();
	}
}
