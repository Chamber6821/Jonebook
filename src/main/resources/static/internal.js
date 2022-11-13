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

$(document).ready(function () {
    initPageControls(getPage())
    $.get('/api/v1/extended-employee', {page: getPage()}, function (employees) {
        for (let employee of employees) {
            $('#employees').append(renderExtendedEmployee(employee))
        }
    })
})
