angular.module("feeds")
    .controller("googleActCtrl", function($scope, feedsService){
        $scope.data.gActFeeds = [];
        $scope.util.googleSort = [
            {criteria: "Date+", value: "-published"},
            {criteria: "Date-", value: "published"},
            {criteria: "Content+", value: "title"},
            {criteria: "Content-", value: "-title"}
        ];

        $scope.searchData = {};
        $scope.searchData.criteria = "-published";

        $scope.getGoogleActivityFeeds = function(){
            feedsService.getGoogleActivityFeeds().then(function(result){
                $scope.data.gActFeeds = result.data.items[0].items;
            }).catch(function(error){
                $scope.error = error;
            })
        }

        $scope.getGoogleActivityFeeds();
    })
    .controller("twitterCtrl", function($scope, feedsService){
        $scope.data.twitterFeeds = [];
        $scope.util.twitterSort = [
            {criteria: "Date+", value: "-created_at"},
            {criteria: "Date-", value: "created_at"},
            {criteria: "Content+", value: "text"},
            {criteria: "Content-", value: "-text"}
        ];

        $scope.searchData = {};
        $scope.searchData.criteria = "-created_at";

        $scope.getTwitterFeeds = function(){
            feedsService.getTwitterFeeds().then(function(result){
                $scope.data.twitterFeeds = result.data.items;
            }).catch(function(error){
                $scope.error = error;
            })
        }

        $scope.getTwitterFeeds();
    })