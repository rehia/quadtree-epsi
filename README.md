# quadtree-epsi
## Auteurs
* Dorian Delpino
* Sébastien Delots
* Maël Prothon

## Présentation
Pour ce projet nous avons décidé d'utiliser le language JAVA. 
Pour exécuter le binaire une fois compiler nous avons écrit un petit script Bash qui permet en lui passant quelques paramètres de lister les points générés par le programme, de connaitre la profondeur d'un point et de lister les points qui lui sont les plus proches.

## Versions Utilisés
* Ubuntu 16.04LTS et Windows 7
* NetBeans (8.1) et Eclipse MARS.2 (4.5.2)
* JAVA 8 et Openjdk (1.8.0_91)
* Bash (4.3.42)

## Documentation Succinte

1. Commencer par cloner ce repository
2. Le code est déja compiler pour l'executer se placer dans le "répertoire" dist du projet et utiliser le script ```./runquadtree -help```
3. Si vous voulez apporter des modification au projet Vous devez recompiler le projet et le script ./runquadtree sera alors supprimer pour le regenerer utiliser la commande suivante : 
```cd dist && cat ../exec.sh quadtree.jar > runquadtree && chmod +x runquadtreeè``` puis refaire l'étape 2


