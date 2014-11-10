angular.module("feeds", ["ngRoute"])
    .config(function ($locationProvider) {
        if (window.history && history.pushState) {
            $locationProvider.html5Mode(true);
        }
    })
    /*.config(function ($routeProvider) {
     $routeProvider.when("/google-activities", {
     templateUrl: "/public/partials/googleActivities.html"
     });
     $routeProvider.when("/twitter-feeds", {
     templateUrl: "/public/partials/twitterFeeds.html"
     });
     $routeProvider.otherwise({
     redirectTo: "/google-activities"
     });
     })*/
    .controller("mainCtrl", function ($scope, $location) {
        $scope.util = {};
        $scope.util.feedsType = [
            {type: "google_activities", desc: "Google Activities"},
            {type: "twitter", desc: "Twitter"}
        ];

        $scope.data = {};
        $scope.data.savedFeeds = [];

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
        }
    })
