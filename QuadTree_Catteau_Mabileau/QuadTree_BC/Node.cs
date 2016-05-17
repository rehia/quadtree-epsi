using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QuadTree_BC
{
    public class Node
    {
        private double _x;
        private double _y;
        private double _size;
        private double _halfSize;

        private List<Point>  _points { get; set; }
        private Dictionary<Loc, Node> _childs;
        
        public enum Loc 
        {
            SO, NO, NE, SE
        }

        public Node(Point xy, double size)
        {
            this._x = xy.X();
            this._y = xy.Y();
            this._size = size;
            this._halfSize = size / 2;
            this._points = new List<Point>();
            this._childs = new Dictionary<Loc, Node>();
        }
        

        public Dictionary<Loc, Node> getChilds()
        {
            return _childs;
        }

        private bool isNodeFull()
        {
            return _points.Count > 4;
        }

        public void addPoint(Point newPoint)
        {
            _points.Add(newPoint);
            if (isNodeFull() || haveChildren())
            {
                createChildrenNodes();
                addPointsToChildrens();
            }
        }

        private bool isContained(Point P)
        {
            if (P.X() > this._x
            && P.X() < this._x + _size
            && P.Y() > this._y
            && P.Y() < this._y + _size)
                return true;
            else
                return false;
        } 

        public int getNbPoint()
        {
            return _points.Count;
        }
        
        private bool havePoint()
        {
            return _points.Count > 1;
        }
        public bool haveChildren()
        {
            return _childs.Count > 0;
        }
         
        private void createChildrenNodes()
        {
            if (!haveChildren()) 
            {
                _childs.Add(Loc.NO, new Node(getNordOuest(), this._halfSize));
                _childs.Add(Loc.NE, new Node(getNordEst(),   this._halfSize));
                _childs.Add(Loc.SE, new Node(getSudEst(),    this._halfSize));
                _childs.Add(Loc.SO, new Node(getSudOuest(),  this._halfSize));
            } 
        }

        /// <summary>
        ///     Répartit les points courants dans les noeuds enfants.
        ///     Si le point est à cheval sur deux enfants il reste sur le parent 
        /// </summary>
        private void addPointsToChildrens()
        {
            for (int i = getNbPoint() - 1; i >= 0; i--)
            {
                foreach (Node child in _childs.Values)
                {
                    if (child.isContained(_points[i]))
                    {
                        _points[i].IncrementDepth();
                        child.addPoint(_points[i]);
                        _points.Remove(_points[i]);
                        break;
                    }
                }
            }
        }

        private Point getNordOuest()
        {
            return new Point(this._x, this._y + _halfSize);
        }
        private Point getNordEst()
        {
            return new Point(this._x + _halfSize, this._y + _halfSize);
        }
        private Point getSudOuest()
        {
            return new Point(this._x, this._y);
        }
        private Point getSudEst()
        {
            return new Point(this._x + _halfSize, this._y);
        }

        public void PrintListPoint()
        {
            foreach (Point point in _points)
            {
                point.ToString();
            }
        }
        public void printDepth(int depth, String indentation = "")
        {
            if (isNodeFull())
                Trace.WriteLine(indentation + "Node");
            else
            {
                foreach (Loc loc in Enum.GetValues(typeof(Loc)))
                {
                    if (haveChildren())
                    {
                        _childs[loc].printDepth(depth + 1, indentation + "  ");
                        foreach (Point point in _childs[loc]._points)
                        {
                            Console.WriteLine(indentation + point.ToString());
                        }
                        Console.WriteLine(indentation + loc + " Profondeur : " + depth + " Nombre de points : " + _childs[loc].getNbPoint());
                        Trace.WriteLine(indentation + "Node" +loc+ " " + depth);
                    }
                    else
                    {
                        Trace.WriteLine(indentation + "EmptyNode");
                    }
                }
            }
        }

        public Node getNodeIfHavePoint(Point PointtoFind)
        {
            Node result = null;
            if (this.NodeHavePoint(PointtoFind))
            {
                return this;
            }
            else
            {
                foreach (var child in _childs.Values)
                {
                    if (child.NodeHavePoint(PointtoFind))
                    {
                        return child;
                    }
                    else
                    {
                        if (child.getNodeIfHavePoint(PointtoFind) != null)
                        {
                            return child.getNodeIfHavePoint(PointtoFind);
                        }
                    }
                }
            }
            return result;
        }

        public bool NodeHavePoint(Point p)
        {
            bool result = false;
            foreach (var point in _points)
            {
                if (point.X() == p.X() && point.Y() == p.Y())
                {
                    result = true;
                    break;
                }
            }
            return result;
        }
        public int getNodeDepth()
        {
            if (_points.Count > 0)
                return _points[0].Depth();
            else
                return 0;
        }
        public List<Point> getPointsVoisins(Point fromPoint)
        {
            List<Point> listVoisins = new List<Point>();
            foreach (Point p in _points)
            {
                if (p != fromPoint)
                    listVoisins.Add(p);
            }
            return listVoisins;
        }
        public void printPointsVoisins()
        {
            foreach (Point p in _points)
                Console.WriteLine("P: " + p.ToString());
        }


    }
}
