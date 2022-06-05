function saveProduct(context) {
    $.ajax({
        type: 'GET',
        url: context,
        success: function (result) {
            swal("Se ha guardado el producto exitosamente.");
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