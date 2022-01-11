-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema smartengine
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema smartengine
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `smartengine` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `smartengine` ;

-- -----------------------------------------------------
-- Table `smartengine`.`categorias`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `smartengine`.`categorias` ;

CREATE TABLE IF NOT EXISTS `smartengine`.`categorias` (
  `IdCategoria` TINYINT(4) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(40) NOT NULL,
  `Imagen` VARCHAR(30) NULL DEFAULT 'default.jpg',
  PRIMARY KEY (`IdCategoria`))
ENGINE = InnoDB
AUTO_INCREMENT = 31
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `smartengine`.`usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `smartengine`.`usuarios` ;

CREATE TABLE IF NOT EXISTS `smartengine`.`usuarios` (
  `idUsuario` TINYINT(4) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `nombre` VARCHAR(20) NOT NULL,
  `apellidos` VARCHAR(30) NOT NULL,
  `nif` CHAR(9) NOT NULL,
  `telefono` CHAR(9) NULL DEFAULT NULL,
  `direccion` VARCHAR(40) NOT NULL,
  `codigoPostal` CHAR(5) NOT NULL,
  `localidad` VARCHAR(40) NOT NULL,
  `provincia` VARCHAR(30) NOT NULL,
  `ultimoAcceso` DATETIME NULL DEFAULT NULL,
  `avatar` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE INDEX `email` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `smartengine`.`pedidos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `smartengine`.`pedidos` ;

CREATE TABLE IF NOT EXISTS `smartengine`.`pedidos` (
  `idPedido` TINYINT(4) NOT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  `tipo_usuario` ENUM('c', 'f') NULL DEFAULT 'c',
  `idUsuario` TINYINT(4) NULL DEFAULT NULL,
  `importe` FLOAT(6,2) UNSIGNED NULL DEFAULT NULL,
  `iva` FLOAT(6,2) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`idPedido`),
  INDEX `idUsuario` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `pedidos_ibfk_1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `smartengine`.`usuarios` (`idUsuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `smartengine`.`productos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `smartengine`.`productos` ;

CREATE TABLE IF NOT EXISTS `smartengine`.`productos` (
  `IdProducto` SMALLINT(6) NOT NULL AUTO_INCREMENT,
  `IdCategoria` TINYINT(4) NOT NULL,
  `Nombre` VARCHAR(100) NOT NULL,
  `Descripcion` MEDIUMTEXT NULL DEFAULT NULL,
  `Precio` DECIMAL(6,2) UNSIGNED NOT NULL,
  `Marca` VARCHAR(40) NOT NULL,
  `Imagen` VARCHAR(30) NULL DEFAULT 'default.jpg',
  PRIMARY KEY (`IdProducto`),
  INDEX `IdCategoria` (`IdCategoria` ASC) VISIBLE,
  CONSTRAINT `productos_categorias`
    FOREIGN KEY (`IdCategoria`)
    REFERENCES `smartengine`.`categorias` (`IdCategoria`))
ENGINE = InnoDB
AUTO_INCREMENT = 46
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `smartengine`.`lineaspedidos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `smartengine`.`lineaspedidos` ;

CREATE TABLE IF NOT EXISTS `smartengine`.`lineaspedidos` (
  `idLinea` TINYINT(4) NOT NULL,
  `idPedido` TINYINT(4) NOT NULL,
  `idProducto` SMALLINT(6) NOT NULL,
  `cantidad` SMALLINT(5) UNSIGNED NULL DEFAULT NULL,
  `orden` SMALLINT(5) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`idLinea`),
  INDEX `idPedido` (`idPedido` ASC) VISIBLE,
  INDEX `idProducto` (`idProducto` ASC) VISIBLE,
  CONSTRAINT `lineaspedidos_ibfk_1`
    FOREIGN KEY (`idPedido`)
    REFERENCES `smartengine`.`pedidos` (`idPedido`),
  CONSTRAINT `lineaspedidos_ibfk_2`
    FOREIGN KEY (`idProducto`)
    REFERENCES `smartengine`.`productos` (`IdProducto`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
