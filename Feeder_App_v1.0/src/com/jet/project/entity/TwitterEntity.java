package com.jet.project.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TwitterEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String postId;
	private List<String> mediaUrlList;
	private String profileImageUrl;
	private String message;
	private Date datePosted;
}
