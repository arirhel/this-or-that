// ArenaRenderer creates HTML elements and appends them to the DOM based upon it's candidates list.
"use strict";

// ### ArenaRenderer Class Constructor
function ArenaRenderer(options) {
  const opts = options || {};
  // Name of the variable used with this constructor
  this.ref = opts.ref;
  // Id of the element to append the arena onto
  this.arenaId = opts.arenaId;
  // A list of candidates to for the arena
  this.candidates = opts.candidates;
}

ArenaRenderer.prototype.render = function() {
  const arena = document.getElementById(this.arenaId)
  arena.innerHTML = "";
  if (this.candidates.length == 0) {
      // Error
      arena.textContent = "Error: no candidates found";
    } else if (this.candidates.length == 1) {
      // Winner
      arena.textContent = "Winner! " + this.candidates[0].value;
    } else {
      // Display the first two candidates in the list (roster)
      let docFrag = document.createDocumentFragment();
  
      let button0 = document.createElement("button");
      let button1 = document.createElement("button");
  
      button0.textContent = this.candidates[0].value;
      button0.setAttribute("onclick", this.ref + ".eliminate(1)");
  
      button1.textContent = this.candidates[1].value;
      button1.setAttribute("onclick", this.ref + ".eliminate(0)");
  
      docFrag.appendChild(button0);
      docFrag.appendChild(button1);

      arena.appendChild(docFrag);
    }
};

// On elimination, the loser is removed and the winner remains to face the next opponent
ArenaRenderer.prototype.eliminate = function(index) {
  this.candidates.splice(index, 1);
  this.render();
};

module.exports = ArenaRenderer;