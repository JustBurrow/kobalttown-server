-- MySQL Script generated by MySQL Workbench
-- Sat Aug  5 23:11:38 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema kobalttown
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema kobalttown
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `kobalttown` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
USE `kobalttown` ;

-- -----------------------------------------------------
-- Table `kobalttown`.`user_account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kobalttown`.`user_account` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 0,
  `create_utc` BIGINT NOT NULL,
  `update_utc` BIGINT NOT NULL,
  `modify_ts` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UQ_ACCOUNT_EMAIL` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kobalttown`.`user_activate_code`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kobalttown`.`user_activate_code` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `account` BIGINT UNSIGNED NOT NULL,
  `activate_code` VARCHAR(128) NOT NULL,
  `expire_utc` BIGINT NOT NULL,
  `used_utc` BIGINT NULL,
  `create_utc` BIGINT NOT NULL,
  `update_utc` BIGINT NOT NULL,
  `modify_ts` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `FK_ACTIVE_CODE_PK_ACCOUNT` (`account` ASC),
  CONSTRAINT `FK_ACTIVE_CODE_PK_ACCOUNT`
    FOREIGN KEY (`account`)
    REFERENCES `kobalttown`.`user_account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
