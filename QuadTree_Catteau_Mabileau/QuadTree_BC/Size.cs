using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QuadTree_BC
{
    public class Size
    {
        public double _height { get; set; }
        public double _width { get; set; }

        public Size(int height, int width)
        {
            _height = height;
            _width = _width;
        }
        public double getHalf()
        {
            return _height / 2;
        }
        public double getHalfWidth()
        {
            return _width / 2;
        }
    }
}
