$(document).ready(function () {
    $("#auth-form").submit(function (event) {
        var pass = $("#pass").val();
        var spass = $("#spass").val();
        if (pass.length < 6) {
            event.preventDefault();
            alert("Password must contain at least 6 characters.");
        }
        if (pass != spass) {
            event.preventDefault();
            alert("Password did not match. Please enter the same password in both fields.");
        }
    });
});