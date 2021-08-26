"use strict";

// Relies on a global variable, "candidates", that's set in Thymeleaf template
// I would like to change that in the future.
// Could create a class but the render and eliminate functions are sort of separate concerns.
const elementId = "arena";

function renderArena() {
  const arena = document.getElementById(elementId)
  arena.innerHTML = "";
  if (candidates.length == 0) {
      // Error
      arena.textContent = "Error: no candidates found";
    } else if (candidates.length == 1) {
      // Winner
      arena.textContent = "Winner! " + candidates[0].value;
    } else {
      let docFrag = document.createDocumentFragment();
  
      let button0 = document.createElement("button");
      let button1 = document.createElement("button");
  
      button0.textContent = candidates[0].value;
      button0.setAttribute("onclick", "eliminate(candidates, 1)");
  
      button1.textContent = candidates[1].value;
      button1.setAttribute("onclick", "eliminate(candidates, 0)");
  
      docFrag.appendChild(button0);
      docFrag.appendChild(button1);

      arena.appendChild(docFrag);
    }
}

function eliminate(index) {
  candidates.splice(index, 1);
  renderArena()
}
