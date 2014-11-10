package com.jet.project.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.jet.project.dao.FeedsDAO;
import com.jet.project.entity.GoogleActivitiesEntity;

public class GoogleActivitiesCRUD {
	GoogleActivitiesEntity gActEntity = new GoogleActivitiesEntity();
	
	public GoogleActivitiesCRUD(JSONObject entity){
		if(entity.get("id") != null){
			gActEntity.setId(Long.valueOf((String) entity.get("id")));
		}
		gActEntity.setDatePosted((String) entity.get("datePosted"));
		gActEntity.setMediaAttachment((ArrayList) entity.get("mediaAttachments"));
		gActEntity.setMessage((String) entity.get("message"));
		gActEntity.setPostId((String) entity.get("postId"));
		gActEntity.setProfileImageUrl((String) entity.get("profileImageUrl"));
	}
	
	public void getAll(){
		FeedsDAO.INSTANCE.getAll(GoogleActivitiesEntity.class);
	}
	
	public void save(){
		FeedsDAO.INSTANCE.save(gActEntity);
	}
	
	public void remove(){
		FeedsDAO.INSTANCE.remove(GoogleActivitiesEntity.class, gActEntity.getId());
	}
	
	
}
