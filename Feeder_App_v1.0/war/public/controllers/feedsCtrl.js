angular.module("feeds")
    .controller("googleActCtrl", function ($scope, feedsService, gactCRUD) {
        $scope.data.gActFeeds = [];
        $scope.data.gActSavedFeeds = [];

        $scope.util.googleSort = [
            {criteria: "Date+", value: "-published"},
            {criteria: "Date-", value: "published"},
            {criteria: "Content+", value: "title"},
            {criteria: "Content-", value: "-title"}
        ];

        $scope.searchData = {};
        $scope.searchData.criteria = "-published";

        $scope.getGoogleActivityFeeds = function () {
            feedsService.getGoogleActivityFeeds().then(function (result) {
                $scope.data.gActFeeds = result.data.items[0].items;
            }).catch(function (error) {
                $scope.error = error;
            })
        };

        $scope.getAllSavedFeeds = function () {
            gactCRUD.getAll()
                .success(function (data) {
                    $scope.data.gActSavedFeeds = data;
                })
                .error(function(error){
                    $scope.error = error;
                })
        };

        $scope.getGoogleActivityFeeds();
//        $scope.getAllSavedFeeds();
        $scope.save = function(item){
            var model = {
                postId: item.id,
                mediaAttachments: item.object.attachments,
                profileImageUrl: item.actor.image.url,
                message: item.title,
                datePosted: item.published
            }
            gactCRUD.save(model)
                .success(function(){
                    console.log("Data is successfully saved")
                })
                .error(function(error){
                    console.log(error);
                });
        }

    })
    .controller("twitterCtrl", function ($scope, feedsService) {
        $scope.data.twitterFeeds = [];
        $scope.util.twitterSort = [
            {criteria: "Date+", value: "-created_at"},
            {criteria: "Date-", value: "created_at"},
            {criteria: "Content+", value: "text"},
            {criteria: "Content-", value: "-text"}
        ];

        $scope.searchData = {};
        $scope.searchData.criteria = "-created_at";

        $scope.getTwitterFeeds = function () {
            feedsService.getTwitterFeeds().then(function (result) {
                $scope.data.twitterFeeds = result.data.items;
            }).catch(function (error) {
                $scope.error = error;
            })
        }

        $scope.getTwitterFeeds();
    })