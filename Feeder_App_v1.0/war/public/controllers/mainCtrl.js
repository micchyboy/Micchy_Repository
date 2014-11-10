angular.module("feeds", ["ngRoute"])
    .config(function ($locationProvider) {
        if (window.history && history.pushState) {
            $locationProvider.html5Mode(true);
        }
    })
    .controller("mainCtrl", function ($scope, $location) {
        $scope.util = {};
        $scope.util.feedsType = [
            {type: "google_activities", desc: "Google Activities"},
            {type: "twitter", desc: "Twitter"}
        ];

        $scope.data = {};

        $scope.error = "";

        $scope.showFeeds = function (type) {
            if (type == "google") {
                $scope.redirectTo("/google-activities")
            }
            else if (type == "twitter") {
                $scope.redirectTo("/twitter-feeds");
            }
        }

        $scope.redirectTo = function (path) {
            $location.path(path);
        };

        $scope.startLoadingImage = function(){
            this.loading = true;
        };

        $scope.endLoadingImage = function(){
            this.loading = false;
        };
    });
