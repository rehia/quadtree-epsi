@ECHO OFF
set argC=0
for %%x in (%*) do Set /A argC+=1
if %argC%==0 or %argC% GTR 2 (goto help)
if %argC%==1 (java -jar quadtree.jar %1)
if %argC%==2 (java -jar quadtree.jar %1 %2)
goto end
:help
echo SYNTAXE: quadtree.bat [options]
echo OPTIONS:
echo a: automatique
echo 	"Un quadtree est généré et 10 points choisis aléatoirement sont analysés par leur profondeur et leur proximité avec d'autres points."
echo i: interactif
echo 	"La génération du quadtree est laissée à l'utilisateur qui à ensuite la main pour demander l'analyse de points."
echo i t: semi automatique
echo 	"Le quadtree est généré puis l'utilisateur prend la main pour l'analyse des points."
:end
