$(document).ready(function () {

    $(".tab:first").show();
    $(".h-line").click(function () {
        $(".tab").hide();
        $(this).next().show();
    });

    getBrands();

    $("#brands").on("change", function () {
        $("#brands-invitation").remove();
        $("#models").find("tr:gt(0)").remove();
        var brandId = $(this).find("option:selected").val();
        getModels(brandId);
        $("#update-brand").prop("disabled", false);
        $("#delete-brand").prop("disabled", false);
        $("#model-controls").show();
    });


    $("#add-brand").click(function () {
        $("#brand-controls").hide();
        $("#brands").prop("disabled", true);
        $("#models").find("input").prop("disabled", true);
        $("#model-controls").hide();
        $("#brand-form").attr("action", "insert_brand").show();
    });

    $("#update-brand").click(function () {
        var brandForm = $("#brand-form");
        var select = $("#brands").find("option:selected");
        brandForm.find("input[name=id]").val(select.val());
        brandForm.find("input[name=brandName]").val((select.text()));
        $("#brand-controls").hide();
        $("#brands").prop("disabled", true);
        $("#models").find("input").prop("disabled", true);
        $("#model-controls").hide();
        brandForm.attr("action", "update_brand").show();
    });

    $("#delete-brand").click(function () {
        if (confirm("Are you sure you want to delete this item?")) {
            var id = $("#brands").find("option:selected").val();
            $.get("delete_brand", {id: id}, function () {
                $("#brands").find("option").remove();
                getBrands();
                $("#models").find("tr:gt(0)").remove();
                $("#models").append($("<tr>").append($("<td>").attr("colspan", "2").text("Not found...")));
            });
        }
    });

    $("#brand-form").submit(function (event) {
        var action = $(this).attr("action");
        $.post(action, $(this).serialize(), function () {
            $("#brands").find("option").remove();
            getBrands();
            $("#models").find("tr:gt(0)").remove();
            $("#models").append($("<tr>").append($("<td>").attr("colspan", "2").text("Not found...")));
            $("#brands").prop("disabled", false);
            $("#brand-controls").show();
            $("#brand-form").find("input[type=text]").val("");
            $("#brand-form").hide();
        });
        event.preventDefault();
    });

    $("#brand-cancel").click(function () {
        $("#brand-controls").show();
        $("#brands").prop("disabled", false);
        $("#models").find("input").prop("disabled", false);
        $("#model-controls").show();
        $("#brand-form").hide();
    });


    $("#add-model").click(function () {
        $("#model-controls").hide();
        $("#brands").prop("disabled", true);
        $("#brand-controls").find("button").prop("disabled", true);
        $("#models").find("input").prop("disabled", true);
        $("#model-form").attr("action", "insert_model").show();
    });

    $("#update-model").click(function () {
        var modelForm = $("#model-form");
        var tr = $("input[name=model-id]:checked").closest("tr");
        modelForm.find("input[name=id]").val(tr.find("td:nth-child(1)").text());
        modelForm.find("input[name=modelName]").val(tr.find("td:nth-child(2)").text());
        $("#model-controls").hide();
        $("#brands").prop("disabled", true);
        $("#brand-controls").find("button").prop("disabled", true);
        $("#models").find("input").prop("disabled", true);
        modelForm.attr("action", "update_model").show();
    });

    $("#delete-model").click(function () {
        if (confirm("Are you sure you want to delete this item?")) {
            var id = $("input[name=model-id]:checked").val();
            $.get("delete_model", {id: id}, function () {
                $("#models").find("tr:gt(0)").remove();
                getModels($("#brands").find("option:selected").val());
            });
        }
    });

    $("#model-form").submit(function (event) {
        var data = $(this).serialize() + "&brandId=" + $("#brands").find("option:selected").val();
        $.post($(this).attr("action"), data, function (response) {
            $("#models").find("tr:gt(0)").remove();
            getModels($("#brands").find("option:selected").val());
            $("#brands").prop("disabled", false);
            $("#brand-controls").find("button").prop("disabled", false);
            $("#model-controls").show();
            $("#model-form").find("input[type=text]").val("");
            $("#model-form").hide();
        });
        event.preventDefault();
    });

    $("#model-cancel").click(function () {
        $("#model-controls").show();
        $("#brands").prop("disabled", false);
        $("#brand-controls").find("button").prop("disabled", false);
        $("#models").find("input").prop("disabled", false);
        $("#model-form").hide();
    });

    $("#models").on("click", "input[name=model-id]", function () {
        $("#update-model").prop("disabled", false);
        $("#delete-model").prop("disabled", false);
    });


    getEngineTypes();

    $("#add-engine").click(function () {
        $("#engine-controls").hide();
        $("#engine-types").find("input").prop("disabled", true);
        $("#engine-form").attr("action", "insert_engine_type").show();
    });

    $("#update-engine").click(function () {
        var engineForm = $("#engine-form");
        var tr = $("input[name=engine-type-id]:checked").closest("tr");
        engineForm.find("input[name=id]").val(tr.find("td:nth-child(1)").text());
        engineForm.find("input[name=engineType]").val(tr.find("td:nth-child(2)").text());
        $("#engine-controls").hide();
        $("#engine-types").find("input").prop("disabled", true);
        engineForm.attr("action", "update_engine_type").show();
    });

    $("#delete-engine").click(function () {
        if (confirm("Are you sure you want to delete this item?")) {
            var id = $("input[name=engine-type-id]:checked").val();
            $.get("delete_engine_type", {id: id}, function () {
                $("#engine-types").find("tr:gt(0)").remove();
                getEngineTypes();
            });
        }
    });

    $("#engine-form").submit(function (event) {
        $.post($(this).attr("action"), $(this).serialize(), function (response) {
            $("#engine-types").find("tr:gt(0)").remove();
            getEngineTypes();
            $("input[name=engine-type-id]").prop("disabled", false).prop("checked", false);
            $("#engine-controls").show();
            $("#engine-form").find("input[type=text]").val("");
            $("#engine-form").hide();
        });
        event.preventDefault();
    });

    $("#engine-cancel").click(function () {
        $("#engine-types").find("input").prop("disabled", false);
        $("#engine-controls").show();
        $("#engine-form").hide();
    });

    $("#engine-types").on("click", "input[name=engine-type-id]", function () {
        $("#update-engine").prop("disabled", false);
        $("#delete-engine").prop("disabled", false);
    });
});

