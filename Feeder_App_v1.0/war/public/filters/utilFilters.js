angular.module("feeds")
    .filter("daysBetween", function () {
        return function (value) {
            var date;
            if (angular.isString(value)) {
                date = new Date(value);
            }

            //Get 1 day in milliseconds
            var one_day = 1000 * 60 * 60 * 24;
            //Get 1 hour in millis
            var one_hour = 1000 * 60 * 60;
            //Get 1 minute in millis
            var one_min = 1000 * 60;
            //Get 1 second in millis
            var one_sec = 1000;
            // Convert both dates to milliseconds
            var date1_ms = date.getTime();
            var date2_ms = new Date().getTime();

            // Calculate the difference in milliseconds
            var difference_ms = date2_ms - date1_ms - (15 * one_hour);

            var difference_secs = Math.round(difference_ms / one_sec);
            var difference_mins = Math.round(difference_ms / one_min);
            var difference_hours = Math.round(difference_ms / one_hour);
            var difference_days = Math.round(difference_ms / one_day);
            // Convert back to days and return
            var desc = "";
            if (difference_days < 0) {
                return "Added now";
            }
            else if (difference_days == 0) {
                if (difference_hours == 0) {
                    if (difference_mins == 0) {
                        return "Added now"
                    }
                    var minutesStr = difference_mins == 1 ? "Added 1 minute ago" : "Added " + difference_mins + " minutes ago";
                    return minutesStr;
                }
                var hoursStr = difference_hours == 1 ? "Added 1 hour ago" : "Added " + difference_hours + " hours ago";
                return hoursStr;
            }
            else if (difference_days == 1) {
                return "Added yesterday"
            }
            else {
                return "Added " + difference_days + " days ago";
            }
        };
    })
    .filter('trusted', ['$sce', function ($sce) {
        return function (url) {
            return $sce.trustAsResourceUrl(url);
        };
    }]);