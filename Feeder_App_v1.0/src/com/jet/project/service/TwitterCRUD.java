package com.jet.project.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jet.project.dao.GenericDAO;
import com.jet.project.entity.TwitterEntity;

public class TwitterCRUD {
	TwitterEntity twitterEntity = new TwitterEntity();
	
	public TwitterCRUD(JSONObject entity){
		twitterEntity.consumeJSON(entity);
	}
	
	public TwitterCRUD(){}
	
	public JSONArray getAll(){
		List<TwitterEntity> entities = GenericDAO.INSTANCE.getAll(TwitterEntity.class);
		JSONArray jsonArray = twitterEntity.convertListToJSONArray(entities);
		
		return jsonArray;
	}
	
	public void save(){
		GenericDAO.INSTANCE.save(twitterEntity);
	}
	
	public void remove(){
		GenericDAO.INSTANCE.remove(TwitterEntity.class, twitterEntity.getId());
	}
	
	
}
