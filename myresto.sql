-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : lun. 05 juil. 2021 à 11:30
-- Version du serveur :  8.0.25-0ubuntu0.20.04.1
-- Version de PHP : 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `myresto`
--

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

CREATE DATABASE IF NOT EXISTS myresto;
USE myresto;

CREATE TABLE `category` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `image` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `category`
--

INSERT INTO `category` (`id`, `name`, `image`) VALUES
(1, 'entree', NULL),
(2, 'boisson', NULL),
(3, 'plat', NULL),
(4, 'dessert', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `listproducts`
--

CREATE TABLE `listproducts` (
  `id_product` int NOT NULL,
  `id_order` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `listproducts`
--

INSERT INTO `listproducts` (`id_product`, `id_order`) VALUES
(2, 960),
(2, 960),
(2, 960),
(2, 24888),
(2, 39205),
(2, 84012),
(2, 99463),
(3, 24888),
(3, 24888),
(3, 39205),
(3, 84012),
(3, 99463),
(4, 84012),
(4, 84012),
(4, 85414),
(4, 99463),
(5, 39205),
(5, 99463);

-- --------------------------------------------------------

--
-- Structure de la table `order_product`
--

CREATE TABLE `order_product` (
  `id` int NOT NULL,
  `id_user` int NOT NULL,
  `date` date DEFAULT NULL,
  `status` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `order_product`
--

INSERT INTO `order_product` (`id`, `id_user`, `date`, `status`) VALUES
(960, 2, '3921-02-12', 'CLOSE'),
(24888, 2, '3921-02-12', 'NEW'),
(39205, 2, '2021-07-01', 'NEW'),
(84012, 2, '3921-02-12', 'NEW'),
(85414, 3, '2021-07-02', 'NEW'),
(99463, 2, '2021-06-30', 'NEW');

-- --------------------------------------------------------

--
-- Structure de la table `product`
--

CREATE TABLE `product` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` float NOT NULL,
  `description` text NOT NULL,
  `image` varchar(255) NOT NULL,
  `id_category` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `product`
--

INSERT INTO `product` (`id`, `name`, `price`, `description`, `image`, `id_category`) VALUES
(2, 'Coca', 1, 'CocaCola ', 'cocacola.jpg', 2),
(3, 'Carottes', 1, 'Carottes vichy', 'carotte-vichy.jpg', 1),
(4, 'Pizza', 12, 'Pizza avec des olives', 'blog-img-05.jpg', 3),
(5, 'Fraises', 5, 'Fraises fraiche', 'fraise.jpeg', 4),
(6, 'Coulis de fraises', 5, 'Coulis de fraises maison', 'img-02.jpg', 4),
(7, 'hamburger', 20, 'hamburger avec des ingrédients maison ', '115-large_default.jpg', 3),
(8, 'tarte citron meringue', 10, 'Tarte citron meringue avec chocolat en poudre', '45-large_default.jpg', 4),
(9, 'Tapas', 15, 'Multitude de tapas', 'blog-img-03.jpg', 3),
(10, 'Ailes de poulet ', 9, 'Ailes de poulet à partager', 'blog-img-02.jpg', 1),
(11, 'paella espagnole', 16, 'paella espagnole produits frais', 'gallery-img-04.jpg', 3),
(12, 'Salade guacamole', 15, 'Salade guacamole avec émincer de poulet\r\n', 'gallery-img-05.jpg', 3),
(13, 'Moules du pays', 13, 'Moules de Bretagne ', 'gallery-img-01.jpg', 1);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `image` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `firstname`, `lastname`, `login`, `address`, `role`, `password`, `salt`, `image`) VALUES
(2, 'Flavien', 'Annaix', 'Flavien', 'flavien@gmail.com', 'ADMIN', 'Mw4uDFq6BigZZ122/UVygMWaDplt7g/SKuGicbyGin4=', 'ylo6tslgE0WZl2Gxo4Xi5s1JXUQ=', NULL),
(3, 'Flavien', 'Annaix', 'FlavienUser', 'flavien@gmail.com', 'USER', 'Mw4uDFq6BigZZ122/UVygMWaDplt7g/SKuGicbyGin4=', 'ylo6tslgE0WZl2Gxo4Xi5s1JXUQ=', NULL),
(4, 'gerant', 'gerant', 'gerant', 'my resto', 'ADMIN', 'G2dsnz73eIbrEV3/PMdSn/sgbI+sxO9LOLJprHWtXes=', 'nFTn/bX8BLba7NdEn74XvcfwKAg=', NULL),
(5, 'Jean', 'dupont', 'dupont', 'dupont', 'USER', 'JFkIZwORio+/711IUD/K6iBmQnTMPQG0n/QdP/0xFxk=', 'OuI6NEG85IFHgp4wV2aYxnryylQ=', NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `listproducts`
--
ALTER TABLE `listproducts`
  ADD KEY `id_product` (`id_product`,`id_order`) USING BTREE,
  ADD KEY `fk_order` (`id_order`);

--
-- Index pour la table `order_product`
--
ALTER TABLE `order_product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`) USING BTREE;

--
-- Index pour la table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_category` (`id_category`) USING BTREE;

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `category`
--
ALTER TABLE `category`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `order_product`
--
ALTER TABLE `order_product`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99464;

--
-- AUTO_INCREMENT pour la table `product`
--
ALTER TABLE `product`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `listproducts`
--
ALTER TABLE `listproducts`
  ADD CONSTRAINT `fk_order` FOREIGN KEY (`id_order`) REFERENCES `order_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_product` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `order_product`
--
ALTER TABLE `order_product`
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `fk_category` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
