FROM java:8
COPY dist/ /usr/src/QuadTree
WORKDIR /usr/src/QuadTree
CMD ["java", "-jar", "QuadTree.jar"]
