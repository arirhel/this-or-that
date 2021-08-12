"use strict";

const Choosy = require("../main.js");
const assert = require("assert");

describe("Choosy", function () {
  const init = { competitors: ["one", "two", "three"] }

  let choosy;
  beforeEach(function() {
    choosy = new Choosy(init)
  })

  it("constructor", function () {
    assert.equal(choosy.competitors, init.competitors)
  });

});
