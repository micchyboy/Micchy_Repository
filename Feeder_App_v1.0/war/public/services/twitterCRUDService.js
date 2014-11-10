angular.module("feeds")
//    .constant("twitterCRUDUrl", "http://localhost:8888/_ah/api/feedsCrud/v1/twitter")
    .constant("twitterCRUDUrl", "https://site-feeder.appspot.com/_ah/api/feedsCrud/v1/twitter")
    .service("twitterCRUD", function($http, twitterCRUDUrl){
        return {
            getAll: function(){
                return $http({ method: "GET", url: twitterCRUDUrl})
            },
            save: function(data){
                return $http({ method: "POST", url: twitterCRUDUrl, data: data})
            },
            delete: function(data){
                return $http({ method: "POST", url: twitterCRUDUrl + "_delete", data: data})
            }
        }
    })
