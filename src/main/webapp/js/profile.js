$(document).ready(function () {
    $(".clickable-row").click(function () {
        window.open($(this).data("href"), "_blank", "width = 1000");
    });
    $(".clickable-row a").click(function (event) {
        event.stopPropagation();
    });
    $("#change-pass-btn").click(function () {
        $(this).hide();
        $("#change-pass-form").show();
    });
    $("#change-pass-cancel").click(function () {
        $("#change-pass-form").hide();
        $("#change-pass-btn").show();
    });
    $("#change-pass-form").submit(function (event) {
        var pass = $("#pass").val();
        var sPass = $("#sPass").val();
        if (pass.length < 6) {
            event.preventDefault();
            alert("Password must contain at least 6 characters.");
        }
        if (pass != sPass) {
            event.preventDefault();
            alert("Password did not match. Please enter the same password in both fields.");
        }
    });
});