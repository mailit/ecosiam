-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Dim 05 Janvier 2014 à 11:14
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `ecosiam`
--

-- --------------------------------------------------------

--
-- Structure de la table `absence`
--

CREATE TABLE IF NOT EXISTS `absence` (
  `isAbsent` tinyint(1) NOT NULL,
  `dateAbsence` date NOT NULL,
  `matiere` int(11) NOT NULL,
  `stagiaire` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

CREATE TABLE IF NOT EXISTS `classe` (
  `idClasse` int(11) NOT NULL AUTO_INCREMENT,
  `LibelleClasse` varchar(20) NOT NULL,
  `filiere` int(11) NOT NULL,
  PRIMARY KEY (`idClasse`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `classe`
--

INSERT INTO `classe` (`idClasse`, `LibelleClasse`, `filiere`) VALUES
(1, 'A', 3),
(2, 'C', 2),
(3, 'B', 2);

-- --------------------------------------------------------

--
-- Structure de la table `etablissement`
--

CREATE TABLE IF NOT EXISTS `etablissement` (
  `idEtablissement` int(11) NOT NULL AUTO_INCREMENT,
  `emailEtablissement` varchar(20) NOT NULL,
  `libelleEtablissement` varchar(20) NOT NULL,
  `telEtablissement` varchar(13) NOT NULL,
  `numAutorisation` varchar(11) NOT NULL,
  `ville` varchar(11) NOT NULL,
  `dateAutorisation` date NOT NULL,
  PRIMARY KEY (`idEtablissement`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `etablissement`
--

INSERT INTO `etablissement` (`idEtablissement`, `emailEtablissement`, `libelleEtablissement`, `telEtablissement`, `numAutorisation`, `ville`, `dateAutorisation`) VALUES
(2, 'kjk@ed', 'ecosiam1', '7878', '98999', '6', '2013-12-23'),
(5, 'ecosiam2@gmail.com', 'ecosiam2', '+8201', '3333', '6', '2014-01-13'),
(7, 'q@d.com', 'aaa', '+311333333333', '2323', '6', '2014-01-12');

-- --------------------------------------------------------

--
-- Structure de la table `filiere`
--

CREATE TABLE IF NOT EXISTS `filiere` (
  `idFiliere` int(11) NOT NULL AUTO_INCREMENT,
  `libelleFiliere` varchar(20) NOT NULL,
  `prixFormation` double NOT NULL,
  `dateCreationFiliere` date NOT NULL,
  `formation` int(11) NOT NULL,
  `responsableFiliere` int(11) DEFAULT NULL,
  PRIMARY KEY (`idFiliere`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `filiere`
--

INSERT INTO `filiere` (`idFiliere`, `libelleFiliere`, `prixFormation`, `dateCreationFiliere`, `formation`, `responsableFiliere`) VALUES
(2, 'Adobe', 3000, '2013-12-11', 2, NULL),
(3, 'automatisme', 4999, '2013-12-01', 4, NULL),
(4, 'info', 9000, '2013-12-10', 4, NULL),
(6, 'design', 2000, '2014-01-01', 1, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `formateur`
--

CREATE TABLE IF NOT EXISTS `formateur` (
  `idFomateur` int(11) NOT NULL AUTO_INCREMENT,
  `ribFormateur` varchar(26) NOT NULL,
  `dateNaissance` date NOT NULL,
  `utilisateur` int(11) NOT NULL,
  PRIMARY KEY (`idFomateur`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `formateur`
--

INSERT INTO `formateur` (`idFomateur`, `ribFormateur`, `dateNaissance`, `utilisateur`) VALUES
(1, '21', '1980-12-10', 16),
(4, '2342', '2014-01-17', 20),
(5, '12234', '1960-01-02', 21),
(6, '11', '2014-01-08', 34);

-- --------------------------------------------------------

--
-- Structure de la table `formateur_specialite`
--

CREATE TABLE IF NOT EXISTS `formateur_specialite` (
  `idFormateurSpecialite` int(11) NOT NULL AUTO_INCREMENT,
  `formateur` int(11) NOT NULL,
  `specialite` int(11) NOT NULL,
  PRIMARY KEY (`idFormateurSpecialite`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `formateur_specialite`
--

INSERT INTO `formateur_specialite` (`idFormateurSpecialite`, `formateur`, `specialite`) VALUES
(2, 1, 3),
(3, 1, 2),
(4, 4, 6),
(5, 5, 8);

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE IF NOT EXISTS `formation` (
  `idFormation` int(11) NOT NULL AUTO_INCREMENT,
  `libelleFormation` varchar(20) NOT NULL,
  `dateCreationFormation` date NOT NULL,
  `etablissement` int(11) NOT NULL,
  `administrateur` int(11) DEFAULT NULL,
  PRIMARY KEY (`idFormation`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `formation`
--

INSERT INTO `formation` (`idFormation`, `libelleFormation`, `dateCreationFormation`, `etablissement`, `administrateur`) VALUES
(1, 'technicien', '2013-12-03', 2, 0),
(2, 'professionnelle', '2013-12-09', 5, 0),
(4, 'ingenieur', '2013-12-03', 2, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

CREATE TABLE IF NOT EXISTS `matiere` (
  `idMatiere` int(11) NOT NULL AUTO_INCREMENT,
  `CoeffMatiere` double NOT NULL,
  `libelleMatiere` varchar(20) NOT NULL,
  `formateur` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMatiere`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `matiere`
--

INSERT INTO `matiere` (`idMatiere`, `CoeffMatiere`, `libelleMatiere`, `formateur`) VALUES
(1, 4, 'routage', 1),
(2, 3, 'C++', 1),
(3, 2, 'c', 1),
(4, 10, 'java', 4),
(5, 3, 'bureautique', 1);

-- --------------------------------------------------------

--
-- Structure de la table `matiere_filiere`
--

CREATE TABLE IF NOT EXISTS `matiere_filiere` (
  `idMatiereFiliere` int(11) NOT NULL AUTO_INCREMENT,
  `matiere` int(11) NOT NULL,
  `filiere` int(11) NOT NULL,
  PRIMARY KEY (`idMatiereFiliere`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Contenu de la table `matiere_filiere`
--

INSERT INTO `matiere_filiere` (`idMatiereFiliere`, `matiere`, `filiere`) VALUES
(8, 1, 3),
(9, 4, 3),
(10, 4, 4),
(11, 2, 4),
(12, 2, 6);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `idRole` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(11) NOT NULL,
  `description` varchar(50) NOT NULL,
  PRIMARY KEY (`idRole`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `role`
--

INSERT INTO `role` (`idRole`, `role`, `description`) VALUES
(3, 'Stagiaire', 'rôle attribué aux stagiaires'),
(4, 'Formateur', 'rôle attribué aux enseignants'),
(6, 'DP', 'Directeur pédagogique'),
(7, 'President', 'super root');

-- --------------------------------------------------------

--
-- Structure de la table `specialite`
--

CREATE TABLE IF NOT EXISTS `specialite` (
  `idSpecialite` int(11) NOT NULL AUTO_INCREMENT,
  `libelleSpecialite` varchar(20) NOT NULL,
  PRIMARY KEY (`idSpecialite`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `specialite`
--

INSERT INTO `specialite` (`idSpecialite`, `libelleSpecialite`) VALUES
(2, 'automatisme'),
(3, 'dev web'),
(4, 'OS'),
(5, 'SOA'),
(6, 'IHM'),
(8, 'Genielogiciel');

-- --------------------------------------------------------

--
-- Structure de la table `stagiaire`
--

CREATE TABLE IF NOT EXISTS `stagiaire` (
  `idStagiaire` int(11) NOT NULL AUTO_INCREMENT,
  `utilisateur` int(11) NOT NULL,
  `cneStagiaire` double NOT NULL,
  `dateInscription` date NOT NULL,
  `dateNaissance` date NOT NULL,
  `classe` int(11) NOT NULL,
  PRIMARY KEY (`idStagiaire`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Contenu de la table `stagiaire`
--

INSERT INTO `stagiaire` (`idStagiaire`, `utilisateur`, `cneStagiaire`, `dateInscription`, `dateNaissance`, `classe`) VALUES
(12, 22, 2918776789, '2014-01-01', '1990-01-29', 1),
(13, 30, 1087665434, '2014-01-01', '1990-09-14', 1),
(15, 33, 1098998988, '2014-01-14', '2014-01-18', 1);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `emailUser` varchar(20) NOT NULL,
  `imageUser` varchar(255) DEFAULT NULL,
  `loginUser` varchar(20) NOT NULL,
  `nomUser` varchar(20) NOT NULL,
  `passwordUser` varchar(20) NOT NULL,
  `prenomUser` varchar(20) NOT NULL,
  `telUser` varchar(13) NOT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=35 ;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`idUser`, `emailUser`, `imageUser`, `loginUser`, `nomUser`, `passwordUser`, `prenomUser`, `telUser`, `role`) VALUES
(16, 'prof@gmail.com', NULL, 'prof', 'wldprof', 'prof', 'prof', '002003', 4),
(20, 'talbi@gmail.com', NULL, 'talbi', 'sarah', 'sarah', 'talbi', '+212619786656', 4),
(21, 'mouhoub@gmail.com', NULL, 'mouhoub', 'Mouhoub', 'mouhoub', 'Mohamed', '+212619778944', 4),
(22, 'derpina@gmail.com', NULL, 'derpina', 'bentdeprina', 'derpina', 'derpina', '+212619653566', 3),
(23, 'dr.sarah19@gmail.com', NULL, 'boss', 'big boss', 'boss', 'boss', '+212619653589', 7),
(24, 'derp@gmail.com', NULL, 'derp', 'derp', 'derp', 'derp', '+212619653599', 6),
(25, 'dp@gmail.com', NULL, 'dp', 'dp', 'dp', 'dp', '+212619653566', 6),
(30, 'fstamine@gmail.com', NULL, 'firewall', 'derp', 'gotohell', 'derpp', '+212619653566', 3),
(33, 'dr.sarah19@gmail.com', NULL, 'hiya', 'houwa', 'houwa', 'hiya', '+212619653566', 3),
(34, 'dr.sarah19@gmail.com', NULL, 'lala', 'lili', 'lili', 'lala', '+212619653566', 4);

-- --------------------------------------------------------

--
-- Structure de la table `ville`
--

CREATE TABLE IF NOT EXISTS `ville` (
  `idVille` int(11) NOT NULL AUTO_INCREMENT,
  `nomVille` varchar(11) NOT NULL,
  PRIMARY KEY (`idVille`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Contenu de la table `ville`
--

INSERT INTO `ville` (`idVille`, `nomVille`) VALUES
(6, 'Temara'),
(10, 'Rabat');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
