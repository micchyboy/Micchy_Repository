angular.module("feeds")
    .constant("googleActivitiesCRUDUrl", "http://localhost:8888/_ah/api/feedsCrud/v1/gact")
    .service("gactCRUD", function($http, googleActivitiesCRUDUrl){
        return {
            getAll: function(){
                return $http({ method: "GET", url: googleActivitiesCRUDUrl})
            },
            save: function(data){
                return $http({ method: "POST", url: googleActivitiesCRUDUrl, data: data})
            },
            delete: function(){
                return $http({ method: "DELETE", url: googleActivitiesCRUDUrl})
            }
        }
    })
