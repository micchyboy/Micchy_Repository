angular.module("feeds")
    .constant("googleActivitiesUrl", "http://localhost:8888/_ah/api/feeds/v1/google_activity_feeds")
    .constant("twitterFeedsUrl", "http://localhost:8888/_ah/api/feeds/v1/twitter_feeds")
//    .constant("googleActivitiesUrl", "https://site-feeder.appspot.com/_ah/api/feeds/v1/google_activity_feeds")
//    .constant("twitterFeedsUrl", "https://site-feeder.appspot.com/_ah/api/feeds/v1/twitter_feeds")
    .service("feedsService", function ($http, googleActivitiesUrl, twitterFeedsUrl) {
        return {
            getGoogleActivityFeeds: function(){
                return $http({ method: "GET", url: googleActivitiesUrl})
            },
            getTwitterFeeds: function(){
                return $http({ method: "GET", url: twitterFeedsUrl})
            }
        }
    })