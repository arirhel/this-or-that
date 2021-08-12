"use strict";

// ### Choosy class constructor
function Choosy(init) {
  let opts = init || {};
  this.competitors = opts.competitors || [];
}

module.exports = Choosy;
