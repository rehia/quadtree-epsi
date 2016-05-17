#!/bin/sh

java=`which java`

if [ !  -x $java ]
then
    echo "Couldn't find java, maybe use docker"
    exit
fi

echo "Java found at $java"

$java -jar "dist/QuadTree.jar"
