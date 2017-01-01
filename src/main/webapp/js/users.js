$(document).ready(function () {
    getUsers();

    $("#users").on("click", "#grant", function () {
        $.get("grant_privileges", {id: $(this).val()}, function () {
            $("#users").find("tr:gt(0)").remove();
            getUsers();
        });
    });

    $("#users").on("click", "#revoke", function () {
        $.get("revoke_privileges", {id: $(this).val()}, function () {
            $("#users").find("tr:gt(0)").remove();
            getUsers();
        });
    });

    $("#users").on("click", "#ban", function () {
        if (confirm("Are you sure?")) {
            $.get("delete_user", {id: $(this).val()}, function () {
                $("#users").find("tr:gt(0)").remove();
                getUsers();
            });
        }
    });
});

function getUsers() {
    var table = $("#users");
    $.get("show_users", function (responseJson) {
        if (responseJson.length > 0) {
            $.each(responseJson, function (index, item) {
                var button;
                var role = item.role.name;
                if (role === "admin") {
                    return true;
                }
                if (role === "user") {
                    button = $("<button>").attr("id", "grant").text("Grant privileges");
                }
                if (role === "moderator") {
                    button = $("<button>").attr("id", "revoke").text("Revoke privileges");
                    role = $("<span>").addClass("moderator").text(role);
                }
                $("<tr>").appendTo(table)
                    .append($("<td>").text(item.id))
                    .append($("<td>").html(role))
                    .append($("<td>").text(item.email))
                    .append($("<td>").text(item.phone))
                    .append($("<td>").text(item.city))
                    .append($("<td>")
                        .append(button.val(item.id))
                        .append($("<button>").attr("id", "ban").val(item.id).text("Permanent ban(delete)")));
            })
        } else {
            table.append($("<tr>").append($("<td>").attr("colspan", "5").text("Not found...")));
        }
    });
}