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
    public class UnitTestPoint
    {
        [TestMethod]
        public void ShouldIncrementDepth()
        {
            double x = 1.82;
            double y = 3.85;
            Point point = new Point(x, y);

            point.IncrementDepth();

            Assert.AreEqual(1, point.Depth());
        }
    }
}
