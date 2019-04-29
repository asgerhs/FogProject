CREATE SCHEMA IF NOT EXISTS `fogproject`;
DROP TABLE IF EXISTS `fogproject`.`users`;
DROP TABLE IF EXISTS `fogproject`.`stock`;

CREATE TABLE `fogproject`.`stock` (
  `ref` VARCHAR(100) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `length` INT NOT NULL,
  `amount` INT NOT NULL,
  `unit` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ref`, `length`));

CREATE TABLE `fogproject`.`users` (
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `role` ENUM('ADMIN', 'SALESMAN') NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);

