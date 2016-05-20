import unittest
import Noeud as classNoeud
#import Quadtree as qt

class test(unittest.TestCase):

    def testCreationNoeud(self):
        n0 = classNoeud.Noeud(100,0,0,None)
        self.assertEqual(n0.type, 2)

    def testDivision(self):
        n0 = classNoeud.Noeud(100, 0, 0, None)
        n0.division()
        self.assertEquals(n0.listeFils[0].nom, "SW")
        self.assertEquals(n0.listeFils[1].nom, "SE")
        self.assertEquals(n0.listeFils[2].nom, "NE")
        self.assertEquals(n0.listeFils[3].nom, "NW")

    def testContientPoint(self):
        n0 = classNoeud.Noeud(100, 0, 0, None)
        self.assertEquals(n0.contains((10,20)),True)

    def testFilsContientLePointDansLeFilsNE(self):
        n0 = classNoeud.Noeud(100,0,0,None)
        n0.division()
        x = 51
        y = 51
        self.assertEquals(n0.listeFils[0].contains((x, y)), False)
        self.assertEquals(n0.listeFils[1].contains((x, y)), False)
        self.assertEquals(n0.listeFils[2].contains((x, y)), True)
        self.assertEquals(n0.listeFils[3].contains((x, y)), False)

    def testAjouterUnPoint(self):
        n0 = classNoeud.Noeud(100, 0, 0, None)
        n0.ajouterUnPoint(10,10)
        self.assertEquals(len(n0.listePoints),1)

    def testAjouter5points(self):
        n0 = classNoeud.Noeud(100, 0, 0, None)
        n0.ajouterUnPoint(1, 1)
        n0.ajouterUnPoint(49, 49)
        n0.ajouterUnPoint(49, 1)
        n0.ajouterUnPoint(1, 49)
        n0.ajouterUnPoint(20, 20)
        self.assertEquals(n0.listeFils[0].listeFils[0].listePoints,[(1,1),(20,20)])
        self.assertEquals(n0.listeFils[0].listeFils[1].listePoints,[(49,1)])
        self.assertEquals(n0.listeFils[0].listeFils[2].listePoints,[(49,49)])
        self.assertEquals(n0.listeFils[0].listeFils[3].listePoints,[(1,49)])

    def testPointAppartient(self):
        n0 = classNoeud.Noeud(100, 0, 0, None)
        n0.ajouterUnPoint(1.0, 1.0)
        self.assertEquals(n0.contiensLePoint((1,1)),True)

    #def testDoitEtreALaProfondeur2(self):
    #   q = qt.Quadtree(49,49)
    #  self.assertEquals(q.pointAuNiveau(q.listeDesNoeuds[0],(49,49)), "Le point (49,49) est a la profondeur : 2")

    #def testDoitAvoirDesFreres(self):
    #    q = qt.Quadtree(5, 5)
    #   self.assertEquals(q.pointLesPlusProches(q.listeDesNoeuds[0], (5, 5)), "Le point (49,49) est a la profondeur : 2")

if __name__ == '__main__':
    unittest.main()

