"use strict";

const ArenaRenderer = require("../main.js");
const assert = require("assert");

describe("ArenaRenderer", function () {
  const options = {
    ref: "arenaRenderer",
    arenaId: "arena",
    candidates: [
      {value: "Physician Assistant"},
      {value: "Software Developer"},
      {value: "Bike Courier"},
      {value: "Body Painter"},
      {value: "Veterinarian"}
    ]
  }

  let arenaRenderer;
  beforeEach(function() {
    arenaRenderer = new ArenaRenderer(options)
  })

  it("constructor", function () {
    assert.equal(arenaRenderer.candidates, options.candidates)
  });

});
