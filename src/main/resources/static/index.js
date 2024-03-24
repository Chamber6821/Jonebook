function getPage() {
    const params = new URLSearchParams(window.location.search)
    return +(params.get('page') || 0)
}

function initPageControls(page) {
    if (!page) $('#previous-button').addClass('disabled')
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
    return str.match(/\b(\w)/g).join('')
}

function compressName(name, maxLen = 10) {
    if (name === null) return null
    if (name.length <= maxLen) return name
    const acr = acronym(name)
    if (acr.length > 1) return acr
    return name
}

function fillSelect(container, elements) {
    for (let element of elements) {
        container.append($('<option>').text(element))
    }
    container.selectpicker()
}

function deleteAllFalsy(obj) {
    Object.keys(obj).forEach((key) => {
        if (!obj[key]) {
            delete obj[key]
        }
    })
    return obj
}

function selectOptions(root, options) {
    $(root)
        .children('option')
        .each(function () {
            if (options.includes($(this).val())) {
                $(this).attr('selected', 'selected')
            } else {
                $(this).removeAttr('selected')
            }
        })
        .change()
}

function getOptions(root) {
    const options = []
    $(root)
        .children(':selected')
        .each(function () {
            options.push($(this).val())
        })
    return options
}
