package com.jet.project.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jet.project.dao.FeedsDAO;
import com.jet.project.entity.GoogleActivitiesEntity;

public class GoogleActivitiesCRUD {
	GoogleActivitiesEntity gActEntity = new GoogleActivitiesEntity();
	
	public GoogleActivitiesCRUD(JSONObject entity){
		gActEntity.consumeJSON(entity);
	}
	
	public GoogleActivitiesCRUD(){}
	
	public JSONArray getAll(){
		List<GoogleActivitiesEntity> entities = FeedsDAO.INSTANCE.getAll(GoogleActivitiesEntity.class);
		JSONArray jsonArray = gActEntity.convertListToJSONArray(entities);
		
		return jsonArray;
	}
	
	public void save(){
		FeedsDAO.INSTANCE.save(gActEntity);
	}
	
	public void remove(){
		FeedsDAO.INSTANCE.remove(GoogleActivitiesEntity.class, gActEntity.getId());
	}
	
	
}
