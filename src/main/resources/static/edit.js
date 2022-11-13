function updateFields(id) {
    $.get('/api/v1/extended-employee/' + id, function (employees) {
        employee = employees[0]
        if (!employee) clearFields()

        $('#name').val(employee.name)
        $('#email').val(employee.email)
        $('#phone').val(employee.phone)
        $('#internalPhone').val(employee.internalPhone)
        $('#department').val(employee.department).change()

        $('#posts option').each(function () {
            if (employee.posts.includes($(this).val())) {
                console.log($(this).val())
                $(this).attr('selected', 'selected').change()
            }
        })
        //
        // $('#posts').val(employee.name).change()
    })
}

function save() {
    $.ajax({
        url: '/api/v1/extended-employee/' + $('#employee-id').val(),
        type: 'PUT',

    })
}

$(document).ready(function () {
    $.get('/api/v1/department/search', {name: ''}, x => fillSelect($('#department'), x))

    $.get('/api/v1/work-post/search', {name: ''}, x => fillSelect($('#posts'), x))

    $('#employee-id').change(function () {
        updateFields($(this).val())
    })

    $('#save').click(function () {
        save()
    })
})