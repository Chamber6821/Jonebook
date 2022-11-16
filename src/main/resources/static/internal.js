function renderExtendedEmployee(employee) {
    return $('<tr>')
        .append($('<td>').text(employee.id))
        .append($('<td>').text(employee.name))
        .append($('<td>').text(employee.email))
        .append($('<td>').text(employee.phone))
        .append($('<td>').text(employee.internalPhone))
        .append($('<td>').text(compressName(employee.department)))
        .append($('<td>').text(employee.posts.map(compressName).join(', ')))
}

function fillTable(employees) {
    const body = $('#employees tbody')
    for (let employee of employees) {
        body.append(renderExtendedEmployee(employee))
    }
}

function collectSearchParams() {
    const departments = []
    $.each($('#departments :selected'), function () {
        departments.push($(this).val())
    })

    const posts = []
    $.each($('#posts :selected'), function () {
        posts.push($(this).val())
    })

    return {
        nameFragment: $('#nameFragment').val(),
        emailFragment: $('#emailFragment').val(),
        phonePrefix: $('#phonePrefix').val(),
        internalPhonePrefix: $('#internalPhonePrefix').val(),
        departmentVariants: departments,
        postsFragment: posts,
    }
}

$(document).ready(function () {
    initPageControls(getPage())

    $.get('/api/v1/extended-employee/search' + window.location.search, {page: getPage()}, fillTable)

    $.get('/api/v1/department/search', {name: ''}, x => fillSelect($('#departments'), x))

    $.get('/api/v1/work-post/search', {name: ''}, x => fillSelect($('#posts'), x))

    $('#search-form').on('submit', function () {
        $('#employees tbody tr').remove()
        const query = $.param(deleteAllFalsy(collectSearchParams()), true)
        window.location.href = "/?" + query
        return false
    })
})
