"use strict";

let competitors;

function bodyOnLoad(optionsDtoJs) {
  document.getElementById("questionSpan").innerHTML = optionsDtoJs.question;
  competitors = optionsDtoJs.options;
  createArena(competitors);
}

function createArena(competitors) {
  const arena = document.getElementById("arena");
  arena.innerHTML = "";

  // Create the "fighting" arena based on the competitors array
  if (competitors.length == 0) {
    // Error
    arena.textContent = "Error";
  } else if (competitors.length == 1) {
    // Winner
    arena.textContent = "Winner! " + competitors[0];
  } else {
    let docFrag = document.createDocumentFragment();

    let button0 = document.createElement("button");
    let button1 = document.createElement("button");

    button0.textContent = competitors[0];
    button0.setAttribute("onclick", "eliminate(competitors, 1)");

    button1.textContent = competitors[1];
    button1.setAttribute("onclick", "eliminate(competitors, 0)");

    docFrag.appendChild(button0);
    docFrag.appendChild(button1);

    arena.appendChild(docFrag);
  }
}

function eliminate(competitors, index) {
  competitors.splice(index, 1);
  createArena(competitors);
}

// ### Choosy class constructor
//function Choosy(init) {
//  let opts = init || {};
//  this.competitors = opts.competitors || [];
//}
//
//module.exports = Choosy;
