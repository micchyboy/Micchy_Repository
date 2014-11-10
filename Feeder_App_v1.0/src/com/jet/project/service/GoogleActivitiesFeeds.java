package com.jet.project.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import com.jet.project.util.Config;
import com.jet.project.util.LogUtil;
import com.jet.project.util.UrlUtil;

public class GoogleActivitiesFeeds implements Feeds{

	@Override
	public JSONArray getFeeds() throws ParseException, IOException{
		URL url;

		url = new URL(Config.getProp("google_activities_url"));

		LogUtil.log("Getting feeds from google activities...");
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		return UrlUtil.consumeJSONResponse(conn);
		



	}

}
