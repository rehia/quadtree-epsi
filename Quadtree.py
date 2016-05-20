import Noeud
import random

class Quadtree():

    profondeurMax = 1
    feuilles = []
    listeDesNoeuds = []
    visited = []
    arbre = []


    def __init__(self,x,y):
        self.n0 = Noeud.Noeud(100,0,0,None)
        self.listeDesNoeuds.append(self.n0)
        self.n0.ajouterUnPoint(x,y)
        for i in range(50):
            self.n0.ajouterUnPoint(random.randrange(0,100),random.randrange(0,100))

    def parcours(self,noeud):
        self.arbre.append(noeud)
        print noeud.nom, noeud.listePoints, "Profondeur : " + str(noeud.profondeur)
        if noeud.listeFils[0] != None:
            for n in noeud.listeFils:
                if n not in self.visited:
                    self.visited.append(n)
                    self.parcours(n)
        else:
            self.parcours(noeud.noeudParent)


    def pointAuNiveau(self,noeud,(x,y)):
        if noeud.contiensLePoint((x, y)):
            print "Le point ("+str(x)+","+str(y)+") est a la profondeur : " + str(noeud.profondeur)
        else:
            if noeud.listeFils[0] != None:
                for n in noeud.listeFils:
                    self.pointAuNiveau(n, (x, y))

    def pointLesPlusProches(self,noeud,(x,y),isFils):
        if noeud.contiensLePoint((x, y)) or isFils:
            if len(noeud.listePoints) > 0 :
                print noeud.nom + " " + str(noeud.profondeur),noeud.listePoints,
            for n in noeud.listeFils:
                if n != None:
                    self.pointLesPlusProches(n,(x,y),True)
        else:
            if noeud.listeFils[0] != None:
                for n in noeud.listeFils:
                    self.pointLesPlusProches(n, (x, y),False)


if __name__ == '__main__':
    x = float(raw_input('X : '))
    y = float(raw_input('Y : '))
    q= Quadtree(x,y)
    #q.parcours(q.listeDesNoeuds[0])

    q.pointLesPlusProches(q.listeDesNoeuds[0],(x,y),False)

    q.pointAuNiveau(q.listeDesNoeuds[0],(x,y))

