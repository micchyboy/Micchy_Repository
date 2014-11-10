angular.module("feeds")
    .controller("googleActCtrl", function ($scope, feedsService, gactCRUD) {
        $scope.data.gActFeeds = [];
        $scope.data.gActSavedFeeds = [];

        $scope.util.googleSort = [
            {criteria: "Date+", value: "-published", saved: "-datePosted"},
            {criteria: "Date-", value: "published", saved: "datePosted"},
            {criteria: "Content+", value: "title", saved: "message"},
            {criteria: "Content-", value: "-title", saved: "-message"}
        ];

        $scope.searchData = {};
        $scope.searchData.criteria = "-published";
        $scope.searchData.savedCriteria = "-datePosted";


        $scope.getGoogleActivityFeeds = function () {
            $scope.startLoadingImage();
            feedsService.getGoogleActivityFeeds().then(function (result) {
                $scope.data.gActFeeds = result.data.items[0].items;
            }).catch(function (error) {
                $scope.error = error;
            }).finally(function () {
                $scope.endLoadingImage();
            })
        };

        $scope.getAllSavedFeeds = function () {
            $scope.startLoadingImage();
            gactCRUD.getAll()
                .success(function (data) {
                    $scope.data.gActSavedFeeds = data.items;
                })
                .error(function (error) {
                    $scope.error = error.message;
                })
                .finally(function () {
                    $scope.endLoadingImage();
                })
        };

        $scope.save = function (item) {
            item.saving = true;
            var model = {
                postId: item.id,
                mediaAttachments: item.object.attachments,
                profileImageUrl: item.actor.image.url,
                message: item.title,
                datePosted: item.published
            }
            gactCRUD.save(model)
                .success(function () {
                    console.log("Data is successfully saved");
                    item.saved = true;
                })
                .error(function (error) {
                    $scope.error = error.message;
                })
                .finally(function () {
                    item.saving = false;
                });
        }

        $scope.remove = function (item) {
            var model = {
                id: item.id
            };

            gactCRUD.delete(model)
                .success(function () {
                    $scope.data.gActSavedFeeds.splice($scope.data.gActSavedFeeds.indexOf(item), 1);
                    console.log("Feed is successfully removed");
                })
                .error(function (error) {
                    $scope.error = error.message;
                });
        }


        $scope.initialLoad = function () {
            $scope.startLoadingImage();
            gactCRUD.getAll()
                .then(function (result) {
                    $scope.data.gActSavedFeeds = result.data.items;
                })
                .then(function () {
                    $scope.getGoogleActivityFeeds();
                })
                .catch(function (error) {
                    $scope.error = error.message;
                })
        }

        $scope.isSaved = function (item) {
            $scope.savedItems = $scope.data.gActSavedFeeds.filter(function (savedItem) {
                if (item.id == savedItem.postId) {
                    item.saved = true;
                    return true;
                }
            });
        }

        $scope.initialLoad();

        $scope.reload = function(){
            $scope.initialLoad();
        }

    })
    .controller("twitterCtrl", function ($scope, feedsService, twitterCRUD) {
        $scope.data.twitterFeeds = [];
        $scope.data.twitterSavedFeeds = [];

        $scope.util.twitterSort = [
            {criteria: "Date+", value: "-created_at", saved: "-datePosted"},
            {criteria: "Date-", value: "created_at", saved: "datePosted"},
            {criteria: "Content+", value: "text", saved: "message"},
            {criteria: "Content-", value: "-text", saved: "-message"}
        ];

        $scope.searchData = {};
        $scope.searchData.criteria = "-created_at";
        $scope.searchData.savedCriteria = "-datePosted";

        $scope.getTwitterFeeds = function () {
            $scope.startLoadingImage();
            feedsService.getTwitterFeeds().then(function (result) {
                $scope.data.twitterFeeds = result.data.items;
            }).catch(function (error) {
                $scope.error = error;
            }).finally(function () {
                $scope.endLoadingImage();
            })
        }


        $scope.getAllSavedFeeds = function () {
            $scope.startLoadingImage();
            twitterCRUD.getAll()
                .success(function (data) {
                    $scope.data.twitterSavedFeeds = data.items;
                })
                .error(function (error) {
                    $scope.error = error.message;
                })
                .finally(function () {
                    $scope.endLoadingImage();
                })
        };

        $scope.save = function (item) {
            item.saving = true;
            var model = {
                postId: item.id,
                mediaAttachments: item.extended_entities ? item.extended_entities.media : "",
                profileImageUrl: item.user.profile_image_url,
                message: item.text,
                datePosted: item.created_at
            }
            twitterCRUD.save(model)
                .success(function () {
                    console.log("Data is successfully saved")
                    item.saved = true;
                })
                .error(function (error) {
                    $scope.error = error.message;
                })
                .finally(function () {
                    item.saving = false;
                });
        }

        $scope.remove = function (item) {
            var model = {
                id: item.id
            };

            twitterCRUD.delete(model)
                .success(function () {
                    $scope.data.twitterSavedFeeds.splice($scope.data.twitterSavedFeeds.indexOf(item), 1);
                    console.log("Feed is successfully removed");
                })
                .error(function (error) {
                    $scope.error = error.message;
                });
        }

        $scope.initialLoad = function () {
            $scope.startLoadingImage();
            twitterCRUD.getAll()
                .then(function (result) {
                    $scope.data.twitterSavedFeeds = result.data.items;
                })
                .then(function () {
                    $scope.getTwitterFeeds();
                })
                .catch(function (error) {
                    $scope.error = error.message;
                })
        }

        $scope.isSaved = function (item) {
            $scope.savedItems = $scope.data.twitterSavedFeeds.filter(function (savedItem) {
                if (item.id == savedItem.postId) {
                    item.saved = true;
                    return true;
                }
            });
        }

        $scope.initialLoad();

        $scope.reload = function(){
            $scope.initialLoad();
        }
    })