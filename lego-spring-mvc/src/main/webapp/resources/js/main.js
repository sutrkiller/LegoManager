/* MAIN JAVASCRIPT FILE */

/*$( document ).ready(function() {

    $('#editPieceTypeModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var name = button.data('name') // Extract info from data-* attributes
        var actClrs = button.data('colors') // Extract info from data-* attributes
        var id = button.data('id') // Extract info from data-* attributes

        var activeColors = actClrs.split('[', 2)[1].split(']', 2)[0].split(',');

        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('.modal-body input.name').val(name)
        modal.find('form').attr('action', function (i, old) {
            return old.replace('{id}', id);
        });

        modal.find('.modal-body label.color').each(function () {
            var colorLabel = $(this);
            var checkbox = $(this).find('input[name=colors]');

            colorLabel.removeClass("active");
            checkbox.prop('checked', true);

            var colorName = checkbox.val();
            $.each(activeColors, function(i, activeColorName) {

                if (colorName.trim() == activeColorName.trim()) {
                    colorLabel.addClass("active");
                    checkbox.prop('checked', true);
                }

            })

        });
    })

});*/

