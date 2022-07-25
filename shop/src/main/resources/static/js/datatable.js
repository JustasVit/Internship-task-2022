$(document).ready(function (){
    $("#products-table").DataTable({
        'aoColumnDefs': [{
            'bSortable': false,
            'aTargets': [-1]
        }]
    });
})