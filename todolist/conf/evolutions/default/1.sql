
CREATE TABLE IF NOT EXISTS `scalatestdb`.`todo` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `isComplete` TINYINT(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
  );

 
CREATE TABLE  IF NOT EXISTS `etudiant` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nomEtudiant` VARCHAR(45) NULL DEFAULT NULL,
  `prenomEtudiant` VARCHAR(45) NULL DEFAULT NULL,
  `NiveauEtude` VARCHAR(45) NULL DEFAULT NULL,
   `id_classe` INT(25) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT fkid_classe foreign KEY(id_classe) REFERENCES classe(id)

  
  );

CREATE TABLE  IF NOT EXISTS `professeur` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nomProfesseur` VARCHAR(45) NULL DEFAULT NULL,
  `prenomProfesseur` VARCHAR(45) NULL DEFAULT NULL,
  `grade` VARCHAR(45) NULL DEFAULT NULL,

  PRIMARY KEY (`id`));

  CREATE TABLE  IF NOT EXISTS `scalatestdb`. `salle` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `numeroSalle` VARCHAR(45) NULL DEFAULT NULL,
  `libelleSalle` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE  IF NOT EXISTS `scalatestdb`. `matiere` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `codeMatiere` VARCHAR(45) NULL DEFAULT NULL,
  `libelleMatiere` VARCHAR(45) NULL DEFAULT NULL,
  `coeffMatiere` INT(11) NOT NULL ,
  PRIMARY KEY (`id`));

CREATE TABLE  IF NOT EXISTS `scalatestdb`. `classe` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `libelleClasse` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE  IF NOT EXISTS `scalatestdb`. `evaluation` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `devoir1` INT(15) NULL DEFAULT NULL,
  `devoir2` VARCHAR(45) NULL DEFAULT NULL,
  `examen` INT(11) NOT NULL ,
  `id_etudiant` INT(11) NOT NULL ,
  `id_matiere` INT(11) NOT NULL ,
  PRIMARY KEY (`id`),
  CONSTRAINT fkid_etudiant foreign KEY(id_etudiant ) REFERENCES etudiant(id),
  CONSTRAINT fkid_matiere foreign KEY(id_matiere ) REFERENCES matiere(id)


  );
  CREATE TABLE  IF NOT EXISTS `scalatestdb`. `cours` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `date_cours` VARCHAR(65) NULL DEFAULT NULL,
  `heure_cours` VARCHAR(45) NULL DEFAULT NULL,
  `id_prof` INT(11) NOT NULL ,
  `id_salle` INT(11) NOT NULL ,
  `id_classe` INT(11) NOT NULL ,
  `id_matiere` INT(11) NOT NULL ,
  PRIMARY KEY (`id`),
  CONSTRAINT fkid_prof foreign KEY(id_prof ) REFERENCES professeur(id),
  CONSTRAINT fkid_matiere foreign KEY(id_matiere ) REFERENCES matiere(id),
  CONSTRAINT fkid_salle foreign KEY(id_salle ) REFERENCES salle(id),
  CONSTRAINT fkid_classe foreign KEY(id_classe ) REFERENCES classe(id)




  );
 
