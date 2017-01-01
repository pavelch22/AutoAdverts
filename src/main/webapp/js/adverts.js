$(document).ready(function () {
    $("#adverts-table").DataTable(
        {
            "order": [[0, "desc"]],
            "columnDefs": [
                {
                    "targets": [0],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [1],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [9],
                    "visible": false,
                    "searchable": false
                }
            ]
        }
    );
    $(".clickable-row").click(function () {
        window.location.href = $(this).data("href");
    });
});