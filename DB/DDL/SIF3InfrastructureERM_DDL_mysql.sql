SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `SIF3_INFRA` ;
CREATE SCHEMA IF NOT EXISTS `SIF3_INFRA` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `SIF3_INFRA` ;

-- -----------------------------------------------------

-- Table `SIF3_SESSION`

-- -----------------------------------------------------
DROP TABLE IF EXISTS `SIF3_SESSION` ;

CREATE  TABLE IF NOT EXISTS `SIF3_SESSION` (
  `SESSION_ID` INT NOT NULL AUTO_INCREMENT ,
  `CONSUMER_NAME` VARCHAR(100) NULL ,
  `SOLUTION_ID` VARCHAR(100) NULL ,
  `APPLICATION_KEY` VARCHAR(100) NOT NULL ,
  `USER_TOKEN` VARCHAR(200) NULL ,
  `INSTANCE_ID` VARCHAR(200) NULL ,
  `PASSWORD` VARCHAR(100) NULL ,
  `SESSION_TOKEN` VARCHAR(200) NULL ,
  `ENVIRONMENT_ID` VARCHAR(36) NULL ,
  `ADAPTER_TYPE` VARCHAR(20) NULL COMMENT 'CONSUMER, PROVIDER, ENVIRONMENT_PROVIDER' ,
  `ENVIRONMENT_XML` TEXT NULL ,
  `QUEUE_STRATEGY` VARCHAR(20) NULL ,
  `CREATED` DATETIME NULL ,
  `LAST_ACCESSED` DATETIME NULL ,
  PRIMARY KEY (`SESSION_ID`) )
ENGINE = InnoDB;

CREATE INDEX `SOL_APP_KEY_IDX` ON `SIF3_SESSION` (`SOLUTION_ID` ASC, `APPLICATION_KEY` ASC, `ADAPTER_TYPE` ASC) ;

CREATE INDEX `SESSION_TK_IDX` ON `SIF3_SESSION` (`SESSION_TOKEN` ASC) ;

CREATE INDEX `ENV_ID_IDX` ON `SIF3_SESSION` (`ENVIRONMENT_ID` ASC, `ADAPTER_TYPE` ASC) ;

CREATE INDEX `SOL_APP_USR_INST_IDX` ON `SIF3_SESSION` (`SOLUTION_ID` ASC, `APPLICATION_KEY` ASC, `USER_TOKEN` ASC, `INSTANCE_ID` ASC, `ADAPTER_TYPE` ASC) ;

-- -----------------------------------------------------
-- Table `SIF3_QUEUE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SIF3_QUEUE` ;

CREATE  TABLE IF NOT EXISTS `SIF3_QUEUE` (
  `QUEUE_ID` VARCHAR(36) NOT NULL ,
  `NAME` VARCHAR(100) NOT NULL ,
  `QUEUE_TYPE` VARCHAR(10) NOT NULL COMMENT 'LONG, IMMEDIATE, WAKE_UP' ,
  `ENVIRONMENT_ID` VARCHAR(36) NOT NULL ,
  `ADAPTER_TYPE` VARCHAR(20) NOT NULL COMMENT 'CONSUMER, PROVIDER, ENVIRONMENT_PROVIDER' ,
  `ZONE_ID` VARCHAR(256) NULL ,
  `CONTEXT_ID` VARCHAR(256) NULL ,
  `SERVICE_NAME` VARCHAR(256) NULL ,
  `SERVICE_TYPE` VARCHAR(20) NULL ,
  `MESSAGE_URI` VARCHAR(256) NOT NULL ,
  `WAKUP_URI` VARCHAR(256) NULL ,
  `LONGPOLLING_TIMEOUT` INT NULL COMMENT 'Vakue in seconds\n' ,
  `WAIT_TIME` INT NULL ,
  `MAX_CONSUMERS` INT NULL ,
  `MSG_COUNT` INT NULL ,
  `CREATED` DATETIME NULL ,
  `LAST_ACCESSED` DATETIME NULL ,
  `LAST_MODIFIED` DATETIME NULL ,
  PRIMARY KEY (`QUEUE_ID`) )
ENGINE = InnoDB;

CREATE INDEX `IDX_QUEUE_ENVID` ON `SIF3_QUEUE` (`ENVIRONMENT_ID` ASC, `ADAPTER_TYPE` ASC) ;

CREATE INDEX `IDX_QUEUE_ZONE` ON `SIF3_QUEUE` (`ENVIRONMENT_ID` ASC, `ADAPTER_TYPE` ASC, `ZONE_ID` ASC) ;

