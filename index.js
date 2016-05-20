var Quad = require('./quad.js');
var prompt = require('prompt');

var q = new Quad({
    w: 100,
    h: 100,
    capacity: 4
});
    q.insert({x:3,y:3,w:0,h:0});
for (var i = 0; i < 49; i++) {
    q.insert({
        x: Math.floor(Math.random() * 100),
        y: Math.floor(Math.random() * 100),
        w: 0,
        h: 0
    });
}

prompt.start();

var afficherMenu = function() {
    console.log("Menu :")
    console.log("------\n");
    console.log("1) Liste des points.\n");
    console.log("2) Afficher les points les plus proches.\n");
    console.log("3) Afficher la profondeur d'un point\n");
    console.log("exit) Pour fermer le programme\n");
    prompt.get(['option'], function (err, result) {
        execMenu(result.option);
    });
};

var execMenu = function(option) {
    option = parseInt(option);
    switch(option) {
        case 1:
            afficherPoints();
            break;
        case 2: 
            afficherPointProche();
            break;
        case 3: 
            afficherProfondeurPoint();
            break;
    }
};

var afficherPoints = function() {
    console.log("Liste des points :");
    console.log("------------------\n");
    var points = q.getAllPoints();
    points.forEach(function(point) {
        console.log("[x : "+point.x+", y : "+point.y+"]");
    });
    afficherPause();
};

var afficherProfondeurPoint = function() {
    prompt.get(['x','y'], function (err, result) {
        console.log("\n");
        console.log("Le point ("+result.x+", "+result.y+") est à une profondeur de "+q.getDepth(parseInt(result.x), parseInt(result.y)));
        afficherPause();
    });
};

var afficherPointProche = function() {
    prompt.get(['x','y'], function (err, result) {
        var points = q.getNearestPoint(parseInt(result.x), parseInt(result.y));
        console.log("\n");
        console.log("Les points les plus proches du point ("+result.x+", "+result.y+") sont :");
        points.forEach(function(point) {
            console.log("[x : "+point.x+", y : "+point.y+"]");
        });
        afficherPause();
    });
};

var afficherPause = function() {
    console.log("\n\n");
    prompt.get(['pause'], function (err, result) {
        afficherMenu();
    });
};

afficherMenu();