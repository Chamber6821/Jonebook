function getPage() {
    const params = new URLSearchParams(window.location.search)
    return +(params.get('page') || 0)
}

function initPageControls(page) {
    if (!page)
        $('#previous-button').addClass('disabled')
    else
        $('#previous-button').children().attr('href', `?page=${page - 1}`)

    $('#current-page').children().append(page)

    $('#next-button').children().attr('href', `?page=${page + 1}`)
}

function acronym(str) {
    return str.match(/\b(\w)/g).join('');
}

function compressName(name, maxLen = 10) {
    if (name.length <= maxLen) return name
    const acr = acronym(name);
    if (acr.length > 1) return acr
    return name
}