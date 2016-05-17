/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author max
 */
public class NodeTest {

    private Rectangle baseZone;
    private Point p1;
    private Point p2;
    private Point p3;
    private Point p4;
    private Point p5;

    @Before
    public void setUp() {
        this.baseZone = new Rectangle(100, 100);
        this.p1 = new Point(10, 10);
        this.p2 = new Point(60, 20);
        this.p3 = new Point(80, 80);
        this.p4 = new Point(20, 40);
        this.p5 = new Point(45, 50);
    }

    @After
    public void tearDown() {
    }

    private Node givenANewLeafNode() {
        return new Node(baseZone, 1);
    }

    @Test
    public void shouldNotBeALeafWhenCreatingAnEmptyNode() {
        Node n = new Node();

        assertEquals(false, n.isLeaf());
    }

    @Test
    public void shouldBeALeafWhenCreatingANodeWithAZone() {
        Node n = givenANewLeafNode();

        assertEquals(true, n.isLeaf());
    }

    @Test
    public void shouldBeALeafWhenAdding1PointsToALeaf() {
        Node n = givenANewLeafNode();
        n.addPoint(p1);

        assertEquals(true, n.isLeaf());
    }

    @Test
    public void shouldBeALeafWhenAdding4PointsToALeaf() {
        Node n = givenANewLeafNode();
        n.addPoint(p1);
        n.addPoint(p2);
        n.addPoint(p3);
        n.addPoint(p4);

        assertEquals(true, n.isLeaf());
    }

    @Test
    public void shouldNotBeALeafWhenAdding5PointsToALeaf() {
        Node n = givenANewLeafNode();
        n.addPoint(p1);
        n.addPoint(p2);
        n.addPoint(p3);
        n.addPoint(p4);
        n.addPoint(p5);

        assertEquals(false, n.isLeaf());
    }

    @Test
    public void pointShouldNotBeAddedIfItHasNegativeCoordinates() {
        Node n = givenANewLeafNode();

        Point outOfBounds = new Point(1, -1);
        n.addPoint(outOfBounds);

        assertEquals(false, n.pointExists(outOfBounds));
    }

    @Test
    public void pointShouldNotBeAddedIfItHasGreaterCoordinatesThanTheZone() {
        Node n = givenANewLeafNode();

        Point outOfBounds = new Point(101, 2);
        n.addPoint(outOfBounds);

        assertEquals(false, n.pointExists(outOfBounds));
    }

    @Test
    public void pointShouldBeAddedIfItsCoordinatesAreInsideTheZone() {
        Node n = givenANewLeafNode();

        n.addPoint(p1);

        assertEquals(true, n.pointExists(p1));
    }

    @Test
    public void pointShouldBeAddedIfItsCoordinatesAreZeroZero() {
        Node n = givenANewLeafNode();

        Point zeroZero = new Point(0, 0);
        n.addPoint(zeroZero);

        assertEquals(true, n.pointExists(zeroZero));
    }

    @Test
    public void pointShouldBeAddedIfItsCoordinatesAreTheEdgeOfTheZone() {
        Node n = givenANewLeafNode();

        Point edge = new Point(100, 100);
        n.addPoint(edge);

        assertEquals(true, n.pointExists(edge));
    }

    @Test
    public void shouldBeZeroPointInANewLeaf() {
        Node n = givenANewLeafNode();

        assertEquals(0, n.numberOfPoints());
    }

    @Test
    public void shouldBeOnePointWhenAddingAPoint() {
        Node n = givenANewLeafNode();

        n.addPoint(p1);

        assertEquals(1, n.numberOfPoints());
    }

    @Test
    public void shouldBeOnePointWhenAddingTwiceTheSamePoint() {
        Node n = givenANewLeafNode();

        n.addPoint(p1);
        n.addPoint(p1);

        assertEquals(1, n.numberOfPoints());
    }

    @Test
    public void shouldBeOnePointWhenAddingACopyOfAnExistingPoint() {
        Node n = givenANewLeafNode();

        Point original = new Point(1, 1);
        Point copy = new Point(1, 1);

        n.addPoint(original);
        n.addPoint(copy);

        assertEquals(1, n.numberOfPoints());
    }

    @Test
    public void shouldBe5PointsWhenAdding5DifferentPoints() {
        Node n = givenANewLeafNode();

        n.addPoint(p1);
        n.addPoint(p2);
        n.addPoint(p3);
        n.addPoint(p4);
        n.addPoint(p5);

        assertEquals(5, n.numberOfPoints());
    }

    @Test
    public void shouldBeDepthOf0WhenSearchinfForANotExistingPoint() {
        Node n = givenANewLeafNode();

        n.addPoint(p1);

        assertEquals(0, n.getDepthOfPoint(p2));
    }

    @Test
    public void shouldBeDepthOf1WhenAddinfOnlyOnePoint() {
        Node n = givenANewLeafNode();

        n.addPoint(p1);

        assertEquals(1, n.getDepthOfPoint(p1));
    }

    @Test
    public void shouldBeDepthOf1WhenAdding4Points() {
        Node n = givenANewLeafNode();

        n.addPoint(p1);
        n.addPoint(p2);
        n.addPoint(p3);
        n.addPoint(p4);

        assertEquals(1, n.getDepthOfPoint(p1));
    }

    @Test
    public void shouldBeDepthOf2WhenAdding5Points() {
        Node n = givenANewLeafNode();

        n.addPoint(p1);
        n.addPoint(p2);
        n.addPoint(p3);
        n.addPoint(p4);
        n.addPoint(p5);

        assertEquals(2, n.getDepthOfPoint(p1));
    }

    @Test
    public void shouldBeEmptyWhenNoNearPoint() {
        Node n = givenANewLeafNode();

        n.addPoint(p1);

        List<Point> emptyList = new ArrayList<>();

        assertEquals(emptyList, n.getNearestPoints(p1));

    }

    @Test
    public void shouldBeNearOfOnePoint() {
        Node n = givenANewLeafNode();

        n.addPoint(p1);
        n.addPoint(p2);

        List<Point> nearestPoints = n.getNearestPoints(p1);

        assertEquals(true, nearestPoints.contains(p2));
    }

    @Test
    public void shouldBeNearOfThreePoint() {
        Node n = givenANewLeafNode();

        n.addPoint(p1);
        n.addPoint(p2);
        n.addPoint(p3);
        n.addPoint(p4);

        List<Point> nearestPoints = n.getNearestPoints(p1);

        boolean containsAllPoints = nearestPoints.contains(p2)
                && nearestPoints.contains(p3)
                && nearestPoints.contains(p4);

        assertEquals(true, containsAllPoints);
    }

    @Test
    public void shouldBeNearOf1Point() {
        Node n = givenANewLeafNode();

        n.addPoint(p1);
        n.addPoint(p2);
        n.addPoint(p3);
        n.addPoint(p4);
        n.addPoint(p5);

        List<Point> nearestPoints = n.getNearestPoints(p1);

        assertEquals(true, nearestPoints.contains(p4));
    }

}
