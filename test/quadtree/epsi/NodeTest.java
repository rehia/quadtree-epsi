/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree.epsi;

import java.util.ArrayList;
import java.util.Arrays;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mathieu
 */
public class NodeTest {

    private Node node;

    public NodeTest() {
    }

    @Before
    public void setUp() {
        node = new Node(new Coordinates(1, 1), 100, 100);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void sonsWellPlaced() {
        givenIHaveANode(new Node(new Coordinates(1, 1), 100, 100));
        whenICreateItsSons();
        thenTheirOriginAre(new Coordinates(1, 1), new Coordinates(50, 1), new Coordinates(1, 50), new Coordinates(50, 50));
    }

    @Test
    public void giveTheRightLength() {
        givenIHaveANode(new Node(new Coordinates(0, 0), 100, 100));
        whenICreateItsSons();
        thenTheLengthIs(50);
    }

    @Test
    public void divisionNotRound() {
        givenIHaveANodeWhereCenterIsOdd();
        whenICreateItsSons();
        thenTheNewNodeAreNotOverlaping();
    }

    @Test
    public void addPointInTheRightNode() {
        givenIHaveANode(new Node(new Coordinates(0, 0), 100, 100));
        whenIAddAPoint(new Coordinates(12, 26));
        itGetsIntoTheRightNode(0);
    }

    @Test
    public void profExample() {
        givenIHaveANode(new Node(new Coordinates(1, 1), 100, 100));
        whenIAddAPoint(new Coordinates(10, 10));
        whenIAddAPoint(new Coordinates(60, 20));
        whenIAddAPoint(new Coordinates(80, 80));
        whenIAddAPoint(new Coordinates(20, 40));
        whenIAddAPoint(new Coordinates(45, 50));
        thenIHaveMyPointsInTheRightPlace();
    }

    @Test
    public void giveTheGoodDepth() {
        givenIHaveANode(new Node(new Coordinates(0, 0), 100, 100));
        whenIAddAPoint(new Coordinates(10, 10));
        whenIAddAPoint(new Coordinates(60, 20));
        whenIAddAPoint(new Coordinates(80, 80));
        whenIAddAPoint(new Coordinates(20, 40));
        whenIAddAPoint(new Coordinates(45, 50));
        whenIAddAPoint(new Coordinates(16, 17));
        whenIAddAPoint(new Coordinates(90, 22));
        whenIAddAPoint(new Coordinates(5, 8));
        whenIAddAPoint(new Coordinates(27, 40));
        whenIAddAPoint(new Coordinates(78, 50));
        thenICanGetItsDepth(new Coordinates(10, 10), 3);
    }

    @Test
    public void giveNoGoodDepth() {
        givenIHaveANode(new Node(new Coordinates(0, 0), 100, 100));
        whenIAddAPoint(new Coordinates(10, 10));
        whenIAddAPoint(new Coordinates(60, 20));
        whenIAddAPoint(new Coordinates(80, 80));
        whenIAddAPoint(new Coordinates(20, 40));
        whenIAddAPoint(new Coordinates(45, 50));
        thenICanGetItsDepth(new Coordinates(8, 8), -1);
    }

    @Test
    public void giveTheGoodNeighbors() {
        givenIHaveANode(new Node(new Coordinates(0, 0), 100, 100));
        whenIAddAPoint(new Coordinates(10, 10));
        whenIAddAPoint(new Coordinates(60, 20));
        whenIAddAPoint(new Coordinates(80, 80));
        whenIAddAPoint(new Coordinates(20, 40));
        whenIAddAPoint(new Coordinates(45, 50));
        whenIAddAPoint(new Coordinates(16, 17));
        whenIAddAPoint(new Coordinates(90, 22));
        whenIAddAPoint(new Coordinates(5, 8));
        whenIAddAPoint(new Coordinates(27, 40));
        whenIAddAPoint(new Coordinates(78, 50));

        thenICanGetTheGoodNeighbors(new Coordinates(5, 8),new ArrayList<Coordinates>(Arrays.asList(new Coordinates(10, 10), new Coordinates(16, 17),new Coordinates(5, 8))));
    }

    private void givenIHaveANode(Node node) {
        this.node = node;
    }

    private void whenICreateItsSons() {
        this.node.createSons();

    }

    private void thenTheirOriginAre(Coordinates expectedcenter1, Coordinates expectedcenter2, Coordinates expectedcenter3, Coordinates expectedcenter4) {
        assertThat(node.getOriginInSons(0), is(equalTo(expectedcenter1)));
        assertThat(node.getOriginInSons(1), is(equalTo(expectedcenter2)));
        assertThat(node.getOriginInSons(2), is(equalTo(expectedcenter3)));
        assertThat(node.getOriginInSons(3), is(equalTo(expectedcenter4)));

    }

    private void thenTheLengthIs(int expectedLenght) {
        assertThat(node.getSons().get(0).getLenght(), is(equalTo(expectedLenght)));
    }

    private void givenIHaveANodeWhereCenterIsOdd() {
        node = new Node(new Coordinates(0, 0), 25, 25);
    }

    private void thenTheNewNodeAreNotOverlaping() {
        assertThat(node.getLenghtInSons(0) + node.getLenghtInSons(1), is(equalTo(node.getLenght())));
        assertThat(node.getWidthInSons(0) + node.getWidthInSons(2), is(equalTo(node.getWidth())));

    }

    private void whenIAddAPoint(Coordinates point) {
        node.addPoint(point);
    }

    private void itGetsIntoTheRightNode(int point) {
        assertThat((node.getPointXInPoint(point) >= node.getOriginX()) && (node.getPointXInPoint(point) <= node.getOriginX() + node.getLenght() / 2 - 1), is(true));
        assertThat((node.getPointYInPoint(point) >= node.getOriginY()) && (node.getPointYInPoint(point) <= node.getOriginY() + node.getWidth() / 2 - 1), is(true));

    }

    private void thenIHaveMyPointsInTheRightPlace() {
        assertThat(node.getPoint(0), is(equalTo(new Coordinates(45, 50))));
        assertThat(node.getSon(0).getPoint(0), is(equalTo(new Coordinates(10, 10))));
        assertThat(node.getSon(0).getPoint(1), is(equalTo(new Coordinates(20, 40))));
        assertThat(node.getSon(1).getPoint(0), is(equalTo(new Coordinates(60, 20))));
        assertThat(node.getSon(3).getPoint(0), is(equalTo(new Coordinates(80, 80))));

    }

    private void thenICanGetItsDepth(Coordinates point, int depth) {

        assertThat(node.getPointDepth(point), is(equalTo(depth)));
    }

    private void thenICanGetTheGoodNeighbors(Coordinates point, ArrayList<Coordinates> neighbors) {
        assertThat(node.getNeighbors(point), is(equalTo(neighbors)));
    }
}
