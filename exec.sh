#!/bin/sh

MYSELF=$(which "$0" 2>/dev/null)

[ $? -gt 0 -a -f "$0" ] && MYSELF="./$0"

HELP="HELP: Quadtree Java Programme :

1) ./appquadtree 
2) Enter the coordinate of X and Y of the requested point and what you want from this point ( depth and / or neighbors) .
3) Enjoy it !!
"
if [[ $1 == "HELP"]]; 
then
     echo "$HELP" ;
fi

exec java -jar $MYSELF
