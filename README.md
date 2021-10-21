# MyResto

## Objectif final
Application web et mobile permettant :
* Aux visiteurs de voir la carte
* Aux clients de commander en ligne
* Au gérant d'analyser les commandes en cours et faire un prévisionnel de commandes


## Objectif version 1
Création d'une application web permettant :
* Aux visiteurs de voir la carte
* Aux clients de commander en ligne


## Application héberger
CLiquer sur [MyResto - Accueil](http://vps-0c0ccce5.vps.ovh.net:8081/MyResto/)


<hr>

## Pré-requis
Java : version `8.0.0` <br>
Maven : version `3.6.3` <br>
Système de gestion de Base de donnée: `MySQL` <br>
Container web Tomcat : version `9.0`


## Installation

1. **Récupéré le projet**
    * avec zip
        * Aller dans le projet [Github](https://github.com/Flav1-ann/MyResto).
        * Cliquer sur le bouton vert `code` puis télécharger le fichier zip.
        * Dans Windows extraire le fichier zip.
    * avec git
        * Ouvrir un terminale dans le dossier ou vous souhaiter avoir l'application
        * Copier/coller cette ligne dans le terminal `git clone https://github.com/Flav1-ann/MyResto.git`
    

2. **La base de donnée**
   * Allez dans le dossier `MyResto`
   * Utiliser le fichier `myresto.sql` pour généré la base de donnée
   * Ajouter dans le fichier .m2/settings.xml le code ci-dessous:
   ```
   <db.url>jdbc:mysql://localhost:3306/{{NOMBDD}}?serverTimezone=UTC</db.url>
   <db.driver>com.mysql.cj.jdbc.Driver</db.driver>
   <db.username>{{VOTRELOGIN}}</db.username>
   <db.password>{{VOTREPWD}}<db.password/>
   ```

## Utiliser l'application

**Generer le war:**
> mvn clean package

**Lancer l'application:**
* Allez dans le dossier `MyResto/Presentation/target`
* Copier/Coller le fichier `Presentation-1.0-SNAPSHOT.war` dans le répertoire `webapps` de votre container web. Vous devez avoir une instance de tomcat en fonctionnement.
* Ouvrir la page web http://localhost:8080/Presentation dans votre navigateur

##Deux choix sont alors possibles.

* Accéder à l'application avec le compte Gérant :

  > Identifiant : gerant
  >  <br/>Mot de passe : gerant

* Accéder à l'application avec le compte utilisateur :

  > Identifiant : dupont
  >  <br/>Mot de passe : dupont
  
## Tests unitaires
Pour lancer les tests unitaires
> mvn test


## Les logs
Vous pouvez retrouver les logs dans les différents sous-dossiers de MyResto dans un dossier nommer logs. <br>
Les logs sont ranger dans des dossiers en fonction du jour ou ils ont été générer.


## Spécificité fonctionnelle
* S'inscrire
* Se connecter
* Visualiser le profile
* Voir la carte
* Visualiser les produits
* Ajouter au panier les produits
* Visualiser le panier
* Commander ce qu'il y a dans le panier
* Voir les commandes
* Gerer les commandes

* Diagramme des cas d'utilisations : 

![](https://github.com/Flav1-ann/MyResto/blob/master/imgs-readme/Diagram_for_project-User_Case.png)

* Diagramme de classes Domaine : 

![](https://github.com/Flav1-ann/MyResto/blob/master/imgs-readme/Diagram_for_project-Class_Package_Domaine.png)

* Diagramme de classes Dao : 

![](https://github.com/Flav1-ann/MyResto/blob/master/imgs-readme/Diagram_for_project-Class_Package_Dao.png)

* Diagramme de classes Service : 

![](https://github.com/Flav1-ann/MyResto/blob/master/imgs-readme/Diagram_for_project-Class_Package_Service.png)

* Diagramme de classes Dto : 

![](https://github.com/Flav1-ann/MyResto/blob/master/imgs-readme/Diagram_for_project-Class_Package_Dto.png)

* Diagramme de classes Servlet : 

![](https://github.com/Flav1-ann/MyResto/blob/master/imgs-readme/Diagram_for_project-Class_Package_Servlet.png)


## Robustesse
* La gestion des exception


## Sécurité
* Les tests unitaire
* Les logs
