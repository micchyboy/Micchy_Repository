package com.google.plus;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterFeed {
	public static void main(String[] args) {
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey("HtmUtOml8IMpzFdtE8AfEmzZC");
		builder.setOAuthConsumerSecret("VPunsRZQrKEnYwktXfPcwvuCQXf81vF1EhFF3EBnqvE87dqElB");
		builder.setOAuthAccessToken("2866226464-dPIeZezump92cF95irCkkHsJ40HsHHoGjoGAmnb");
		builder.setOAuthAccessTokenSecret("5TEjJk9e1fIcg3wTCIDyywM9fBYyhJjADnoziAmmRApGD");
		Configuration configuration = builder.build();
		TwitterFactory factory = new TwitterFactory(configuration);
		Twitter twitter = factory.getInstance();
		
		ResponseList<Status> a;
		try {
			 String user = twitter.verifyCredentials().getScreenName();
//			 a = twitter.getUserTimeline(new Paging().count(200));
			 a = twitter.getUserTimeline(new Paging().count(200));
		
		for(Status b: a){
			System.out.println(b.getText());
		}
		
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
