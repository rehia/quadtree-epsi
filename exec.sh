#!/bin/sh

MYSELF=$(which "$0" 2>/dev/null)

[ $? -gt 0 -a -f "$0" ] && MYSELF="./$0"

JAVA_OPT=""
INVALID_OPT="Invalid option :"
HELP="HELP: Quadtree Java Programme :

1) ./appquadtree -list
2) ./appquadtree -depth 10 20     
3) ./appquadtree -nearest 10 20

-list | -l : List the all generated points.

-depth | -d : Know the depth of a point in the tree , with the X,Y coordinates of the point.

-nearest | -n : Know the closest to a given point points, with the X,Y coordinates of the point
"

case $1 in
   -list|-l)   JAVA_OPT="list" ;;
   -depth|-d)   JAVA_OPT="depth $2 $3" ;;
   -nearest|-n)   JAVA_OPT="nearest $2 $3" ;;
   -help|-h)   echo "$HELP" ;;
   \?)   echo "$INVALID_OPT $1" ;;
esac

if test -n "$JAVA_OPT" ; then
     exec java -jar $MYSELF $JAVA_OPT
fi
