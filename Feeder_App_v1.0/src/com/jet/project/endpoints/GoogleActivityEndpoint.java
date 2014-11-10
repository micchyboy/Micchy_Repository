package com.jet.project.endpoints;

import org.json.simple.JSONObject;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.InternalServerErrorException;
import com.jet.project.service.GoogleActivitiesCRUD;

@Api(name = "feedsCrud")
public class GoogleActivityEndpoint {
	private GoogleActivitiesCRUD crud;

	@ApiMethod(name = "google.activities.getAll", path = "gact", httpMethod = HttpMethod.GET)
	public JSONObject getGoogleActivityFeeds() throws Exception{
		JSONObject feeds = new JSONObject();
		try {
			System.out.println("Get google activities!");


		} catch (Exception e) {

			handleErrors(e);
		}

		return feeds;
	}

	@ApiMethod(name = "google.activities.save", path = "gact", httpMethod = HttpMethod.POST)
	public void saveGoogleActivityFeed(JSONObject data) throws Exception{
		crud = new GoogleActivitiesCRUD(data);

		try {
			crud.save();
		} catch (Exception e) {
			handleErrors(e);
		}
	}

	@ApiMethod(name = "google.activities.delete", path = "gact", httpMethod = HttpMethod.DELETE)
	public JSONObject deleteGoogleActivityFeed() throws Exception{
		JSONObject feeds = new JSONObject();
		try {
			System.out.println("Delete google activity!");


		} catch (Exception e) {

			handleErrors(e);
		}

		return feeds;
	}

	private void handleErrors(Exception e) throws Exception{
		e.printStackTrace();
		throw new InternalServerErrorException("Something is wrong with our server. Please try again later");
	}
}