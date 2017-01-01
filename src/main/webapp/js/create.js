$(document).ready(function () {
    $("#auth-form").submit(function (event) {
        var files = $("#photos").get(0).files;
        if (files.length > 8) {
            alert("You can upload only 8 photos");
            return false;
        }
        var maxSize = 2 * 1024 * 1024;
        $.each(files, function (i, v) {
            if (v.size > maxSize) {
                alert("Maximum photo size: 2MB");
                event.preventDefault();
                return false;
            }
        });
    });
});