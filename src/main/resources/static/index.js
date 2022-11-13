function getPage() {
    const params = new URLSearchParams(window.location.search)
    return +(params.get('page') || 0)
}

function initPageControls(page) {
    if (!page)
        $('#previous-button').addClass('disabled')
    else {
        const prevUrl = new URL(window.location.href)
        prevUrl.searchParams.set('page', `${page - 1}`)
        $('#previous-button').children().attr('href', prevUrl.toString())
    }

    $('#current-page').children().append(page)

    const nextUrl = new URL(window.location.href)
    nextUrl.searchParams.set('page', `${page + 1}`)
    $('#next-button').children().attr('href', nextUrl.toString())
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