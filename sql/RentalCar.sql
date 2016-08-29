-- MySQL Script generated by MySQL Workbench
-- 08/30/16 00:36:22
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema summary_task4_car_rental
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `summary_task4_car_rental` ;

-- -----------------------------------------------------
-- Schema summary_task4_car_rental
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `summary_task4_car_rental` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `summary_task4_car_rental` ;

-- -----------------------------------------------------
-- Table `car`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `car` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `brend` VARCHAR(20) NOT NULL,
  `model` VARCHAR(50) NOT NULL,
  `qualityClass` ENUM('LUXURY', 'BUSINESS', 'ECONOMY') NOT NULL,
  `rentalCost` INT NOT NULL,
  `carStatus` ENUM('FREE', 'ON_REPAIR') NOT NULL,
  `yearOfIssue` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `car_busy_dates`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car_busy_dates` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `car_busy_dates` (
  `id` INT NULL,
  `car_id` INT NOT NULL,
  `busyDate` DATE NOT NULL,
  `order_id` INT NULL,
  PRIMARY KEY (`car_id`, `busyDate`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `car_id_idx` ON `car_busy_dates` (`car_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `passSeries` VARCHAR(20) NOT NULL,
  `passNumber` INT NOT NULL,
  `passSurname` VARCHAR(50) NOT NULL,
  `passName` VARCHAR(50) NOT NULL,
  `passPatronomic` VARCHAR(50) NOT NULL,
  `passDateOfBirth` DATE NOT NULL,
  `sex` ENUM('MALE', 'FEMALE') NOT NULL,
  `userBlocking` VARCHAR(10) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `role` ENUM('ADMIN', 'MANAGER', 'CLIENT') NOT NULL,
  `language` VARCHAR(20) NOT NULL,
  `confirmation` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `account` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `accountForRent` INT NOT NULL,
  `accountForRepair` INT NOT NULL,
  `accountRentPaid` VARCHAR(10) NOT NULL,
  `accountRepairPaid` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `car_id` INT NOT NULL,
  `client_id` INT NOT NULL,
  `presenceOfTheDriver` VARCHAR(10) NOT NULL,
  `startDate` DATE NOT NULL,
  `endDate` DATE NOT NULL,
  `account_id` INT NOT NULL,
  `orderStatus` ENUM('UNTREATED', 'ACTIVE', 'CLOSE', 'REJECTED') NOT NULL,
  `rejectionReason` VARCHAR(500) NOT NULL,
  `createOrderDate` DATE NOT NULL,
  `managerNameWhoClosedOrder` VARCHAR(50) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Data for table `car`
-- -----------------------------------------------------
START TRANSACTION;
USE `summary_task4_car_rental`;
INSERT INTO `car` (`id`, `brend`, `model`, `qualityClass`, `rentalCost`, `carStatus`, `yearOfIssue`) VALUES (5, 'Geely', 'MK', 'ECONOMY', 75, 'FREE', '2009-08-01');
INSERT INTO `car` (`id`, `brend`, `model`, `qualityClass`, `rentalCost`, `carStatus`, `yearOfIssue`) VALUES (6, 'Geely', 'CK', 'ECONOMY', 75, 'FREE', '2008-04-13');
INSERT INTO `car` (`id`, `brend`, `model`, `qualityClass`, `rentalCost`, `carStatus`, `yearOfIssue`) VALUES (9, 'BMW', '7 Series', 'LUXURY', 500, 'FREE', '2016-01-15');
INSERT INTO `car` (`id`, `brend`, `model`, `qualityClass`, `rentalCost`, `carStatus`, `yearOfIssue`) VALUES (10, 'BMW', '7 Series', 'LUXURY', 400, 'FREE', '2012-10-05');
INSERT INTO `car` (`id`, `brend`, `model`, `qualityClass`, `rentalCost`, `carStatus`, `yearOfIssue`) VALUES (13, 'Mercedes-Benz', 'E-class W213', 'LUXURY', 600, 'FREE', '2015-12-15');
INSERT INTO `car` (`id`, `brend`, `model`, `qualityClass`, `rentalCost`, `carStatus`, `yearOfIssue`) VALUES (15, 'Volkswagen', 'Passat B7', 'BUSINESS', 200, 'FREE', '2015-08-17');
INSERT INTO `car` (`id`, `brend`, `model`, `qualityClass`, `rentalCost`, `carStatus`, `yearOfIssue`) VALUES (16, 'Volkswagen', 'Jetta V', 'BUSINESS', 180, 'FREE', '2015-09-26');
INSERT INTO `car` (`id`, `brend`, `model`, `qualityClass`, `rentalCost`, `carStatus`, `yearOfIssue`) VALUES (17, 'Skoda', 'Fabia MK2', 'BUSINESS', 140, 'FREE', '2013-04-30');
INSERT INTO `car` (`id`, `brend`, `model`, `qualityClass`, `rentalCost`, `carStatus`, `yearOfIssue`) VALUES (44, 'Hyndai', 'Accent', 'BUSINESS', 120, 'FREE', '2013-05-19');
INSERT INTO `car` (`id`, `brend`, `model`, `qualityClass`, `rentalCost`, `carStatus`, `yearOfIssue`) VALUES (46, 'Lada', '2114', 'ECONOMY', 50, 'FREE', '2012-06-14');
INSERT INTO `car` (`id`, `brend`, `model`, `qualityClass`, `rentalCost`, `carStatus`, `yearOfIssue`) VALUES (47, 'Porsche', 'Cayenne (955)', 'LUXURY', 650, 'FREE', '2011-07-22');

