CREATE SCHEMA IF NOT EXISTS `fogproject`;
DROP TABLE IF EXISTS `fogproject`.`users`;
DROP TABLE IF EXISTS `fogproject`.`stock`;

CREATE TABLE `fogproject`.`stock` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `length` INT NULL,
  `qty` INT NOT NULL,
  `unit` VARCHAR(45) NOT NULL,
  `description` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `fogproject`.`users` (
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `role` ENUM('ADMIN', 'SALESMAN') NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);

