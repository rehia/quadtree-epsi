# QuadTree I4 - C1 - HERMER & PRIMAUX & REYROLLE

## Requirements

You need at least one of those :

* OpenJDK 7 or 8
* Docker

## Execute QuadTree

Clone the repo, we'll call it "quadtree-epsi"

### Using OpenJDK 7 or 8 installed on your computer

```
cd quadtree-epsi
chmod +x execute.sh
./execute.sh
```

### Using Docker

```
cd quadtree-epsi
docker build -t quadtree-epsi .
docker run -it --rm --name my-running-quadtree quadtree-epsi
```