function getEngineTypes() {
    $.get("engine_types", function (responseJson) {
        var table = $("#engine-types");
        if (responseJson.length > 0) {
            $.each(responseJson, function (index, item) {
                $("<tr>").appendTo(table)
                    .append($("<td>").text(item.id))
                    .append($("<td>").text(item.name))
                    .append($("<td>").html($("<input name='engine-type-id' type='radio'>").val(item.id)));
            });
        } else {
            table.append($("<tr>").append($("<td>").attr("colspan", "3").text("Not found...")));
        }
        $("#update-engine").prop("disabled", true);
        $("#delete-engine").prop("disabled", true);
    });
}

function getBrands() {
    $.get("brands", function (responseJson) {
        var select = $("#brands");
        if (responseJson.length > 0) {
            $("#brands").append($("<option>").attr("id", "brands-invitation").text("Select a brand name"));
            $("#update-brand").prop("disabled", true);
            $("#delete-brand").prop("disabled", true);
            $("#model-controls").hide();
            $.each(responseJson, function (index, item) {
                select.append($("<option>").val(item.id).text(item.name));
            });
        } else {
            $("#brands-invitation").remove();
            select.append($("<option>").text("Not found..."));
        }
    });
}

function getModels(brandId) {
    $.get("models", {id: brandId}, function (responseJson) {
        var table = $("#models");
        if (responseJson.length > 0) {
            $.each(responseJson, function (index, item) {
                $("<tr>").appendTo(table)
                    .append($("<td>").text(item.id))
                    .append($("<td>").text(item.name))
                    .append($("<td>").html($("<input name='model-id' type='radio'>").val(item.id)));
            });
        } else {
            table.append($("<tr>").append($("<td>").attr("colspan", "2").text("Not found...")));
        }
        $("#update-model").prop("disabled", true);
        $("#delete-model").prop("disabled", true);
    });
}
