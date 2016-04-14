# quadtree-epsi
Projet de développement pour la mise en oeuvre de TDD et des principes de Clean Code, autour du Quadtree

## Contexte
Cet exercice noté a pour objectif de valider votre compréhension du Test Driven Development, et des principes de Clean Code.

Vous allez faire cet exercice à 2.

Le choix du langage est libre. MAIS vous devez fournir les conditions d'exécution de votre code (language, OS, runtime, avec leur numéro de version), un script en ligne de commande (sh ou powershell, pas d'interface web ou desktop), et une documentation succincte du script (soit fournie par le script lui-même, soit sous un format texte quelconque, markdown apprécié).

## Objectif
Vous devez implémenter un Quadtree. Un quadtree est une structure utilisée pour indexer des données géographiques, pour la détection de collisions dans les jeux, et d'autres applications encore.

![Exemple quadtree](https://upload.wikimedia.org/wikipedia/commons/thumb/8/8b/Point_quadtree.svg/300px-Point_quadtree.svg.png)

Voici l'énoncé :

Dans une grille de 100 x 100 points, on dispose 50 points (coordonnées X et Y) de manière aléatoire.

On prendra 10 points au hasard parmi les 50, et on cherchera à déterminer leur profondeur dans l'arbre, et les points les plus proches, en interrogeant le quadtree.

Le principe du quadtree est assez simple :

Au départ, le quadtree ne dispose que d'un seul noeud : c'est la grille elle même.

On ajoute les points les uns après les autres au quadtree.

Un noeud du quadtree ne peut disposer au maximum que de 4 points (convention prise pour l'exercice). Au bout du 5e point, le noeud se subdivise en 4 parties égales, devenant des noeuds enfants du noeud parent. Les points sont alors ventilés dans les 4 noeuds enfants, en fonction de leurs coordonnées. Si un point se trouve à cheval sur plusieurs noeuds enfants, il n'est pas ventilé, et reste associé au noeud parent.

De proche en proche, cette structure devient un arbre, dans laquelle une branche a 0 ou 4 descendants. A chaque noeud est associé une profondeur.

![Arbre](http://2012books.lardbucket.org/books/geographic-information-system-basics/section_08/4ef2103c1582b29f8a7c37c8e10006c9.jpg)

On doit pouvoir interroger le quadtree de 2 façons :

- d'une part, pour connaître la profondeur d'un point dans l'arbre, en passant les coordonnées X,Y du point. 
- d'autre part, pour connaître les points les plus proches d'un point donné, toujours grâce aux coordonnées X,Y de ce dernier. Les points les plus proches sont évidemment ceux appartenant au même noeud. Pour un point n'ayant pas été ventilé, car se trouvant à cheval sur plusieurs noeuds fils, les points les plus proches sont l'ensemble des points se trouvant dans les noeuds descendants (quelque soit leur profondeur).

## Livraison
Le script que vous allez livrer devra demander à l'utilisateur une action (profondeur ou points les plus proches), ainsi que des coordonnées X,Y. La documentation succincte associée devra indiquer comment lancer le script, quelles options passer (si nécessaire), et comment interagir avec le script. Les résultats seront eux aussi affichés dans la console.

De préférence, forkez ce repository, et utilisez le fork pour commiter votre code. Commitez à chaque cycle TDD (après le refactor, et avant le prochain red). Faites une pull request une fois que vous avez terminé. 

## Notation
Vous serez notés sur votre compréhension de la démarche TDD, et sur les principes de Clean Code. La performance du système n'entrera pas en ligne de compte, sauf s'il répond de manière anormalement lente (plus d'une trentaine de secondes... c'est large...).

Rappelez-vous les 2 choses suivantes : 

- "*First, make it work (and prove it by a test), then make it right*"
- Une code propre est avant tout un code testé

Bon courage !