CREATE INDEX `IDX_QUEUE_ZONE_CTX_SVC` ON `SIF3_QUEUE` (`ENVIRONMENT_ID` ASC, `ADAPTER_TYPE` ASC, `ZONE_ID` ASC, `CONTEXT_ID` ASC, `SERVICE_NAME` ASC, `SERVICE_TYPE` ASC) ;

-- -----------------------------------------------------
-- Table `SIF3_SUBSCRIPTION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SIF3_SUBSCRIPTION` ;

CREATE  TABLE IF NOT EXISTS `SIF3_SUBSCRIPTION` (
  `SUBSCRIPTION_ID` VARCHAR(36) NOT NULL ,
  `QUEUE_ID` VARCHAR(36) NOT NULL ,
  `ADAPTER_TYPE` VARCHAR(20) NOT NULL COMMENT 'CONSUMER, PROVIDER, ENVIRONMENT_PROVIDER' ,
  `ZONE_ID` VARCHAR(256) NULL ,
  `CONTEXT_ID` VARCHAR(256) NULL ,
  `SERVICE_NAME` VARCHAR(256) NOT NULL ,
  `SERVICE_TYPE` VARCHAR(20) NOT NULL ,
  `CREATED` DATETIME NULL ,
  `LAST_ACCESSED` DATETIME NULL ,
  PRIMARY KEY (`SUBSCRIPTION_ID`) ,
  CONSTRAINT `FK_SUBSCRIPTION_QUEUE`
    FOREIGN KEY (`QUEUE_ID` )
    REFERENCES `SIF3_QUEUE` (`QUEUE_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `IDX_SUBSCR_QUEUEID` ON `SIF3_SUBSCRIPTION` (`QUEUE_ID` ASC, `ADAPTER_TYPE` ASC) ;

CREATE INDEX `IDX_SUBSCR_SERVICE` ON `SIF3_SUBSCRIPTION` (`SERVICE_NAME` ASC, `SERVICE_TYPE` ASC) ;

CREATE INDEX `FK_SUBSCR_QUEUE` ON `SIF3_SUBSCRIPTION` (`QUEUE_ID` ASC) ;

CREATE INDEX `IDK_SUBSCR_ZONE_CTX_SVC` ON `SIF3_SUBSCRIPTION` (`ZONE_ID` ASC, `CONTEXT_ID` ASC, `SERVICE_NAME` ASC, `SERVICE_TYPE` ASC) ;

-- -----------------------------------------------------
-- Table `SIF3_ENV_TEMPLATE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SIF3_ENV_TEMPLATE` ;

CREATE  TABLE IF NOT EXISTS `SIF3_ENV_TEMPLATE` (
  `ENV_TEMPLATE_ID` VARCHAR(50) NOT NULL ,
  `TEMPLATE_FILE_NAME` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`ENV_TEMPLATE_ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SIF3_APP_TEMPLATE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SIF3_APP_TEMPLATE` ;

CREATE  TABLE IF NOT EXISTS `SIF3_APP_TEMPLATE` (
  `APP_TEMPLATE_ID` INT NOT NULL ,
  `SOLUTION_ID` VARCHAR(100) NULL ,
  `APPLICATION_KEY` VARCHAR(100) NULL ,
  `PASSWORD` VARCHAR(100) NULL ,
  `USER_TOKEN` VARCHAR(200) NULL ,
  `INSTANCE_ID` VARCHAR(200) NULL ,
  `AUTH_METHOD` VARCHAR(20) NULL ,
  `ENV_TEMPLATE_ID` VARCHAR(50) NULL ,
  PRIMARY KEY (`APP_TEMPLATE_ID`) ,
  CONSTRAINT `FK_APP_TMPLT_TO_ENV_TMPLT`
    FOREIGN KEY (`ENV_TEMPLATE_ID` )
    REFERENCES `SIF3_ENV_TEMPLATE` (`ENV_TEMPLATE_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `SOL_APPKEY_IDX` ON `SIF3_APP_TEMPLATE` (`SOLUTION_ID` ASC, `APPLICATION_KEY` ASC) ;

CREATE INDEX `APPKEY_IDX` ON `SIF3_APP_TEMPLATE` (`APPLICATION_KEY` ASC) ;

CREATE INDEX `SOL_APPKEY_USERTK_IDX` ON `SIF3_APP_TEMPLATE` (`SOLUTION_ID` ASC, `APPLICATION_KEY` ASC, `USER_TOKEN` ASC) ;

CREATE INDEX `APPKEY_USERTK_IDX` ON `SIF3_APP_TEMPLATE` (`APPLICATION_KEY` ASC, `USER_TOKEN` ASC) ;

CREATE INDEX `IDX_APP_TMPLT_TO_ENV_TMPLT` ON `SIF3_APP_TEMPLATE` (`ENV_TEMPLATE_ID` ASC) ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
