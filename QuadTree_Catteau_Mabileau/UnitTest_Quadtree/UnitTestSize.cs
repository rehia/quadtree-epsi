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
    public class UnitTestSize
    {
        [TestMethod]
        public void ShouldReturnHeightDivideBy2()
        {
            Size size = new Size(4, 5);

            double heightDividedBy2 = size.getHalfHeight();

            Assert.AreEqual(2, heightDividedBy2);
        }

        [TestMethod]
        public void ShouldReturnWithDivideBy2()
        {
            Size size = new Size(4, 10);

            double widthDividedBy2 = size.getHalfWidth();

            Assert.AreEqual(5, widthDividedBy2);
        }
    }
}