COMMIT;


-- -----------------------------------------------------
-- Data for table `car_busy_dates`
-- -----------------------------------------------------
START TRANSACTION;
USE `summary_task4_car_rental`;
INSERT INTO `car_busy_dates` (`id`, `car_id`, `busyDate`, `order_id`) VALUES (276, 5, '2016-08-29', 86);
INSERT INTO `car_busy_dates` (`id`, `car_id`, `busyDate`, `order_id`) VALUES (277, 5, '2016-08-30', 86);
INSERT INTO `car_busy_dates` (`id`, `car_id`, `busyDate`, `order_id`) VALUES (278, 5, '2016-08-31', 86);
INSERT INTO `car_busy_dates` (`id`, `car_id`, `busyDate`, `order_id`) VALUES (311, 6, '2016-08-30', 88);
INSERT INTO `car_busy_dates` (`id`, `car_id`, `busyDate`, `order_id`) VALUES (312, 6, '2016-08-31', 88);
INSERT INTO `car_busy_dates` (`id`, `car_id`, `busyDate`, `order_id`) VALUES (313, 6, '2016-09-01', 88);
INSERT INTO `car_busy_dates` (`id`, `car_id`, `busyDate`, `order_id`) VALUES (314, 6, '2016-09-02', 88);
INSERT INTO `car_busy_dates` (`id`, `car_id`, `busyDate`, `order_id`) VALUES (315, 6, '2016-09-03', 88);
INSERT INTO `car_busy_dates` (`id`, `car_id`, `busyDate`, `order_id`) VALUES (316, 6, '2016-09-04', 88);
INSERT INTO `car_busy_dates` (`id`, `car_id`, `busyDate`, `order_id`) VALUES (323, 9, '2016-08-30', 92);
INSERT INTO `car_busy_dates` (`id`, `car_id`, `busyDate`, `order_id`) VALUES (324, 9, '2016-08-31', 92);
INSERT INTO `car_busy_dates` (`id`, `car_id`, `busyDate`, `order_id`) VALUES (NULL, 10, '2016-08-30', 94);
INSERT INTO `car_busy_dates` (`id`, `car_id`, `busyDate`, `order_id`) VALUES (NULL, 10, '2016-08-31', 94);
INSERT INTO `car_busy_dates` (`id`, `car_id`, `busyDate`, `order_id`) VALUES (319, 16, '2016-08-30', 90);
INSERT INTO `car_busy_dates` (`id`, `car_id`, `busyDate`, `order_id`) VALUES (320, 16, '2016-08-31', 90);
INSERT INTO `car_busy_dates` (`id`, `car_id`, `busyDate`, `order_id`) VALUES (274, 46, '2016-08-29', 85);
INSERT INTO `car_busy_dates` (`id`, `car_id`, `busyDate`, `order_id`) VALUES (275, 46, '2016-08-30', 85);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `summary_task4_car_rental`;
INSERT INTO `user` (`id`, `passSeries`, `passNumber`, `passSurname`, `passName`, `passPatronomic`, `passDateOfBirth`, `sex`, `userBlocking`, `password`, `email`, `role`, `language`, `confirmation`) VALUES (1, 'МН', 831327, 'Юшин', 'Роман', 'Владимирович', '1990-02-01', 'MALE', 'false', 'qwerty', 'yushin.khpi@gmail.com', 'ADMIN', 'ru', '');
INSERT INTO `user` (`id`, `passSeries`, `passNumber`, `passSurname`, `passName`, `passPatronomic`, `passDateOfBirth`, `sex`, `userBlocking`, `password`, `email`, `role`, `language`, `confirmation`) VALUES (2, 'МК', 111111, 'Юшина', 'Эмилия', 'Богдановна', '1990-08-09', 'FEMALE', 'true', 'qwerty', 'etik@gmail.com', 'MANAGER', 'ru', '');
INSERT INTO `user` (`id`, `passSeries`, `passNumber`, `passSurname`, `passName`, `passPatronomic`, `passDateOfBirth`, `sex`, `userBlocking`, `password`, `email`, `role`, `language`, `confirmation`) VALUES (3, 'RR', 222222, 'Horev', 'Alex', 'Sergeevish', '1991-01-01', 'MALE', 'false', 'qwerty', 'horev@rambler.ru', 'CLIENT', 'en', '');
INSERT INTO `user` (`id`, `passSeries`, `passNumber`, `passSurname`, `passName`, `passPatronomic`, `passDateOfBirth`, `sex`, `userBlocking`, `password`, `email`, `role`, `language`, `confirmation`) VALUES (4, 'FF', 123, 'Yakimenko', 'Igor', 'Anatolievich', '1991-02-10', 'MALE', 'true', 'yyyy', 'yak@gmail.com', 'CLIENT', 'ru', '');
INSERT INTO `user` (`id`, `passSeries`, `passNumber`, `passSurname`, `passName`, `passPatronomic`, `passDateOfBirth`, `sex`, `userBlocking`, `password`, `email`, `role`, `language`, `confirmation`) VALUES (7, 'hh', 12, 'q', 'q', 'q', '2016-08-08', 'MALE', 'false', 'rrrr', 'rr@rambler.ru', 'MANAGER', 'ru', '');

