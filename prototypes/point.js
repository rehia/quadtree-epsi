function Point(x, y) {
   if (typeof x === 'undefined' || typeof y === 'undefined') {
     throw new Error("Point can't be construct with no values");
   }
   if (x < 0 || y < 0) {
     throw new Error("Point can't be construct with negative coordinate");
   }
   this.x = x;
   this.y = y;
 };

 Point.prototype.getX = function() {
   return this.x;
 };
 Point.prototype.getY = function() {
   return this.y;
 };
 module.exports = Point;
