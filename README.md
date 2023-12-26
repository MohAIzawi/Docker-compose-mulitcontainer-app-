Aperçu de l'architecture de l'application
Introduction
Ce document décrit une application Docker multi-conteneurs intégrant une architecture de sécurité et une gestion de base de données. Il détaille les composants, la configuration de sécurité, et la manière d'opérer les environnements de développement et de production.

Technologies et Architecture
Frontend
Technologie : React
Containerisation : L'application React est construite pour la production et servie via un serveur Nginx, offrant des performances optimisées et une livraison efficace du contenu statique.
Backend
Technologie : Spring Boot (Java)
Containerisation : L'application Spring Boot est sécurisée avec Spring Security, implémentant une authentification basée sur JDBC et un cryptage des mots de passe avec BCrypt.
Configuration de Sécurité : Des configurations de sécurité détaillées sont appliquées pour renforcer l'authentification et l'autorisation au sein de l'application.
Base de Données
Technologie : MariaDB
Gestion de la Base de Données : La base de données utilise des volumes persistants pour la sauvegarde et la restauration des données, et est configurée avec des variables d'environnement pour une sécurité accrue.
Architecture Docker
Conteneurs :

frontend : Sert l'application React avec un serveur Nginx.
backend : Exécute l'application Spring Boot sécurisée avec Spring Security.
db : Héberge la base de données MariaDB avec une configuration de sécurité renforcée et une persistance des données.
Docker Compose : Orchestre la construction et le déploiement de l'application multi-conteneurs et gère les réseaux internes et les volumes pour la persistance des données.

Environnements
Environnement de Développement
Caractéristiques :
Le code source est monté directement pour permettre le rechargement en direct.
Les logs verbeux sont activés pour le débogage.
Utilisation d'une base de données légère avec des données de test pré-remplies.
Environnement de Production
Caractéristiques :
Les fichiers statiques sont compilés et servis via Nginx.
Les logs sont configurés pour capturer uniquement les informations critiques.
Configuration de MariaDB optimisée pour la production avec des volumes pour la persistance des données.
Guide Pratique
Lancement des Environnements
Développement
Variables d'Environnement : Configurer les variables pour le développement.
Docker Compose : docker-compose -f docker-compose-dev.yml up.
Production
Variables d'Environnement : Configurer les variables pour la production.
Docker Compose : docker-compose -f docker-compose-prod.yml up.
Vérification des Caractéristiques de Chaque Environnement
Développement
Rechargement en Direct : Vérifier que le code se recharge correctement après modifications.
Logs : Confirmer l'affichage des logs détaillés pour le débogage.
Production
Fichiers Statiques : Vérifier que les fichiers statiques sont correctement servis par Nginx.
Logs : Vérifier que seuls les logs pertinents sont enregistrés.
Base de Données : Confirmer la persistance des données et la sécurité de l'accès à la base de données.


Le fichier docker-compose.yml est utilisé pour définir et exécuter des applications multi-conteneurs avec Docker. Voici une explication de chaque section de votre fichier compose :

yaml
Copy code
version: '3'
Cette ligne spécifie la version de la syntaxe Docker Compose utilisée. La version 3 est une version stable et largement prise en charge qui permet de définir des services, des réseaux et des volumes.

yaml
Copy code
services:
La section services est utilisée pour configurer les conteneurs qui seront déployés. Chaque service correspond à un conteneur.

yaml
Copy code
  frontend:
    build: ./my-react-app
    ports:
      - "3000:80"
frontend: Nom du service pour l'application frontend.
build: ./my-react-app: Chemin vers le répertoire contenant le Dockerfile de l'application React. Docker construira l'image à partir de ce répertoire.
ports: La liste des ports qui seront exposés et mappés entre l'hôte et le conteneur. Le port 3000 de l'hôte est mappé au port 80 du conteneur, sur lequel le serveur web du conteneur écoute.
yaml
Copy code
  backend:
    build: ./spring-boot-app
    ports:
      - "8080:8080"
backend: Nom du service pour l'application backend.
build: ./spring-boot-app: Chemin vers le répertoire contenant le Dockerfile de l'application Spring Boot.
ports: Mapping du port 8080 de l'hôte vers le port 8080 du conteneur, qui est le port standard pour les applications Spring Boot.
yaml
Copy code
  db:
    image: mariadb
    environment:
      MYSQL_ROOT_PASSWORD: 1234
      MYSQL_DATABASE: react-app-database
      MYSQL_USER: Mohamed
      MYSQL_PASSWORD: 1234
    ports:
      - "3306:3306"
db: Nom du service pour le conteneur de base de données.
image: mariadb: Nom de l'image Docker de MariaDB à utiliser pour créer le conteneur.
environment: Variables d'environnement pour configurer le conteneur MariaDB, y compris le mot de passe root (MYSQL_ROOT_PASSWORD), le nom de la base de données par défaut (MYSQL_DATABASE), le nom d'utilisateur (MYSQL_USER) et le mot de passe de l'utilisateur (MYSQL_PASSWORD).
ports: Mapping du port 3306 (le port standard pour MySQL/MariaDB) de l'hôte vers le port 3306 du conteneur.
Ce fichier compose crée une pile de trois services qui fonctionnent ensemble pour servir une application web complète avec une interface frontend, un backend API et une base de données. Le service frontend sert l'interface utilisateur, le service backend gère la logique métier et le traitement des requêtes, et le service db gère le stockage et la récupération des données.