COMMIT;


-- -----------------------------------------------------
-- Data for table `account`
-- -----------------------------------------------------
START TRANSACTION;
USE `summary_task4_car_rental`;
INSERT INTO `account` (`id`, `accountForRent`, `accountForRepair`, `accountRentPaid`, `accountRepairPaid`) VALUES (93, 1200, 50, 'true', 'true');
INSERT INTO `account` (`id`, `accountForRent`, `accountForRepair`, `accountRentPaid`, `accountRepairPaid`) VALUES (94, 2000, 5000, 'true', 'true');
INSERT INTO `account` (`id`, `accountForRent`, `accountForRepair`, `accountRentPaid`, `accountRepairPaid`) VALUES (95, 240, 0, 'true', 'true');
INSERT INTO `account` (`id`, `accountForRent`, `accountForRepair`, `accountRentPaid`, `accountRepairPaid`) VALUES (98, 90, 0, 'true', 'true');
INSERT INTO `account` (`id`, `accountForRent`, `accountForRepair`, `accountRentPaid`, `accountRepairPaid`) VALUES (99, 225, 0, 'true', 'true');
INSERT INTO `account` (`id`, `accountForRent`, `accountForRepair`, `accountRentPaid`, `accountRepairPaid`) VALUES (100, 19200, 0, 'true', 'true');
INSERT INTO `account` (`id`, `accountForRent`, `accountForRepair`, `accountRentPaid`, `accountRepairPaid`) VALUES (101, 450, 0, 'true', 'true');
INSERT INTO `account` (`id`, `accountForRent`, `accountForRepair`, `accountRentPaid`, `accountRepairPaid`) VALUES (102, 2000, 123, 'true', 'true');
INSERT INTO `account` (`id`, `accountForRent`, `accountForRepair`, `accountRentPaid`, `accountRepairPaid`) VALUES (103, 720, 0, 'true', 'true');
INSERT INTO `account` (`id`, `accountForRent`, `accountForRepair`, `accountRentPaid`, `accountRepairPaid`) VALUES (104, 800, 0, 'true', 'true');
INSERT INTO `account` (`id`, `accountForRent`, `accountForRepair`, `accountRentPaid`, `accountRepairPaid`) VALUES (105, 2000, 0, 'true', 'true');
INSERT INTO `account` (`id`, `accountForRent`, `accountForRepair`, `accountRentPaid`, `accountRepairPaid`) VALUES (106, 2000, 0, 'true', 'true');
INSERT INTO `account` (`id`, `accountForRent`, `accountForRepair`, `accountRentPaid`, `accountRepairPaid`) VALUES (107, 1600, 0, 'false', 'true');

COMMIT;


