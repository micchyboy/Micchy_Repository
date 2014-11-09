angular.module("feeds")
    .controller("googleActCtrl", function($scope, feedsService){
        $scope.getGoogleActivityFeeds = function(){
            feedsService.getGoogleActivityFeeds().then(function(result){
                $scope.data.feeds = result.data.items[0].items;
            }).catch(function(error){
                $scope.error = error;
            })
        }

        $scope.getGoogleActivityFeeds();
    })
    .controller("twitterCtrl", function($scope, feedsService){
        $scope.getTwitterFeeds = function(){
            feedsService.getTwitterFeeds().then(function(result){
                $scope.data.feeds = result.data.items;
            }).catch(function(error){
                $scope.error = error;
            })
        }

        $scope.getTwitterFeeds();
    })