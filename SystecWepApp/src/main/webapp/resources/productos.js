function saveProduct(context) {
    swal("Se ha guardado el producto exitosamente.");
    $.ajax({
        type: 'GET',
        url: context,
        success: function (result) {

        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert("error");
        console.log(jqXHR);
        console.log(textStatus);
        console.log(errorThrown);
    });
}

function openEditProduct(context) {
    $.ajax({
        type: 'GET',
        url: context,
        success: function (result) {
            $('#modal-content').html(result);
            $('#modalEditProduct').modal('show');
        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert("error");
        console.log(jqXHR);
        console.log(textStatus);
        console.log(errorThrown);
    });
}