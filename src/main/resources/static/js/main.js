function setInnerHTMLValues(optionsDtoJs) {
    document.getElementById("questionSpan").innerHTML = optionsDtoJs.question;
    document.getElementById("optionsSpan").innerHTML = optionsDtoJs.options;
}

function choose(options) {
    console.log(options)
}