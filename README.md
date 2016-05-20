Le projet est en python 2.7

Pour l'executer, il suffit de lancer le main.py

Explication succinte :

La ROOT node est au niveau 0
A la création, il y a 50 points dispatchés aléatoirement et 1 point spécifié par l'utilisateur
Les parcours (pointAuNiveau et pointLesPlusProches) se font de manière récursives

Optionnel:
	Il est possible d'afficher la création du quadtree grâce à la librairie turtle (language LOGO)
	pour se faire :
	
	#### Noeud.py #####
	
	def __init__(self,tailleCote,x,y,noeudParent,nom="ROOT"):
        self.tailleDuCote = tailleCote
        self.x = x
        self.y = y
        self.type = Noeud.FEUILLE
        self.nom=nom
        if noeudParent == None:
            self.profondeur = 0
        else:
            self.profondeur = noeudParent.profondeur + 1
        self.noeudParent = noeudParent
        self.listeFils = [None, None, None, None]
        self.listePoints = []
        #self.carre() <-- décommenter pour afficher la génération des carrés
		
    def ajouterUnPoint(self,x,y):
        if self.type ==  1 :
            for fils in self.listeFils:
                if fils.contains((x,y)):
                    fils.ajouterUnPoint(x,y)
        else:
            if len(self.listePoints) <4  :
                self.ajouterUnPointDansLaListeDePoints(x,y)
            else:
                self.division()
                self.ajouterUnPoint(x,y)
        #self.points(x,y) <-- décommenter pour afficher la génération des points