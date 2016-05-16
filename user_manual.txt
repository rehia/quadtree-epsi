Développement d'un QuadTree - Dans le cadre de l'apprentissage du cycle TDD

EPSI - I4 - Classe 2

Auteurs :
Raphaël Pastor (DEV)
Mourad Ben-Zaid (DEV)
Faudel Boukhateb (RESEAUX)

Installation
------------
Nous utilisons PHP.
Le programme s'exécute en console
Pour exécuter notre programme, sur Windows, téléchargez PHP puis indiquer dans la variable
d'environnement Path le chemin vers le bin de PHP.
Ainsi vous pouvez vous rendre dans notre dossier racine QuadTree et exécuter la commande :
php index.php

Nous affichons les 50 points du quadtree, avec la profondeur et les points les plus proches pour chacun.

Les méthodes permettants de connaitre la profondeur et les points les plus proches sont dans QuadTree/Quadtree/QuadtreeAbstract.php,
les méthodes sont getNearestPoints() et getProfondeurPoint()

Tests
-----

Pour exécuter les tests (situés dans le dossier QuadTree/Tests), nous utilisons PHPUnit
L'installation est facile voici un lien pour vous aider : https://phpunit.de/getting-started.html

Après avoir vérifier que phpunit est fonctionnel, rendez vous toujours dans la console

Dans le dossier Tests, nous avons crée deux classes de tests, TestBounds.php et TestQuadTree.php

Pour lancer les tests, placez vous dans le dossier Tests et exécuter les commandes :

phpunit TestBounds.php
phpunit TestQuadTree.php