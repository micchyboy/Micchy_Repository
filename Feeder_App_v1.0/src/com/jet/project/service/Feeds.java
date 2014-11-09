package com.jet.project.service;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;



public interface Feeds {
	JSONArray getFeeds() throws ParseException, IOException;
}
