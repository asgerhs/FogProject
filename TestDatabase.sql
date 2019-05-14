DROP SCHEMA IF EXISTS `testdatabase`;
CREATE SCHEMA `testdatabase`;
USE `testdatabase` ;

CREATE TABLE `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `admin` TINYINT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `user` VALUES (null, 'abc', '123', '1');
INSERT INTO `user` VALUES (null, 'def', '321', '0');