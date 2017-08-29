-- MySQL Script generated by MySQL Workbench
-- Sat Aug 19 09:47:09 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema kobalttown
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `kobalttown`;

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


-- -----------------------------------------------------
-- Table `kobalttown`.`cfg_principal_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kobalttown`.`cfg_principal_type` (
  `id` INT UNSIGNED NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `modify_ts` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UQ_PRINCIPAL_TYPE_NAME` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kobalttown`.`user_account_principal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kobalttown`.`user_account_principal` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `account` BIGINT UNSIGNED NOT NULL,
  `principal_type` INT UNSIGNED NOT NULL DEFAULT 0,
  `public_key` VARCHAR(255) NOT NULL,
  `private_key` VARCHAR(60) NOT NULL,
  `create_utc` BIGINT NOT NULL,
  `modify_ts` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UQ_ACCOUNT_PRINCIPAL_PUBLIC_KEY` (`principal_type` ASC, `public_key` ASC),
  UNIQUE INDEX `UQ_ACCOUNT_PRINCIPAL_ACCOUNT` (`account` ASC),
  CONSTRAINT `FK_ACCOUNT_PRINCIPAL_PK_ACCOUNT`
    FOREIGN KEY (`account`)
    REFERENCES `kobalttown`.`user_account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ACCOUNT_PRINCIPAL_PK_PRINCIPAL_TYPE`
    FOREIGN KEY (`principal_type`)
    REFERENCES `kobalttown`.`cfg_principal_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `kobalttown` ;

-- -----------------------------------------------------
-- Placeholder table for view `kobalttown`.`view_account_auth`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kobalttown`.`view_account_auth` (`id` INT, `email` INT, `login_email` INT, `password` INT, `enabled` INT, `create_utc` INT, `update_utc` INT, `modify_ts` INT);

-- -----------------------------------------------------
-- View `kobalttown`.`view_account_auth`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kobalttown`.`view_account_auth`;
USE `kobalttown`;
CREATE  OR REPLACE VIEW `view_account_auth` AS
SELECT 
    `a`.`id`,
    `a`.`email`,
    `p`.`public_key` AS `login_email`,
    `p`.`private_key` as `password`,
    `a`.`enabled`,
    `a`.`create_utc`,
    `a`.`update_utc`,
    `a`.`modify_ts`
FROM
    `user_account` AS  `a`
        INNER JOIN `user_account_principal` AS `p`
			ON `p`.`account` = `a`.`id`
ORDER BY
	`a`.`id` ASC
;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
