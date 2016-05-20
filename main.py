import Quadtree as qt
import os

clear = lambda: os.system('cls')
a = 0
while int(a) <1 or int(a)>3 :
    print "Bienvenue dans le QuadTree !"
    print "Vous avez 3 choix possibles :"
    print "1) Les points les plus proches (par default)"
    print "2) La profondeur d'un point"
    print "3) Les deux :)"
    a = raw_input("A vous de choisir ! : ")
    clear()

print "Ok vous avez choisis le "+str(a)
print "Initialisation..."
x = float(raw_input('X : '))
y = float(raw_input('Y : '))
q= qt.Quadtree(x,y)
if int(a) == 1:
    print "Les points les plus proches sont :"
    q.pointLesPlusProches(q.listeDesNoeuds[0], (x, y), False)
elif int(a) == 2 :
    print "La profondeur du point est :"
    q.pointAuNiveau(q.listeDesNoeuds[0], (x, y))
else:
    print "Les points les plus proches sont :"
    q.pointLesPlusProches(q.listeDesNoeuds[0], (x, y), False)
    print "et sa profondeur :"
    q.pointAuNiveau(q.listeDesNoeuds[0], (x, y))

os.system("pause")
clear()

#q.parcours(q.listeDesNoeuds[0])


