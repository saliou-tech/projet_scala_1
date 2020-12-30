-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 27 déc. 2020 à 12:15
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bd_projet_scala`
--

-- --------------------------------------------------------

--
-- Structure de la table `apprendre`
--

DROP TABLE IF EXISTS `apprendre`;
CREATE TABLE IF NOT EXISTS `apprendre` (
  `codeMatiere` varchar(20) NOT NULL,
  `idEtudiant` varchar(20) NOT NULL,
  KEY `fkAprrendre` (`codeMatiere`),
  KEY `fkAprrendre1` (`idEtudiant`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
CREATE TABLE IF NOT EXISTS `etudiant` (
  `idEtudiant` varchar(20) NOT NULL,
  `nomEtudiant` varchar(50) NOT NULL,
  `prenomEtudiant` varchar(50) NOT NULL,
  `niveauEtude` varchar(50) NOT NULL,
  `numeroSalle` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idEtudiant`),
  KEY `fknumeroSalle` (`numeroSalle`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

DROP TABLE IF EXISTS `matiere`;
CREATE TABLE IF NOT EXISTS `matiere` (
  `codeMatiere` varchar(20) NOT NULL,
  `libelleMatiere` varchar(50) NOT NULL,
  `coeffMatiere` int(20) NOT NULL,
  PRIMARY KEY (`codeMatiere`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `professeur`
--

DROP TABLE IF EXISTS `professeur`;
CREATE TABLE IF NOT EXISTS `professeur` (
  `idProfesseur` varchar(10) NOT NULL,
  `nomProfesseur` varchar(50) NOT NULL,
  `prenomProfesseur` varchar(50) NOT NULL,
  `grade` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idProfesseur`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

DROP TABLE IF EXISTS `salle`;
CREATE TABLE IF NOT EXISTS `salle` (
  `numeroSalle` varchar(20) NOT NULL,
  `libelleSalle` varchar(20) NOT NULL,
  PRIMARY KEY (`numeroSalle`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
