angular.module("feeds")
    .directive("showWhenSaving", function () {
        return {
            link: function($scope, $elem, $attrs){
                $scope.$watch("saving", function(value){
                    if(value == true){
                        $attrs.$set("src", $attrs["show-when-saving"]);
                    }
                })
            }
        }
    })