FROM java:8

COPY ./src /usr/quadtree/src

RUN cd /usr/quadtree/src && \
    mkdir /usr/quadtree/out && \
    find . -name '*.java' -exec javac -d /usr/quadtree/out -Xlint:unchecked {} \;

WORKDIR /usr/quadtree/out

RUN jar cvfe quadtree.jar quadtree.Program .

CMD ["java", "-jar", "quadtree.jar"]
