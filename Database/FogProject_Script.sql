CREATE SCHEMA IF NOT EXISTS `fogproject`;
DROP TABLE IF EXISTS `fogproject`.`users`;
DROP TABLE IF EXISTS `fogproject`.`requests`;
DROP TABLE IF EXISTS `fogproject`.`stockToCategory`;
DROP TABLE IF EXISTS `fogproject`.`categories`;
DROP TABLE IF EXISTS `fogproject`.`stock`;

CREATE TABLE `fogproject`.`stock` (
  `ref` VARCHAR(100) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `length` INT NOT NULL,
  `amount` INT NOT NULL,
  `unit` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ref`)
);

CREATE TABLE `fogproject`.`categories` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY(`id`)
);

CREATE TABLE `fogproject`.`stockToCategory` (
  `stockRef` VARCHAR(100) NOT NULL,
  `categoryId` INT NOT NULL,
  PRIMARY KEY (`stockRef`, `categoryId`),
  INDEX `categoryId_FK_idx` (`categoryId` ASC) VISIBLE,
  CONSTRAINT `stockRef_FK`
    FOREIGN KEY (`stockRef`)
    REFERENCES `fogproject`.`stock` (`ref`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `categoryId_FK`
    FOREIGN KEY (`categoryId`)
    REFERENCES `fogproject`.`categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE `fogproject`.`requests` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `width` INT NOT NULL,
    `length` INT NOT NULL,
    `shedWidth` INT NOT NULL,
    `shedLength` INT NOT NULL,
    `roof` VARCHAR(100) NOT NULL,
    `angle` INT NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    `address` VARCHAR(100) NOT NULL,
    `zipCity` VARCHAR(200) NOT NULL,
    `phone` VARCHAR(30) NOT NULL, 
    `email` VARCHAR(200) NOT NULL,  
    `note` VARCHAR(500) NOT NULL, 
    PRIMARY KEY(`id`)
);

CREATE TABLE `fogproject`.`users` (
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `role` ENUM('ADMIN', 'SALESMAN') NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE
);

