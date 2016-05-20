using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QuadTree_BC
{
    public class Size
    {
        private double _height;
        private double _width;

        public Size(int height, int width)
        {
            _height = height;
            _width = width;
        }

        public double getHalfHeight()
        {
            return _height / 2;
        }
        public double getHalfWidth()
        {
            return _width / 2;
        }
    }
}
