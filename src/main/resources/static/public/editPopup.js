let selectedId = 0

function getPopupFields() {
    return {
        name: $('#edit-name').val(),
        email: $('#edit-email').val(),
        phone: $('#edit-phone').val(),
        internalPhone: $('#edit-internalPhone').val(),
        department: $('#edit-department :selected').val(),
        posts: getOptions($('#edit-posts'))
    }
}

function setPopupFields(fields) {
    console.log('Set fields: ', fields)

    $('#edit-name').val(fields.name)
    $('#edit-email').val(fields.email)
    $('#edit-phone').val(fields.phone)
    $('#edit-internalPhone').val(fields.internalPhone)

    selectOptions($('#edit-department'), [fields.department])
    selectOptions($('#edit-posts'), fields.posts || [])
}

function setHeader(name, id = undefined) {
    $('#edit-header').text(name)
    $('#edit-id').text(id)
}

function editMode() {
    $('#edit-save-changes').show()
    $('#edit-create').hide()
}

function createMode() {
    $('#edit-save-changes').hide()
    $('#edit-create').show()
}

function createNew() {
    setHeader('New')
    createMode()
    setPopupFields({})
    //$('#edit-modal').modal('show')
}

function editExists(id) {
    selectedId = id
    setHeader('Edit: ' + id)
    editMode()
    return $.get('/api/v1/extended-employee/' + id, {}, function (employees) {
        setPopupFields(employees[0])
        $('#edit-modal').modal('show')
    })
}

$(document).ready(function () {
    const departmentsRequest = $.get('/api/v1/department/search', {name: ''}, x => fillSelect($('#edit-department'), x))
    const workPostRequest = $.get('/api/v1/work-post/search', {name: ''}, x => fillSelect($('#edit-posts'), x))

    $.when(departmentsRequest, workPostRequest).done(function () {
        $('#edit-save-changes').on('click', function () {
            $('#edit-modal').modal('hide')
            $.ajax({
                type: 'PATCH',
                url: '/api/v1/extended-employee/' + selectedId,
                headers: {'Content-Type': 'application/json'},
                data: JSON.stringify(getPopupFields())
            }).done(function () {
                window.location.reload()
            })
        })

        $('#edit-create').on('click', function () {
            $('#edit-modal').modal('hide')
            $.ajax({
                type: 'POST',
                url: '/api/v1/extended-employee',
                headers: {'Content-Type': 'application/json'},
                data: JSON.stringify(getPopupFields())
            }).done(function () {
                window.location.reload()
            })
        })

        $('#add-new').on('click', function () {
            console.log('click')
            createNew()
        })

        console.log('Edit popup initialized')
    })
})