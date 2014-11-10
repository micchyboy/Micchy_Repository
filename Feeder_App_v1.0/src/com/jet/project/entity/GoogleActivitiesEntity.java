package com.jet.project.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.appengine.api.datastore.Blob;
import com.jet.project.util.CustomUtil;
import com.jet.project.util.LogUtil;

@Entity
public class GoogleActivitiesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String postId;
	private Blob mediaAttachment;
	private String profileImageUrl;
	private String message;
	private String datePosted;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}

	public Blob getMediaAttachment() {
		return mediaAttachment;
	}
	public void setMediaAttachment(Blob mediaAttachment) {
		this.mediaAttachment = mediaAttachment;
	}
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDatePosted() {
		return datePosted;
	}
	public void setDatePosted(String datePosted) {
		this.datePosted = datePosted;
	}

	@Transient
	public GoogleActivitiesEntity consumeJSON(JSONObject entity){
//		GoogleActivitiesEntity gActEntity = new GoogleActivitiesEntity();
		
		LogUtil.log("Mapping JSON data to Java object..");

		if(entity.get("id") != null){
			this.setId(Long.valueOf((String) entity.get("id")));
		}
		this.setDatePosted((String) entity.get("datePosted"));
		this.setMediaAttachment(CustomUtil.convertToBlob(entity.get("mediaAttachments")));
		this.setMessage((String) entity.get("message"));
		this.setPostId((String) entity.get("postId"));
		this.setProfileImageUrl((String) entity.get("profileImageUrl"));

		LogUtil.log("Done Mapping.");
		
		return this;
	}
	
	@SuppressWarnings("unchecked")
	@Transient
	public JSONObject convertToJSON(GoogleActivitiesEntity entity){
		JSONObject json = new JSONObject();
//		GoogleActivitiesEntity gActEntity = new GoogleActivitiesEntity();
		
		LogUtil.log("Converting Java object to JSON..");

		json.put("id", entity.getId());
		json.put("datePosted", entity.getDatePosted());
		json.put("mediaAttachments",  CustomUtil.revertBlobToObject(entity.getMediaAttachment()));
		json.put("message", entity.getMessage());
		json.put("postId", entity.getPostId());
		json.put("profileImageUrl", entity.getProfileImageUrl());

		LogUtil.log("Done Converting.");
		
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@Transient
	public JSONArray convertListToJSONArray(List<GoogleActivitiesEntity> entities){
		JSONArray jsonArray = new JSONArray();
		
		LogUtil.log("Converting Java Arraylist to JSON Array..");

		for(GoogleActivitiesEntity g : entities){
			JSONObject json = convertToJSON(g);
			jsonArray.add(json);
		}
		
		LogUtil.log("Done Converting.");
		
		return jsonArray;
	}
}
