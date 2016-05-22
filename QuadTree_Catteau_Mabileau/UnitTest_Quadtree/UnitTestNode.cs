using Microsoft.VisualStudio.TestTools.UnitTesting;
using QuadTree_BC;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UnitTest_Quadtree
{
    [TestClass]
    public class UnitTestNode
    {

        [ClassInitialize]
        public static void setUp(TestContext context)
        {
          Node node = new Node(new Point(0, 0), 100);
        }

        [TestMethod]
        public void ShouldHave1Point()
        {
            double x = 1.82;
            double y = 3.85;
            Point point = new Point(1, 2);

            Node node = new Node(new Point(x, y), 3);
            node.addPoint(point);
            int expected = 1;

            Assert.AreEqual(expected, 1);
        }


        [TestMethod]
        public void PlaceMultiplePointAndSplitIntoChildrens()
        {
            Random random = new Random();
            Quadtree tree = new Quadtree(100);
            tree.addPoint(new Point(1, 1));
            tree.addPoint(new Point(10, 1));
            tree.addPoint(new Point(20, 20));
            tree.addPoint(new Point(30, 30));
            //4 points & no children
            Assert.AreEqual(4, tree.getNbPoint());
            Assert.AreEqual(false, tree.haveChildren());

            tree.addPoint(new Point(1, 4));
            //0 point & 4 childrens           
            Assert.AreEqual(0, tree.getNbPoint());
            Assert.AreEqual(true, tree.haveChildren());
            Assert.AreEqual(4, tree.getChilds().Count);

            //Still 0 point, all added in childrens nodes
            tree.addPoint(new Point(40, 40));
            Assert.AreEqual(0, tree.getNbPoint());
            tree.addPoint(new Point(4, 3));
            tree.addPoint(new Point(4, 10));
            tree.addPoint(new Point(5, 4));
            Assert.AreEqual(0, tree.getNbPoint());
        }

        [TestMethod]
        public void ShouldHave4Childs()
        {
            Node node = new Node(new Point(0, 0), 100);
            node.addPoint(new Point(20, 20));
            node.addPoint(new Point(40, 20));
            node.addPoint(new Point(20, 80));
            node.addPoint(new Point(80, 20));
            node.addPoint(new Point(80, 80));
            
            Assert.AreEqual(4, node.getChilds().Count);
            Assert.AreEqual(0, node.getNbPoint());
        }

        [TestMethod]
        public void ShouldHave0PointAndChildrens()
        {
            Node node = new Node(new Point(0, 0), 100);
            node.addPoint(new Point(20, 20));
            node.addPoint(new Point(40, 20));
            node.addPoint(new Point(20, 80));
            node.addPoint(new Point(80, 20));
            node.addPoint(new Point(80, 80));
            
            Assert.AreEqual(0, node.getNbPoint());
        }

        [TestMethod]
        public void ShouldHave1PointAndChildrens()
        {
            Node node = new Node(new Point(0, 0), 100);
            node.addPoint(new Point(20, 20));
            node.addPoint(new Point(40, 20));
            node.addPoint(new Point(50, 50));
            node.addPoint(new Point(80, 20));
            node.addPoint(new Point(80, 80));

            Assert.AreEqual(1, node.getNbPoint());
        }

        [TestMethod]
        public void ShouldHave3Voisins()
        {
            Node node = new Node(new Point(0, 0), 100);
            node.addPoint(new Point(20, 20));
            node.addPoint(new Point(40, 20));
            node.addPoint(new Point(40, 40));

            Assert.AreEqual(3, node.getPointsVoisins(new Point(80, 80)).Count);
        }

        [ClassCleanup]
        public static void ClassCleanup() { }
    }
}