-- -----------------------------------------------------
-- Data for table `order`
-- -----------------------------------------------------
START TRANSACTION;
USE `summary_task4_car_rental`;
INSERT INTO `order` (`id`, `car_id`, `client_id`, `presenceOfTheDriver`, `startDate`, `endDate`, `account_id`, `orderStatus`, `rejectionReason`, `createOrderDate`, `managerNameWhoClosedOrder`) VALUES (80, 13, 4, 'false', '2016-08-29', '2016-08-30', 93, 'CLOSE', 'no rejection reason', '2016-08-28', 'Юшина Эмилия');
INSERT INTO `order` (`id`, `car_id`, `client_id`, `presenceOfTheDriver`, `startDate`, `endDate`, `account_id`, `orderStatus`, `rejectionReason`, `createOrderDate`, `managerNameWhoClosedOrder`) VALUES (81, 9, 4, 'true', '2016-09-01', '2016-09-02', 94, 'CLOSE', 'Не оплачено.', '2016-08-28', 'Юшина Эмилия');
INSERT INTO `order` (`id`, `car_id`, `client_id`, `presenceOfTheDriver`, `startDate`, `endDate`, `account_id`, `orderStatus`, `rejectionReason`, `createOrderDate`, `managerNameWhoClosedOrder`) VALUES (82, 44, 4, 'false', '2016-08-30', '2016-08-31', 95, 'CLOSE', 'no rejection reason', '2016-08-28', 'Юшина Эмилия');
INSERT INTO `order` (`id`, `car_id`, `client_id`, `presenceOfTheDriver`, `startDate`, `endDate`, `account_id`, `orderStatus`, `rejectionReason`, `createOrderDate`, `managerNameWhoClosedOrder`) VALUES (85, 46, 4, 'false', '2016-08-29', '2016-08-30', 98, 'REJECTED', 'Не оплачено вовремя.', '2016-08-28', 'NULL');
INSERT INTO `order` (`id`, `car_id`, `client_id`, `presenceOfTheDriver`, `startDate`, `endDate`, `account_id`, `orderStatus`, `rejectionReason`, `createOrderDate`, `managerNameWhoClosedOrder`) VALUES (86, 5, 4, 'false', '2016-08-29', '2016-08-31', 99, 'ACTIVE', 'no rejection reason', '2016-08-28', 'NULL');
INSERT INTO `order` (`id`, `car_id`, `client_id`, `presenceOfTheDriver`, `startDate`, `endDate`, `account_id`, `orderStatus`, `rejectionReason`, `createOrderDate`, `managerNameWhoClosedOrder`) VALUES (87, 13, 4, 'false', '2016-09-01', '2016-10-02', 100, 'CLOSE', 'no rejection reason', '2016-08-28', 'Юшина Эмилия');
INSERT INTO `order` (`id`, `car_id`, `client_id`, `presenceOfTheDriver`, `startDate`, `endDate`, `account_id`, `orderStatus`, `rejectionReason`, `createOrderDate`, `managerNameWhoClosedOrder`) VALUES (88, 6, 4, 'false', '2016-08-30', '2016-09-04', 101, 'ACTIVE', 'no rejection reason', '2016-08-29', 'NULL');
INSERT INTO `order` (`id`, `car_id`, `client_id`, `presenceOfTheDriver`, `startDate`, `endDate`, `account_id`, `orderStatus`, `rejectionReason`, `createOrderDate`, `managerNameWhoClosedOrder`) VALUES (89, 9, 17, 'true', '2016-08-30', '2016-08-31', 102, 'CLOSE', 'no rejection reason', '2016-08-29', 'Юшина Эмилия');
INSERT INTO `order` (`id`, `car_id`, `client_id`, `presenceOfTheDriver`, `startDate`, `endDate`, `account_id`, `orderStatus`, `rejectionReason`, `createOrderDate`, `managerNameWhoClosedOrder`) VALUES (90, 16, 17, 'true', '2016-08-30', '2016-08-31', 103, 'REJECTED', 'неадекватный', '2016-08-29', 'NULL');
INSERT INTO `order` (`id`, `car_id`, `client_id`, `presenceOfTheDriver`, `startDate`, `endDate`, `account_id`, `orderStatus`, `rejectionReason`, `createOrderDate`, `managerNameWhoClosedOrder`) VALUES (91, 10, 17, 'false', '2016-08-30', '2016-08-31', 104, 'CLOSE', 'no rejection reason', '2016-08-29', 'Юшина Эмилия');
INSERT INTO `order` (`id`, `car_id`, `client_id`, `presenceOfTheDriver`, `startDate`, `endDate`, `account_id`, `orderStatus`, `rejectionReason`, `createOrderDate`, `managerNameWhoClosedOrder`) VALUES (92, 9, 3, 'true', '2016-08-30', '2016-08-31', 105, 'UNTREATED', 'no rejection reason', '2016-08-29', 'NULL');
INSERT INTO `order` (`id`, `car_id`, `client_id`, `presenceOfTheDriver`, `startDate`, `endDate`, `account_id`, `orderStatus`, `rejectionReason`, `createOrderDate`, `managerNameWhoClosedOrder`) VALUES (93, 9, 17, 'true', '2016-08-30', '2016-08-31', 106, 'UNTREATED', 'no rejection reason', '2016-08-29', 'NULL');
INSERT INTO `order` (`id`, `car_id`, `client_id`, `presenceOfTheDriver`, `startDate`, `endDate`, `account_id`, `orderStatus`, `rejectionReason`, `createOrderDate`, `managerNameWhoClosedOrder`) VALUES (94, 10, 17, 'true', '2016-08-30', '2016-08-31', 107, 'UNTREATED', 'no rejection reason', '2016-08-29', 'NULL');

COMMIT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;