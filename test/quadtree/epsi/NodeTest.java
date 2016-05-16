/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree.epsi;

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
        node = new Node(new Coordinates(0, 0), 100, 100);
    }

    @After
    public void tearDown() {
    }

    @Test

    public void placeSonsWell() {
        givenIHaveANode();
        whenICreateItsSons();
        thenTheirCenterAre(new Coordinates(0, 0), new Coordinates(50, 0), new Coordinates(0, 50), new Coordinates(50, 50));
    }

    @Test
    public void giveTheRightLength() {
        givenIHaveANode();
        whenICreateItsSons();
        thenTheLengthIs(50);
    }

    @Test
    public void divisionNotRound() {
        givenIHaveANodeWhereCenterIsOdd();
        whenICreateItsSons();
        thenTheNewNodeAreNotOveraping();
    }
@Test
public void addPointInTheRightNode(){
     givenIHaveANode();
     whenIAddAPoint(new Coordinates(12,26));
     itGetsIntoTheRightNode();
}
    private void givenIHaveANode() {
    }

    private void whenICreateItsSons() {
        this.node.createSons();

    }

    private void thenTheirCenterAre(Coordinates expectedcenter1, Coordinates expectedcenter2, Coordinates expectedcenter3, Coordinates expectedcenter4) {
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

    private void thenTheNewNodeAreNotOveraping() {
        assertThat(node.getLenghtInSons(0) + node.getLenghtInSons(1), is(equalTo(node.getLenght())));
        assertThat(node.getWidthInSons(0) + node.getWidthInSons(2), is(equalTo(node.getWidth())));

    }

    private void whenIAddAPoint(Coordinates point) {
        node.addPoint(point);
    }

    private void itGetsIntoTheRightNode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
