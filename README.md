# Exercice11

Votre grand-mère possède une superbe collection d'ustensiles anciens ayant beaucoup de valeur, et elle aimerait pouvoir les référencer et connaître leurs valeurs. Pour ce faire, vous lui proposez d'écrire un programme Java.

Comme votre grand-mère ne veut pas mettre les deux pieds dans le plat et écrire du code Java, vous lui demandez de lister ses ustensile dans le tableau suivant : Liste des ustensiles de grand-mère. Votre programme Java devra donc lire ce fichier pour acquérir les données nécessaire à l'exécution du programme.
Partie 1 : Définir les objets représentant les ustensiles

Pour commencer, il faut réfléchir à la hierarchie d'objets permettant de représenter les différents ustensiles. Vous avez les informations suivantes sur ceux-ci :

    Tout ustensile possède une année de fabrication
    La valeur de l'ustensile se calcul comme suit :
        S'il a été fabriqué il y a moins de 50 ans, il ne vaut rien (0)
        S'il a été fabriqué il y a plus de 50 ans, sa valeur est égale à anneeCourante - anneeFabrication - 50
        Les assiettes carrés sont particulière car plus rare, leur valeur est de 5 fois sa valeur si elle était ronde
    Une assiette est un type d'ustensile et peut être de forme carré ou ronde
        Une assiette ronde a un rayon qui permet de calculer sa surface
        Une assiette carré a une cote qui permet de calculer sa surface
    Une cuillère a une longueur

Partie 2 : Lire un fichier de type tableur

Le document comportant les données est un "spreadsheet" en ligne qui est lisible par une API Java spéciale, mais nous allons préférer pratiquer la lecture d'un fichier CSV (Comma Separated Values) qui ne nécessite pas de librairie supplémentaire.

Une fois sur le document google de la liste des ustensiles, vous pouvez faire un export CSV depuis le menu Fichier->Télécharger au format->Valeurs séparées par des virgules. Une fois le fichier CSV ajouté à votre projet, il faut maintenant écrire le code Java permettant de le lire et d'analyser ("parsing") les données.

Aide :

    Pour lire un fichier, on a besoin d'un objet Java qui représente un flux d'entrée (java.io.InputStream)
        On distingue deux moyens principaux :
            Par le système new java.io.File(path) puis new java.io.FileInputStream(path)
            Par le classpath java.lang.ClassLoader.getResourceAsStream(path). A vous de choisir la méthode la plus adaptée dans ce projet. N'oubliez pas que le fichier est sensé évoluer au fur et à mesure que votre grand-mère les référence.
        Il faut ensuite utiliser un objet qui hérite de la classe abstraite java.io.Reader afin de lire dans le flux
            Selon le type de flux que vous avez récupéré, il faut utiliser l'objet reader adapté
        On encapsule ensuite le reader dans une classe un peu plus avancée permettant de lire des lignes complètes dans le flux : java.io.BufferedReader
    Il faut ensuite transformer ces données récupérées pour chaque ligne sous forme de chaînes de caractères (java.util.List<String>)
        Chaque ligne récupérée dans le fichier correspond à un objet d'ustensile
        Chaque colonne de la ligne est séparée par le caractère ','. Il faut donc utiliser la méthode java.lang.String.split()
        La colonne type permet de déterminer quelle classe utiliser

Partie 3 : Afficher les données demandées

Votre grand-mère aimerait avoir les informations suivantes lorsqu'elle lance le programme :

    Le nombre total de cuillères
    La surface totale de toutes les assiettes
        Pour une assiette ronde : Math.PI * rayon * rayon
        Pour une assiette carré : cote * cote
    La valeur totale de tous les ustensiles

Exemple de résultat d'exécution :

Il y a 2 cuillères.
Surface totale des assiettes : 344.63961941597415
Valeur totale de la collection : 395.0
