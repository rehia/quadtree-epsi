var Quad = require('../quad.js');
var assert = require("assert");


var quad = new Quad({
	w: 100,
	h: 100,
	capacity: 4
});


describe('QuadTree', function () {
	describe('Quadtree test', function(){
	  	it("L'objet ne contient pas de points", function(){
	    	assert(quad.getAllPoints().length == 0);
	    	suiteTest();
	  	});
	});
});

function suiteTest() {
	for (var i = 0; i < 49; i++) {
		quad.insert({
	        x: Math.floor(Math.random() * 100),
	        y: Math.floor(Math.random() * 100),
	        w: 0,
	        h: 0
		});
	}
	quad.insert({x: 10, y: 10, w: 0, h:0});

	describe('QuadTree', function () {

		describe('Insertion', function () {
			it("Le QuadTree contient 50 points", function () {
				assert(quad.getAllPoints().length == 50);
			});
		});

		describe('Point proche', function(){
		  it("Tester que la méthode point proche renvoie une liste de point" , function(){
				var points = quad.getNearestPoint(10,10);
				assert(Array.isArray(points), 'liste de point');
		  });
		});

		describe('Point existe', function(){
		  	it("Tester si un point existe", function(){
		    	assert(quad.getDepth(10, 10) != null);
		  	});
		});

		describe('Profondeur', function(){
		  	it("Tester que la méthode renvoie bien une profondeur" , function(){
				assert(Number.isInteger(quad.getDepth(10, 10)));
		  	});
		});
	});
}