angular.module("feeds")
    .directive("fullScreen", function ($compile) {
        return {
            scope: {},
            link: function ($scope, $elem, $attrs) {
                var content = document.querySelector("#imageFullScreen").textContent.trim();
                var listElem = angular.element(content);

                $($elem)
                    .mouseover(function () {
                        $(this).addClass("pointed");
                    })
                    .mouseout(function () {
                        $(this).removeClass("pointed");
                    });

                $elem.on('click', function () {
                    $scope.$apply(function () {
                        $scope.image = $attrs["src"];
                        var compileFn = $compile(listElem);
                        compileFn($scope);

                        console.log("List Element: " + listElem);

                        $('#myModal').modal();
                        $('#myModal').on('shown.bs.modal', function () {
                            $('#myModal .modal-content').html(listElem[0]);
                        });
                        $('#myModal').on('hidden.bs.modal', function () {
                            $('#myModal .modal-content').html('');
                        });
                    })

                });
            }
        }
    })